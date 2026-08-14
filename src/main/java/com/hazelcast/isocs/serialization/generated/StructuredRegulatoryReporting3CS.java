package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class StructuredRegulatoryReporting3CS extends GeneratedPain001CompactSerializer<StructuredRegulatoryReporting3> {
    public StructuredRegulatoryReporting3CS() {
        super(StructuredRegulatoryReporting3.class);
    }

    @Override
    public void write(CompactWriter writer, StructuredRegulatoryReporting3 object) {
        writer.writeCompact("amt", object.getAmt());
        writer.writeString("cd", object.getCd());
        writer.writeString("ctry", object.getCtry());
        writer.writeTimestampWithTimezone("dt", object.getDt());
        writer.writeArrayOfString("inf", toArray(object.getInf(), String[]::new));
        writer.writeString("tp", object.getTp());
    }

    @Override
    public StructuredRegulatoryReporting3 read(CompactReader reader) {
        StructuredRegulatoryReporting3 object = new StructuredRegulatoryReporting3();
        object.setAmt(reader.readCompact("amt"));
        object.setCd(reader.readString("cd"));
        object.setCtry(reader.readString("ctry"));
        object.setDt(reader.readTimestampWithTimezone("dt"));
        var values0 = reader.readArrayOfString("inf");
        if (values0 != null) object.getInf().addAll(Arrays.asList(values0));
        object.setTp(reader.readString("tp"));
        return object;
    }
}
