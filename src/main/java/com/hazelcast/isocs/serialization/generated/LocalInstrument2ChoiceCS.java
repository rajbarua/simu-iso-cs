package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class LocalInstrument2ChoiceCS extends GeneratedPain001CompactSerializer<LocalInstrument2Choice> {
    public LocalInstrument2ChoiceCS() {
        super(LocalInstrument2Choice.class);
    }

    @Override
    public void write(CompactWriter writer, LocalInstrument2Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public LocalInstrument2Choice read(CompactReader reader) {
        LocalInstrument2Choice object = new LocalInstrument2Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
