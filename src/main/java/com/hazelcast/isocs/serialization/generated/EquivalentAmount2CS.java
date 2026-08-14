package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class EquivalentAmount2CS extends GeneratedPain001CompactSerializer<EquivalentAmount2> {
    public EquivalentAmount2CS() {
        super(EquivalentAmount2.class);
    }

    @Override
    public void write(CompactWriter writer, EquivalentAmount2 object) {
        writer.writeCompact("amt", object.getAmt());
        writer.writeString("ccyOfTrf", object.getCcyOfTrf());
    }

    @Override
    public EquivalentAmount2 read(CompactReader reader) {
        EquivalentAmount2 object = new EquivalentAmount2();
        object.setAmt(reader.readCompact("amt"));
        object.setCcyOfTrf(reader.readString("ccyOfTrf"));
        return object;
    }
}
