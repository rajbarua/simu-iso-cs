package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PaymentIdentification1CS extends GeneratedPain001CompactSerializer<PaymentIdentification1> {
    public PaymentIdentification1CS() {
        super(PaymentIdentification1.class);
    }

    @Override
    public void write(CompactWriter writer, PaymentIdentification1 object) {
        writer.writeString("endToEndId", object.getEndToEndId());
        writer.writeString("instrId", object.getInstrId());
    }

    @Override
    public PaymentIdentification1 read(CompactReader reader) {
        PaymentIdentification1 object = new PaymentIdentification1();
        object.setEndToEndId(reader.readString("endToEndId"));
        object.setInstrId(reader.readString("instrId"));
        return object;
    }
}
