package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxParty2CS extends GeneratedPain001CompactSerializer<TaxParty2> {
    public TaxParty2CS() {
        super(TaxParty2.class);
    }

    @Override
    public void write(CompactWriter writer, TaxParty2 object) {
        writer.writeCompact("authstn", object.getAuthstn());
        writer.writeString("regnId", object.getRegnId());
        writer.writeString("taxId", object.getTaxId());
        writer.writeString("taxTp", object.getTaxTp());
    }

    @Override
    public TaxParty2 read(CompactReader reader) {
        TaxParty2 object = new TaxParty2();
        object.setAuthstn(reader.readCompact("authstn"));
        object.setRegnId(reader.readString("regnId"));
        object.setTaxId(reader.readString("taxId"));
        object.setTaxTp(reader.readString("taxTp"));
        return object;
    }
}
