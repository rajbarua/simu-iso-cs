package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class RemittanceInformation5CS extends GeneratedPain001CompactSerializer<RemittanceInformation5> {
    public RemittanceInformation5CS() {
        super(RemittanceInformation5.class);
    }

    @Override
    public void write(CompactWriter writer, RemittanceInformation5 object) {
        writer.writeArrayOfCompact("strd", toArray(object.getStrd(), StructuredRemittanceInformation7[]::new));
        writer.writeArrayOfString("ustrd", toArray(object.getUstrd(), String[]::new));
    }

    @Override
    public RemittanceInformation5 read(CompactReader reader) {
        RemittanceInformation5 object = new RemittanceInformation5();
        var values0 = reader.readArrayOfCompact("strd", StructuredRemittanceInformation7.class);
        if (values0 != null) object.getStrd().addAll(Arrays.asList(values0));
        var values1 = reader.readArrayOfString("ustrd");
        if (values1 != null) object.getUstrd().addAll(Arrays.asList(values1));
        return object;
    }
}
