package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ActiveOrHistoricCurrencyAndAmountCS extends GeneratedPain001CompactSerializer<ActiveOrHistoricCurrencyAndAmount> {
    public ActiveOrHistoricCurrencyAndAmountCS() {
        super(ActiveOrHistoricCurrencyAndAmount.class);
    }

    @Override
    public void write(CompactWriter writer, ActiveOrHistoricCurrencyAndAmount object) {
        writer.writeString("ccy", object.getCcy());
        writer.writeDecimal("value", object.getValue());
    }

    @Override
    public ActiveOrHistoricCurrencyAndAmount read(CompactReader reader) {
        ActiveOrHistoricCurrencyAndAmount object = new ActiveOrHistoricCurrencyAndAmount();
        object.setCcy(reader.readString("ccy"));
        object.setValue(reader.readDecimal("value"));
        return object;
    }
}
