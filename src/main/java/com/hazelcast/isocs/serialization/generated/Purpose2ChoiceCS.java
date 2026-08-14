package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class Purpose2ChoiceCS extends GeneratedPain001CompactSerializer<Purpose2Choice> {
    public Purpose2ChoiceCS() {
        super(Purpose2Choice.class);
    }

    @Override
    public void write(CompactWriter writer, Purpose2Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public Purpose2Choice read(CompactReader reader) {
        Purpose2Choice object = new Purpose2Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
