package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CreditorReferenceType1ChoiceCS extends GeneratedPain001CompactSerializer<CreditorReferenceType1Choice> {
    public CreditorReferenceType1ChoiceCS() {
        super(CreditorReferenceType1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, CreditorReferenceType1Choice object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public CreditorReferenceType1Choice read(CompactReader reader) {
        CreditorReferenceType1Choice object = new CreditorReferenceType1Choice();
        object.setCd(enumValue(reader.readString("cd"), DocumentType3Code::valueOf));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
