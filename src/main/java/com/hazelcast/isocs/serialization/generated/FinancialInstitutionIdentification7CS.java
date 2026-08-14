package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class FinancialInstitutionIdentification7CS extends GeneratedPain001CompactSerializer<FinancialInstitutionIdentification7> {
    public FinancialInstitutionIdentification7CS() {
        super(FinancialInstitutionIdentification7.class);
    }

    @Override
    public void write(CompactWriter writer, FinancialInstitutionIdentification7 object) {
        writer.writeString("bic", object.getBIC());
        writer.writeCompact("clrSysMmbId", object.getClrSysMmbId());
        writer.writeString("nm", object.getNm());
        writer.writeCompact("othr", object.getOthr());
        writer.writeCompact("pstlAdr", object.getPstlAdr());
    }

    @Override
    public FinancialInstitutionIdentification7 read(CompactReader reader) {
        FinancialInstitutionIdentification7 object = new FinancialInstitutionIdentification7();
        object.setBIC(reader.readString("bic"));
        object.setClrSysMmbId(reader.readCompact("clrSysMmbId"));
        object.setNm(reader.readString("nm"));
        object.setOthr(reader.readCompact("othr"));
        object.setPstlAdr(reader.readCompact("pstlAdr"));
        return object;
    }
}
