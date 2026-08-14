package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxRecordDetails1CS extends GeneratedPain001CompactSerializer<TaxRecordDetails1> {
    public TaxRecordDetails1CS() {
        super(TaxRecordDetails1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxRecordDetails1 object) {
        writer.writeCompact("amt", object.getAmt());
        writer.writeCompact("prd", object.getPrd());
    }

    @Override
    public TaxRecordDetails1 read(CompactReader reader) {
        TaxRecordDetails1 object = new TaxRecordDetails1();
        object.setAmt(reader.readCompact("amt"));
        object.setPrd(reader.readCompact("prd"));
        return object;
    }
}
