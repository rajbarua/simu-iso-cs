package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class DatePeriodDetailsCS extends GeneratedPain001CompactSerializer<DatePeriodDetails> {
    public DatePeriodDetailsCS() {
        super(DatePeriodDetails.class);
    }

    @Override
    public void write(CompactWriter writer, DatePeriodDetails object) {
        writer.writeTimestampWithTimezone("frDt", object.getFrDt());
        writer.writeTimestampWithTimezone("toDt", object.getToDt());
    }

    @Override
    public DatePeriodDetails read(CompactReader reader) {
        DatePeriodDetails object = new DatePeriodDetails();
        object.setFrDt(reader.readTimestampWithTimezone("frDt"));
        object.setToDt(reader.readTimestampWithTimezone("toDt"));
        return object;
    }
}
