package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class RegulatoryReporting3CS extends GeneratedPain001CompactSerializer<RegulatoryReporting3> {
    public RegulatoryReporting3CS() {
        super(RegulatoryReporting3.class);
    }

    @Override
    public void write(CompactWriter writer, RegulatoryReporting3 object) {
        writer.writeCompact("authrty", object.getAuthrty());
        writer.writeString("dbtCdtRptgInd", object.getDbtCdtRptgInd() == null ? null : object.getDbtCdtRptgInd().name());
        writer.writeArrayOfCompact("dtls", toArray(object.getDtls(), StructuredRegulatoryReporting3[]::new));
    }

    @Override
    public RegulatoryReporting3 read(CompactReader reader) {
        RegulatoryReporting3 object = new RegulatoryReporting3();
        object.setAuthrty(reader.readCompact("authrty"));
        object.setDbtCdtRptgInd(enumValue(reader.readString("dbtCdtRptgInd"), RegulatoryReportingType1Code::valueOf));
        var values0 = reader.readArrayOfCompact("dtls", StructuredRegulatoryReporting3.class);
        if (values0 != null) object.getDtls().addAll(Arrays.asList(values0));
        return object;
    }
}
