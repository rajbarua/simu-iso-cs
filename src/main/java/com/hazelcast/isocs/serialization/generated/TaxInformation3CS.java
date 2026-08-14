package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxInformation3CS extends GeneratedPain001CompactSerializer<TaxInformation3> {
    public TaxInformation3CS() {
        super(TaxInformation3.class);
    }

    @Override
    public void write(CompactWriter writer, TaxInformation3 object) {
        writer.writeString("admstnZn", object.getAdmstnZn());
        writer.writeCompact("cdtr", object.getCdtr());
        writer.writeCompact("dbtr", object.getDbtr());
        writer.writeTimestampWithTimezone("dt", object.getDt());
        writer.writeString("mtd", object.getMtd());
        writer.writeArrayOfCompact("rcrd", toArray(object.getRcrd(), TaxRecord1[]::new));
        writer.writeString("refNb", object.getRefNb());
        writer.writeDecimal("seqNb", object.getSeqNb());
        writer.writeCompact("ttlTaxAmt", object.getTtlTaxAmt());
        writer.writeCompact("ttlTaxblBaseAmt", object.getTtlTaxblBaseAmt());
    }

    @Override
    public TaxInformation3 read(CompactReader reader) {
        TaxInformation3 object = new TaxInformation3();
        object.setAdmstnZn(reader.readString("admstnZn"));
        object.setCdtr(reader.readCompact("cdtr"));
        object.setDbtr(reader.readCompact("dbtr"));
        object.setDt(reader.readTimestampWithTimezone("dt"));
        object.setMtd(reader.readString("mtd"));
        var values0 = reader.readArrayOfCompact("rcrd", TaxRecord1.class);
        if (values0 != null) object.getRcrd().addAll(Arrays.asList(values0));
        object.setRefNb(reader.readString("refNb"));
        object.setSeqNb(reader.readDecimal("seqNb"));
        object.setTtlTaxAmt(reader.readCompact("ttlTaxAmt"));
        object.setTtlTaxblBaseAmt(reader.readCompact("ttlTaxblBaseAmt"));
        return object;
    }
}
