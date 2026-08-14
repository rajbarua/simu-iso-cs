package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ClearingSystemMemberIdentification2CS extends GeneratedPain001CompactSerializer<ClearingSystemMemberIdentification2> {
    public ClearingSystemMemberIdentification2CS() {
        super(ClearingSystemMemberIdentification2.class);
    }

    @Override
    public void write(CompactWriter writer, ClearingSystemMemberIdentification2 object) {
        writer.writeCompact("clrSysId", object.getClrSysId());
        writer.writeString("mmbId", object.getMmbId());
    }

    @Override
    public ClearingSystemMemberIdentification2 read(CompactReader reader) {
        ClearingSystemMemberIdentification2 object = new ClearingSystemMemberIdentification2();
        object.setClrSysId(reader.readCompact("clrSysId"));
        object.setMmbId(reader.readString("mmbId"));
        return object;
    }
}
