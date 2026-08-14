package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class FinancialIdentificationSchemeName1ChoiceCS extends GeneratedPain001CompactSerializer<FinancialIdentificationSchemeName1Choice> {
    public FinancialIdentificationSchemeName1ChoiceCS() {
        super(FinancialIdentificationSchemeName1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, FinancialIdentificationSchemeName1Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public FinancialIdentificationSchemeName1Choice read(CompactReader reader) {
        FinancialIdentificationSchemeName1Choice object = new FinancialIdentificationSchemeName1Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
