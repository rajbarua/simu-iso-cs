package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class NameAndAddress10CS extends GeneratedPain001CompactSerializer<NameAndAddress10> {
    public NameAndAddress10CS() {
        super(NameAndAddress10.class);
    }

    @Override
    public void write(CompactWriter writer, NameAndAddress10 object) {
        writer.writeCompact("adr", object.getAdr());
        writer.writeString("nm", object.getNm());
    }

    @Override
    public NameAndAddress10 read(CompactReader reader) {
        NameAndAddress10 object = new NameAndAddress10();
        object.setAdr(reader.readCompact("adr"));
        object.setNm(reader.readString("nm"));
        return object;
    }
}
