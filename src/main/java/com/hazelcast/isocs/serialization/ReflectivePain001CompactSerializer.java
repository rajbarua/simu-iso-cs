package com.hazelcast.isocs.serialization;

import com.hazelcast.nio.serialization.HazelcastSerializationException;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Explicit Compact serializer used by the pain.001 benchmark.
 *
 * <p>The field plan is built once when the serializer is registered. Unlike Hazelcast's no-code
 * serializer in 5.7.0, each non-primitive field is read exactly once. Concrete no-argument
 * subclasses are registered for every generated pain.001 class so nested objects never fall back
 * to no-code Compact serialization.</p>
 */
public abstract class ReflectivePain001CompactSerializer<T> implements CompactSerializer<T> {
    static final String TYPE_NAME_PREFIX = "simu-iso-cs.explicit.";

    private final Class<T> compactClass;
    private final Constructor<T> constructor;
    private final FieldPlan[] fields;

    protected ReflectivePain001CompactSerializer(Class<T> compactClass) {
        this.compactClass = compactClass;
        try {
            constructor = compactClass.getDeclaredConstructor();
            constructor.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new HazelcastSerializationException("No accessible empty constructor for " + compactClass.getName(), e);
        }
        fields = Arrays.stream(compactClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> !Modifier.isTransient(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .map(FieldPlan::new)
                .toArray(FieldPlan[]::new);
    }

    @Override
    public final Class<T> getCompactClass() {
        return compactClass;
    }

    @Override
    public final String getTypeName() {
        return TYPE_NAME_PREFIX + compactClass.getName();
    }

    @Override
    public final void write(CompactWriter writer, T object) {
        try {
            for (FieldPlan field : fields) {
                field.write(writer, object);
            }
        } catch (ReflectiveOperationException e) {
            throw new HazelcastSerializationException("Cannot write " + compactClass.getName(), e);
        }
    }

    @Override
    public final T read(CompactReader reader) {
        try {
            T object = constructor.newInstance();
            for (FieldPlan field : fields) {
                field.read(reader, object);
            }
            return object;
        } catch (ReflectiveOperationException e) {
            throw new HazelcastSerializationException("Cannot read " + compactClass.getName(), e);
        }
    }

    private enum Kind {
        STRING,
        DECIMAL,
        OFFSET_DATE_TIME,
        BOOLEAN,
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        CHAR,
        ENUM,
        LIST_STRING,
        LIST_ENUM,
        LIST_COMPACT,
        COMPACT
    }

    private static final class FieldPlan {
        private final Field field;
        private final String name;
        private final Class<?> type;
        private final Class<?> componentType;
        private final Kind kind;

        private FieldPlan(Field field) {
            this.field = field;
            field.setAccessible(true);
            name = field.getName();
            type = field.getType();
            if (List.class.equals(type)) {
                componentType = listComponentType(field.getGenericType(), field);
                if (String.class.equals(componentType)) {
                    kind = Kind.LIST_STRING;
                } else if (componentType.isEnum()) {
                    kind = Kind.LIST_ENUM;
                } else if (componentType.getPackageName().equals("com.hz.demo.pmt.pain001_03")) {
                    kind = Kind.LIST_COMPACT;
                } else {
                    throw unsupported(field);
                }
            } else {
                componentType = null;
                kind = scalarKind(type, field);
            }
        }

        private void write(CompactWriter writer, Object object) throws IllegalAccessException {
            Object value = field.get(object);
            switch (kind) {
                case STRING -> writer.writeString(name, (String) value);
                case DECIMAL -> writer.writeDecimal(name, (BigDecimal) value);
                case OFFSET_DATE_TIME -> writer.writeTimestampWithTimezone(name, (OffsetDateTime) value);
                case BOOLEAN -> writeBoolean(writer, value);
                case BYTE -> writeByte(writer, value);
                case SHORT -> writeShort(writer, value);
                case INT -> writeInt(writer, value);
                case LONG -> writeLong(writer, value);
                case FLOAT -> writeFloat(writer, value);
                case DOUBLE -> writeDouble(writer, value);
                case CHAR -> writeChar(writer, value);
                case ENUM -> writer.writeString(name, value == null ? null : ((Enum<?>) value).name());
                case LIST_STRING -> writer.writeArrayOfString(name, toStringArray(value));
                case LIST_ENUM -> writer.writeArrayOfString(name, toEnumNameArray(value));
                case LIST_COMPACT -> writer.writeArrayOfCompact(name, toTypedArray(value, componentType));
                case COMPACT -> writer.writeCompact(name, value);
            }
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        private void read(CompactReader reader, Object object) throws IllegalAccessException {
            Object value = switch (kind) {
                case STRING -> reader.readString(name);
                case DECIMAL -> reader.readDecimal(name);
                case OFFSET_DATE_TIME -> reader.readTimestampWithTimezone(name);
                case BOOLEAN -> type.isPrimitive() ? reader.readBoolean(name) : reader.readNullableBoolean(name);
                case BYTE -> type.isPrimitive() ? reader.readInt8(name) : reader.readNullableInt8(name);
                case SHORT -> type.isPrimitive() ? reader.readInt16(name) : reader.readNullableInt16(name);
                case INT -> type.isPrimitive() ? reader.readInt32(name) : reader.readNullableInt32(name);
                case LONG -> type.isPrimitive() ? reader.readInt64(name) : reader.readNullableInt64(name);
                case FLOAT -> type.isPrimitive() ? reader.readFloat32(name) : reader.readNullableFloat32(name);
                case DOUBLE -> type.isPrimitive() ? reader.readFloat64(name) : reader.readNullableFloat64(name);
                case CHAR -> (char) reader.readInt16(name);
                case ENUM -> {
                    String enumName = reader.readString(name);
                    yield enumName == null ? null : Enum.valueOf((Class<? extends Enum>) type, enumName);
                }
                case LIST_STRING -> mutableList(reader.readArrayOfString(name));
                case LIST_ENUM -> {
                    String[] names = reader.readArrayOfString(name);
                    if (names == null) {
                        yield null;
                    }
                    List<Enum<?>> values = new ArrayList<>(names.length);
                    for (String enumName : names) {
                        values.add(enumName == null ? null : Enum.valueOf((Class<? extends Enum>) componentType, enumName));
                    }
                    yield values;
                }
                case LIST_COMPACT -> mutableList(reader.readArrayOfCompact(name, (Class) componentType));
                case COMPACT -> reader.readCompact(name);
            };
            field.set(object, value);
        }

        private void writeBoolean(CompactWriter writer, Object value) {
            if (type.isPrimitive()) {
                writer.writeBoolean(name, (Boolean) value);
            } else {
                writer.writeNullableBoolean(name, (Boolean) value);
            }
        }

        private void writeByte(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeInt8(name, (Byte) value);
            else writer.writeNullableInt8(name, (Byte) value);
        }

        private void writeShort(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeInt16(name, (Short) value);
            else writer.writeNullableInt16(name, (Short) value);
        }

        private void writeInt(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeInt32(name, (Integer) value);
            else writer.writeNullableInt32(name, (Integer) value);
        }

        private void writeLong(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeInt64(name, (Long) value);
            else writer.writeNullableInt64(name, (Long) value);
        }

        private void writeFloat(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeFloat32(name, (Float) value);
            else writer.writeNullableFloat32(name, (Float) value);
        }

        private void writeDouble(CompactWriter writer, Object value) {
            if (type.isPrimitive()) writer.writeFloat64(name, (Double) value);
            else writer.writeNullableFloat64(name, (Double) value);
        }

        private void writeChar(CompactWriter writer, Object value) {
            writer.writeInt16(name, (short) ((Character) value).charValue());
        }

        private static Kind scalarKind(Class<?> type, Field field) {
            if (String.class.equals(type)) return Kind.STRING;
            if (BigDecimal.class.equals(type)) return Kind.DECIMAL;
            if (OffsetDateTime.class.equals(type)) return Kind.OFFSET_DATE_TIME;
            if (Boolean.class.equals(type) || boolean.class.equals(type)) return Kind.BOOLEAN;
            if (Byte.class.equals(type) || byte.class.equals(type)) return Kind.BYTE;
            if (Short.class.equals(type) || short.class.equals(type)) return Kind.SHORT;
            if (Integer.class.equals(type) || int.class.equals(type)) return Kind.INT;
            if (Long.class.equals(type) || long.class.equals(type)) return Kind.LONG;
            if (Float.class.equals(type) || float.class.equals(type)) return Kind.FLOAT;
            if (Double.class.equals(type) || double.class.equals(type)) return Kind.DOUBLE;
            if (Character.class.equals(type) || char.class.equals(type)) return Kind.CHAR;
            if (type.isEnum()) return Kind.ENUM;
            if (type.getPackageName().equals("com.hz.demo.pmt.pain001_03")) return Kind.COMPACT;
            throw unsupported(field);
        }

        private static Class<?> listComponentType(Type genericType, Field field) {
            if (genericType instanceof ParameterizedType parameterizedType
                    && parameterizedType.getActualTypeArguments().length == 1
                    && parameterizedType.getActualTypeArguments()[0] instanceof Class<?> component) {
                return component;
            }
            throw unsupported(field);
        }

        private static HazelcastSerializationException unsupported(Field field) {
            return new HazelcastSerializationException("Unsupported explicit Compact field "
                    + field.getDeclaringClass().getName() + '.' + field.getName() + ": " + field.getGenericType());
        }

        private static String[] toStringArray(Object value) {
            return value == null ? null : ((List<?>) value).toArray(String[]::new);
        }

        private static String[] toEnumNameArray(Object value) {
            if (value == null) return null;
            List<?> list = (List<?>) value;
            String[] names = new String[list.size()];
            for (int i = 0; i < names.length; i++) {
                Object element = list.get(i);
                names[i] = element == null ? null : ((Enum<?>) element).name();
            }
            return names;
        }

        private static Object[] toTypedArray(Object value, Class<?> componentType) {
            if (value == null) return null;
            List<?> list = (List<?>) value;
            Object[] array = (Object[]) Array.newInstance(componentType, list.size());
            return list.toArray(array);
        }

        private static <E> List<E> mutableList(E[] values) {
            return values == null ? null : new ArrayList<>(Arrays.asList(values));
        }
    }
}
