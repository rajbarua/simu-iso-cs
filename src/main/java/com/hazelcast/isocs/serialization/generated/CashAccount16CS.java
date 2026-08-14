package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CashAccount16CS extends GeneratedPain001CompactSerializer<CashAccount16> {
    public CashAccount16CS() {
        super(CashAccount16.class);
    }

    @Override
    public void write(CompactWriter writer, CashAccount16 object) {
        writer.writeString("ccy", object.getCcy());
        writer.writeCompact("id", object.getId());
        writer.writeString("nm", object.getNm());
        writer.writeCompact("tp", object.getTp());
    }

    @Override
    public CashAccount16 read(CompactReader reader) {
        CashAccount16 object = new CashAccount16();
        object.setCcy(reader.readString("ccy"));
        object.setId(reader.readCompact("id"));
        object.setNm(reader.readString("nm"));
        object.setTp(reader.readCompact("tp"));
        return object;
    }
}
