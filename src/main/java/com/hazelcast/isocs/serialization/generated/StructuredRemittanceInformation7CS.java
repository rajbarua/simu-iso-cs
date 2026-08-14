package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class StructuredRemittanceInformation7CS extends GeneratedPain001CompactSerializer<StructuredRemittanceInformation7> {
    public StructuredRemittanceInformation7CS() {
        super(StructuredRemittanceInformation7.class);
    }

    @Override
    public void write(CompactWriter writer, StructuredRemittanceInformation7 object) {
        writer.writeArrayOfString("addtlRmtInf", toArray(object.getAddtlRmtInf(), String[]::new));
        writer.writeCompact("cdtrRefInf", object.getCdtrRefInf());
        writer.writeCompact("invcee", object.getInvcee());
        writer.writeCompact("invcr", object.getInvcr());
        writer.writeCompact("rfrdDocAmt", object.getRfrdDocAmt());
        writer.writeArrayOfCompact("rfrdDocInf", toArray(object.getRfrdDocInf(), ReferredDocumentInformation3[]::new));
    }

    @Override
    public StructuredRemittanceInformation7 read(CompactReader reader) {
        StructuredRemittanceInformation7 object = new StructuredRemittanceInformation7();
        var values0 = reader.readArrayOfString("addtlRmtInf");
        if (values0 != null) object.getAddtlRmtInf().addAll(Arrays.asList(values0));
        object.setCdtrRefInf(reader.readCompact("cdtrRefInf"));
        object.setInvcee(reader.readCompact("invcee"));
        object.setInvcr(reader.readCompact("invcr"));
        object.setRfrdDocAmt(reader.readCompact("rfrdDocAmt"));
        var values1 = reader.readArrayOfCompact("rfrdDocInf", ReferredDocumentInformation3.class);
        if (values1 != null) object.getRfrdDocInf().addAll(Arrays.asList(values1));
        return object;
    }
}
