package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class TaxRecord1CS extends GeneratedPain001CompactSerializer<TaxRecord1> {
    public TaxRecord1CS() {
        super(TaxRecord1.class);
    }

    @Override
    public void write(CompactWriter writer, TaxRecord1 object) {
        writer.writeString("addtlInf", object.getAddtlInf());
        writer.writeString("certId", object.getCertId());
        writer.writeString("ctgy", object.getCtgy());
        writer.writeString("ctgyDtls", object.getCtgyDtls());
        writer.writeString("dbtrSts", object.getDbtrSts());
        writer.writeString("frmsCd", object.getFrmsCd());
        writer.writeCompact("prd", object.getPrd());
        writer.writeCompact("taxAmt", object.getTaxAmt());
        writer.writeString("tp", object.getTp());
    }

    @Override
    public TaxRecord1 read(CompactReader reader) {
        TaxRecord1 object = new TaxRecord1();
        object.setAddtlInf(reader.readString("addtlInf"));
        object.setCertId(reader.readString("certId"));
        object.setCtgy(reader.readString("ctgy"));
        object.setCtgyDtls(reader.readString("ctgyDtls"));
        object.setDbtrSts(reader.readString("dbtrSts"));
        object.setFrmsCd(reader.readString("frmsCd"));
        object.setPrd(reader.readCompact("prd"));
        object.setTaxAmt(reader.readCompact("taxAmt"));
        object.setTp(reader.readString("tp"));
        return object;
    }
}
