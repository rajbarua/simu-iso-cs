package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class RemittanceAmount1CS extends GeneratedPain001CompactSerializer<RemittanceAmount1> {
    public RemittanceAmount1CS() {
        super(RemittanceAmount1.class);
    }

    @Override
    public void write(CompactWriter writer, RemittanceAmount1 object) {
        writer.writeArrayOfCompact("adjstmntAmtAndRsn", toArray(object.getAdjstmntAmtAndRsn(), DocumentAdjustment1[]::new));
        writer.writeCompact("cdtNoteAmt", object.getCdtNoteAmt());
        writer.writeCompact("dscntApldAmt", object.getDscntApldAmt());
        writer.writeCompact("duePyblAmt", object.getDuePyblAmt());
        writer.writeCompact("rmtdAmt", object.getRmtdAmt());
        writer.writeCompact("taxAmt", object.getTaxAmt());
    }

    @Override
    public RemittanceAmount1 read(CompactReader reader) {
        RemittanceAmount1 object = new RemittanceAmount1();
        var values0 = reader.readArrayOfCompact("adjstmntAmtAndRsn", DocumentAdjustment1.class);
        if (values0 != null) object.getAdjstmntAmtAndRsn().addAll(Arrays.asList(values0));
        object.setCdtNoteAmt(reader.readCompact("cdtNoteAmt"));
        object.setDscntApldAmt(reader.readCompact("dscntApldAmt"));
        object.setDuePyblAmt(reader.readCompact("duePyblAmt"));
        object.setRmtdAmt(reader.readCompact("rmtdAmt"));
        object.setTaxAmt(reader.readCompact("taxAmt"));
        return object;
    }
}
