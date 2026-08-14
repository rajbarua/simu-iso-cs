package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxPeriod1CS extends GeneratedPain001CompactSerializer<TaxPeriod1> {
    public TaxPeriod1CS() {
        super(TaxPeriod1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxPeriod1 object) {
        writer.writeCompact("frToDt", object.getFrToDt());
        writer.writeString("tp", object.getTp() == null ? null : object.getTp().name());
        writer.writeTimestampWithTimezone("yr", object.getYr());
    }

    @Override
    public TaxPeriod1 read(CompactReader reader) {
        TaxPeriod1 object = new TaxPeriod1();
        object.setFrToDt(reader.readCompact("frToDt"));
        object.setTp(enumValue(reader.readString("tp"), TaxRecordPeriod1Code::valueOf));
        object.setYr(reader.readTimestampWithTimezone("yr"));
        return object;
    }
}
