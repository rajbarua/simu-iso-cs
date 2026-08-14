package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxParty1CS extends GeneratedPain001CompactSerializer<TaxParty1> {
    public TaxParty1CS() {
        super(TaxParty1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxParty1 object) {
        writer.writeString("regnId", object.getRegnId());
        writer.writeString("taxId", object.getTaxId());
        writer.writeString("taxTp", object.getTaxTp());
    }

    @Override
    public TaxParty1 read(CompactReader reader) {
        TaxParty1 object = new TaxParty1();
        object.setRegnId(reader.readString("regnId"));
        object.setTaxId(reader.readString("taxId"));
        object.setTaxTp(reader.readString("taxTp"));
        return object;
    }
}
