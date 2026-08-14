package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CreditorReferenceType2CS extends GeneratedPain001CompactSerializer<CreditorReferenceType2> {
    public CreditorReferenceType2CS() {
        super(CreditorReferenceType2.class);
    }

    @Override
    public void write(CompactWriter writer, CreditorReferenceType2 object) {
        writer.writeCompact("cdOrPrtry", object.getCdOrPrtry());
        writer.writeString("issr", object.getIssr());
    }

    @Override
    public CreditorReferenceType2 read(CompactReader reader) {
        CreditorReferenceType2 object = new CreditorReferenceType2();
        object.setCdOrPrtry(reader.readCompact("cdOrPrtry"));
        object.setIssr(reader.readString("issr"));
        return object;
    }
}
