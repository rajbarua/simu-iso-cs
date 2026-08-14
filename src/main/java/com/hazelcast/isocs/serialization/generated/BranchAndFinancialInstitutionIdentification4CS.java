package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class BranchAndFinancialInstitutionIdentification4CS extends GeneratedPain001CompactSerializer<BranchAndFinancialInstitutionIdentification4> {
    public BranchAndFinancialInstitutionIdentification4CS() {
        super(BranchAndFinancialInstitutionIdentification4.class);
    }

    @Override
    public void write(CompactWriter writer, BranchAndFinancialInstitutionIdentification4 object) {
        writer.writeCompact("brnchId", object.getBrnchId());
        writer.writeCompact("finInstnId", object.getFinInstnId());
    }

    @Override
    public BranchAndFinancialInstitutionIdentification4 read(CompactReader reader) {
        BranchAndFinancialInstitutionIdentification4 object = new BranchAndFinancialInstitutionIdentification4();
        object.setBrnchId(reader.readCompact("brnchId"));
        object.setFinInstnId(reader.readCompact("finInstnId"));
        return object;
    }
}
