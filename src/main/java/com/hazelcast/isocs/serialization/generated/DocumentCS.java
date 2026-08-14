package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class DocumentCS extends GeneratedPain001CompactSerializer<Document> {
    public DocumentCS() {
        super(Document.class);
    }

    @Override
    public void write(CompactWriter writer, Document object) {
        writer.writeCompact("cstmrCdtTrfInitn", object.getCstmrCdtTrfInitn());
    }

    @Override
    public Document read(CompactReader reader) {
        Document object = new Document();
        object.setCstmrCdtTrfInitn(reader.readCompact("cstmrCdtTrfInitn"));
        return object;
    }
}
