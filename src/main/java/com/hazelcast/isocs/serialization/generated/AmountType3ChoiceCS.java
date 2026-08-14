package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class AmountType3ChoiceCS extends GeneratedPain001CompactSerializer<AmountType3Choice> {
    public AmountType3ChoiceCS() {
        super(AmountType3Choice.class);
    }

    @Override
    public void write(CompactWriter writer, AmountType3Choice object) {
        writer.writeCompact("eqvtAmt", object.getEqvtAmt());
        writer.writeCompact("instdAmt", object.getInstdAmt());
    }

    @Override
    public AmountType3Choice read(CompactReader reader) {
        AmountType3Choice object = new AmountType3Choice();
        object.setEqvtAmt(reader.readCompact("eqvtAmt"));
        object.setInstdAmt(reader.readCompact("instdAmt"));
        return object;
    }
}
