package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class DateAndPlaceOfBirthCS extends GeneratedPain001CompactSerializer<DateAndPlaceOfBirth> {
    public DateAndPlaceOfBirthCS() {
        super(DateAndPlaceOfBirth.class);
    }

    @Override
    public void write(CompactWriter writer, DateAndPlaceOfBirth object) {
        writer.writeTimestampWithTimezone("birthDt", object.getBirthDt());
        writer.writeString("cityOfBirth", object.getCityOfBirth());
        writer.writeString("ctryOfBirth", object.getCtryOfBirth());
        writer.writeString("prvcOfBirth", object.getPrvcOfBirth());
    }

    @Override
    public DateAndPlaceOfBirth read(CompactReader reader) {
        DateAndPlaceOfBirth object = new DateAndPlaceOfBirth();
        object.setBirthDt(reader.readTimestampWithTimezone("birthDt"));
        object.setCityOfBirth(reader.readString("cityOfBirth"));
        object.setCtryOfBirth(reader.readString("ctryOfBirth"));
        object.setPrvcOfBirth(reader.readString("prvcOfBirth"));
        return object;
    }
}
