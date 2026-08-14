package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ReferredDocumentType1ChoiceCS extends GeneratedPain001CompactSerializer<ReferredDocumentType1Choice> {
    public ReferredDocumentType1ChoiceCS() {
        super(ReferredDocumentType1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, ReferredDocumentType1Choice object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public ReferredDocumentType1Choice read(CompactReader reader) {
        ReferredDocumentType1Choice object = new ReferredDocumentType1Choice();
        object.setCd(enumValue(reader.readString("cd"), DocumentType5Code::valueOf));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
