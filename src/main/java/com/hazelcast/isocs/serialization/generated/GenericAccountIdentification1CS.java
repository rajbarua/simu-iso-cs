package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class GenericAccountIdentification1CS extends GeneratedPain001CompactSerializer<GenericAccountIdentification1> {
    public GenericAccountIdentification1CS() {
        super(GenericAccountIdentification1.class);
    }

    @Override
    public void write(CompactWriter writer, GenericAccountIdentification1 object) {
        writer.writeString("id", object.getId());
        writer.writeString("issr", object.getIssr());
        writer.writeCompact("schmeNm", object.getSchmeNm());
    }

    @Override
    public GenericAccountIdentification1 read(CompactReader reader) {
        GenericAccountIdentification1 object = new GenericAccountIdentification1();
        object.setId(reader.readString("id"));
        object.setIssr(reader.readString("issr"));
        object.setSchmeNm(reader.readCompact("schmeNm"));
        return object;
    }
}
