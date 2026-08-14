package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class BranchData2CS extends GeneratedPain001CompactSerializer<BranchData2> {
    public BranchData2CS() {
        super(BranchData2.class);
    }

    @Override
    public void write(CompactWriter writer, BranchData2 object) {
        writer.writeString("id", object.getId());
        writer.writeString("nm", object.getNm());
        writer.writeCompact("pstlAdr", object.getPstlAdr());
    }

    @Override
    public BranchData2 read(CompactReader reader) {
        BranchData2 object = new BranchData2();
        object.setId(reader.readString("id"));
        object.setNm(reader.readString("nm"));
        object.setPstlAdr(reader.readCompact("pstlAdr"));
        return object;
    }
}
