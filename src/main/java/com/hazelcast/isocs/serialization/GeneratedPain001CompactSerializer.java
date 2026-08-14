package com.hazelcast.isocs.serialization;

import com.hazelcast.nio.serialization.compact.CompactSerializer;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

/** Common non-reflective support for the generated pain.001 Compact serializers. */
public abstract class GeneratedPain001CompactSerializer<T> implements CompactSerializer<T> {
    public static final String TYPE_NAME_PREFIX = "simu-iso-cs.explicit.";

    private final Class<T> compactClass;

    protected GeneratedPain001CompactSerializer(Class<T> compactClass) {
        this.compactClass = compactClass;
    }

    @Override
    public final Class<T> getCompactClass() {
        return compactClass;
    }

    @Override
    public final String getTypeName() {
        return TYPE_NAME_PREFIX + compactClass.getName();
    }

    protected static <E> E[] toArray(List<E> values, IntFunction<E[]> arrayFactory) {
        return values == null || values.isEmpty() ? null : values.toArray(arrayFactory);
    }

    protected static <E extends Enum<E>> String[] enumNames(List<E> values) {
        if (values == null || values.isEmpty()) return null;
        String[] names = new String[values.size()];
        for (int i = 0; i < names.length; i++) {
            E value = values.get(i);
            names[i] = value == null ? null : value.name();
        }
        return names;
    }

    protected static <E extends Enum<E>> E enumValue(String name, Function<String, E> valueOf) {
        return name == null ? null : valueOf.apply(name);
    }

    protected static <E extends Enum<E>> void addEnumNames(
            List<E> target, String[] names, Function<String, E> valueOf) {
        for (String name : names) {
            target.add(enumValue(name, valueOf));
        }
    }
}
