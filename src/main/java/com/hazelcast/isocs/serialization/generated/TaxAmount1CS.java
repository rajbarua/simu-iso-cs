package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxAmount1CS extends GeneratedPain001CompactSerializer<TaxAmount1> {
    public TaxAmount1CS() {
        super(TaxAmount1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxAmount1 object) {
        writer.writeArrayOfCompact("dtls", toArray(object.getDtls(), TaxRecordDetails1[]::new));
        writer.writeDecimal("rate", object.getRate());
        writer.writeCompact("taxblBaseAmt", object.getTaxblBaseAmt());
        writer.writeCompact("ttlAmt", object.getTtlAmt());
    }

    @Override
    public TaxAmount1 read(CompactReader reader) {
        TaxAmount1 object = new TaxAmount1();
        var values0 = reader.readArrayOfCompact("dtls", TaxRecordDetails1.class);
        if (values0 != null) object.getDtls().addAll(Arrays.asList(values0));
        object.setRate(reader.readDecimal("rate"));
        object.setTaxblBaseAmt(reader.readCompact("taxblBaseAmt"));
        object.setTtlAmt(reader.readCompact("ttlAmt"));
        return object;
    }
}
