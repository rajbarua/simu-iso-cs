package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CreditorReferenceInformation2CS extends GeneratedPain001CompactSerializer<CreditorReferenceInformation2> {
    public CreditorReferenceInformation2CS() {
        super(CreditorReferenceInformation2.class);
    }

    @Override
    public void write(CompactWriter writer, CreditorReferenceInformation2 object) {
        writer.writeString("ref", object.getRef());
        writer.writeCompact("tp", object.getTp());
    }

    @Override
    public CreditorReferenceInformation2 read(CompactReader reader) {
        CreditorReferenceInformation2 object = new CreditorReferenceInformation2();
        object.setRef(reader.readString("ref"));
        object.setTp(reader.readCompact("tp"));
        return object;
    }
}
