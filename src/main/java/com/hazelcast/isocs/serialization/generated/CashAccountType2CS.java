package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CashAccountType2CS extends GeneratedPain001CompactSerializer<CashAccountType2> {
    public CashAccountType2CS() {
        super(CashAccountType2.class);
    }

    @Override
    public void write(CompactWriter writer, CashAccountType2 object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public CashAccountType2 read(CompactReader reader) {
        CashAccountType2 object = new CashAccountType2();
        object.setCd(enumValue(reader.readString("cd"), CashAccountType4Code::valueOf));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
