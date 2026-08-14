package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxAuthorisation1CS extends GeneratedPain001CompactSerializer<TaxAuthorisation1> {
    public TaxAuthorisation1CS() {
        super(TaxAuthorisation1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxAuthorisation1 object) {
        writer.writeString("nm", object.getNm());
        writer.writeString("titl", object.getTitl());
    }

    @Override
    public TaxAuthorisation1 read(CompactReader reader) {
        TaxAuthorisation1 object = new TaxAuthorisation1();
        object.setNm(reader.readString("nm"));
        object.setTitl(reader.readString("titl"));
        return object;
    }
}
