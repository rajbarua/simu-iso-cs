package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PaymentTypeInformation19CS extends GeneratedPain001CompactSerializer<PaymentTypeInformation19> {
    public PaymentTypeInformation19CS() {
        super(PaymentTypeInformation19.class);
    }

    @Override
    public void write(CompactWriter writer, PaymentTypeInformation19 object) {
        writer.writeCompact("ctgyPurp", object.getCtgyPurp());
        writer.writeString("instrPrty", object.getInstrPrty() == null ? null : object.getInstrPrty().name());
        writer.writeCompact("lclInstrm", object.getLclInstrm());
        writer.writeCompact("svcLvl", object.getSvcLvl());
    }

    @Override
    public PaymentTypeInformation19 read(CompactReader reader) {
        PaymentTypeInformation19 object = new PaymentTypeInformation19();
        object.setCtgyPurp(reader.readCompact("ctgyPurp"));
        object.setInstrPrty(enumValue(reader.readString("instrPrty"), Priority2Code::valueOf));
        object.setLclInstrm(reader.readCompact("lclInstrm"));
        object.setSvcLvl(reader.readCompact("svcLvl"));
        return object;
    }
}
