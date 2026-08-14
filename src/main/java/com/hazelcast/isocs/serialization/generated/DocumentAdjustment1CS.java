package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class DocumentAdjustment1CS extends GeneratedPain001CompactSerializer<DocumentAdjustment1> {
    public DocumentAdjustment1CS() {
        super(DocumentAdjustment1.class);
    }

    @Override
    public void write(CompactWriter writer, DocumentAdjustment1 object) {
        writer.writeString("addtlInf", object.getAddtlInf());
        writer.writeCompact("amt", object.getAmt());
        writer.writeString("cdtDbtInd", object.getCdtDbtInd() == null ? null : object.getCdtDbtInd().name());
        writer.writeString("rsn", object.getRsn());
    }

    @Override
    public DocumentAdjustment1 read(CompactReader reader) {
        DocumentAdjustment1 object = new DocumentAdjustment1();
        object.setAddtlInf(reader.readString("addtlInf"));
        object.setAmt(reader.readCompact("amt"));
        object.setCdtDbtInd(enumValue(reader.readString("cdtDbtInd"), CreditDebitCode::valueOf));
        object.setRsn(reader.readString("rsn"));
        return object;
    }
}
