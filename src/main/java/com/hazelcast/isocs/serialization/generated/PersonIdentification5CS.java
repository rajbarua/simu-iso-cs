package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PersonIdentification5CS extends GeneratedPain001CompactSerializer<PersonIdentification5> {
    public PersonIdentification5CS() {
        super(PersonIdentification5.class);
    }

    @Override
    public void write(CompactWriter writer, PersonIdentification5 object) {
        writer.writeCompact("dtAndPlcOfBirth", object.getDtAndPlcOfBirth());
        writer.writeArrayOfCompact("othr", toArray(object.getOthr(), GenericPersonIdentification1[]::new));
    }

    @Override
    public PersonIdentification5 read(CompactReader reader) {
        PersonIdentification5 object = new PersonIdentification5();
        object.setDtAndPlcOfBirth(reader.readCompact("dtAndPlcOfBirth"));
        var values0 = reader.readArrayOfCompact("othr", GenericPersonIdentification1.class);
        if (values0 != null) object.getOthr().addAll(Arrays.asList(values0));
        return object;
    }
}
