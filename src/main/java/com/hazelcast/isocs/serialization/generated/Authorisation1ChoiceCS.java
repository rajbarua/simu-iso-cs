package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class Authorisation1ChoiceCS extends GeneratedPain001CompactSerializer<Authorisation1Choice> {
    public Authorisation1ChoiceCS() {
        super(Authorisation1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, Authorisation1Choice object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public Authorisation1Choice read(CompactReader reader) {
        Authorisation1Choice object = new Authorisation1Choice();
        object.setCd(enumValue(reader.readString("cd"), Authorisation1Code::valueOf));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
