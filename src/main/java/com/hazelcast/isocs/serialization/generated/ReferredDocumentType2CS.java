package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ReferredDocumentType2CS extends GeneratedPain001CompactSerializer<ReferredDocumentType2> {
    public ReferredDocumentType2CS() {
        super(ReferredDocumentType2.class);
    }

    @Override
    public void write(CompactWriter writer, ReferredDocumentType2 object) {
        writer.writeCompact("cdOrPrtry", object.getCdOrPrtry());
        writer.writeString("issr", object.getIssr());
    }

    @Override
    public ReferredDocumentType2 read(CompactReader reader) {
        ReferredDocumentType2 object = new ReferredDocumentType2();
        object.setCdOrPrtry(reader.readCompact("cdOrPrtry"));
        object.setIssr(reader.readString("issr"));
        return object;
    }
}
