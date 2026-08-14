package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class Party6ChoiceCS extends GeneratedPain001CompactSerializer<Party6Choice> {
    public Party6ChoiceCS() {
        super(Party6Choice.class);
    }

    @Override
    public void write(CompactWriter writer, Party6Choice object) {
        writer.writeCompact("orgId", object.getOrgId());
        writer.writeCompact("prvtId", object.getPrvtId());
    }

    @Override
    public Party6Choice read(CompactReader reader) {
        Party6Choice object = new Party6Choice();
        object.setOrgId(reader.readCompact("orgId"));
        object.setPrvtId(reader.readCompact("prvtId"));
        return object;
    }
}
