package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class GenericFinancialIdentification1CS extends GeneratedPain001CompactSerializer<GenericFinancialIdentification1> {
    public GenericFinancialIdentification1CS() {
        super(GenericFinancialIdentification1.class);
    }

    @Override
    public void write(CompactWriter writer, GenericFinancialIdentification1 object) {
        writer.writeString("id", object.getId());
        writer.writeString("issr", object.getIssr());
        writer.writeCompact("schmeNm", object.getSchmeNm());
    }

    @Override
    public GenericFinancialIdentification1 read(CompactReader reader) {
        GenericFinancialIdentification1 object = new GenericFinancialIdentification1();
        object.setId(reader.readString("id"));
        object.setIssr(reader.readString("issr"));
        object.setSchmeNm(reader.readCompact("schmeNm"));
        return object;
    }
}
