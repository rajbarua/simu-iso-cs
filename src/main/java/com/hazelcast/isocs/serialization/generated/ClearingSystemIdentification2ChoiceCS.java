package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ClearingSystemIdentification2ChoiceCS extends GeneratedPain001CompactSerializer<ClearingSystemIdentification2Choice> {
    public ClearingSystemIdentification2ChoiceCS() {
        super(ClearingSystemIdentification2Choice.class);
    }

    @Override
    public void write(CompactWriter writer, ClearingSystemIdentification2Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public ClearingSystemIdentification2Choice read(CompactReader reader) {
        ClearingSystemIdentification2Choice object = new ClearingSystemIdentification2Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
