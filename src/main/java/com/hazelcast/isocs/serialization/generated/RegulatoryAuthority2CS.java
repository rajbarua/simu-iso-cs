package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class RegulatoryAuthority2CS extends GeneratedPain001CompactSerializer<RegulatoryAuthority2> {
    public RegulatoryAuthority2CS() {
        super(RegulatoryAuthority2.class);
    }

    @Override
    public void write(CompactWriter writer, RegulatoryAuthority2 object) {
        writer.writeString("ctry", object.getCtry());
        writer.writeString("nm", object.getNm());
    }

    @Override
    public RegulatoryAuthority2 read(CompactReader reader) {
        RegulatoryAuthority2 object = new RegulatoryAuthority2();
        object.setCtry(reader.readString("ctry"));
        object.setNm(reader.readString("nm"));
        return object;
    }
}
