package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ExchangeRateInformation1CS extends GeneratedPain001CompactSerializer<ExchangeRateInformation1> {
    public ExchangeRateInformation1CS() {
        super(ExchangeRateInformation1.class);
    }

    @Override
    public void write(CompactWriter writer, ExchangeRateInformation1 object) {
        writer.writeString("ctrctId", object.getCtrctId());
        writer.writeString("rateTp", object.getRateTp() == null ? null : object.getRateTp().name());
        writer.writeDecimal("xchgRate", object.getXchgRate());
    }

    @Override
    public ExchangeRateInformation1 read(CompactReader reader) {
        ExchangeRateInformation1 object = new ExchangeRateInformation1();
        object.setCtrctId(reader.readString("ctrctId"));
        object.setRateTp(enumValue(reader.readString("rateTp"), ExchangeRateType1Code::valueOf));
        object.setXchgRate(reader.readDecimal("xchgRate"));
        return object;
    }
}
