package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ReferredDocumentInformation3CS extends GeneratedPain001CompactSerializer<ReferredDocumentInformation3> {
    public ReferredDocumentInformation3CS() {
        super(ReferredDocumentInformation3.class);
    }

    @Override
    public void write(CompactWriter writer, ReferredDocumentInformation3 object) {
        writer.writeString("nb", object.getNb());
        writer.writeTimestampWithTimezone("rltdDt", object.getRltdDt());
        writer.writeCompact("tp", object.getTp());
    }

    @Override
    public ReferredDocumentInformation3 read(CompactReader reader) {
        ReferredDocumentInformation3 object = new ReferredDocumentInformation3();
        object.setNb(reader.readString("nb"));
        object.setRltdDt(reader.readTimestampWithTimezone("rltdDt"));
        object.setTp(reader.readCompact("tp"));
        return object;
    }
}
