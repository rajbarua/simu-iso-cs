package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class InstructionForCreditorAgent1CS extends GeneratedPain001CompactSerializer<InstructionForCreditorAgent1> {
    public InstructionForCreditorAgent1CS() {
        super(InstructionForCreditorAgent1.class);
    }

    @Override
    public void write(CompactWriter writer, InstructionForCreditorAgent1 object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("instrInf", object.getInstrInf());
    }

    @Override
    public InstructionForCreditorAgent1 read(CompactReader reader) {
        InstructionForCreditorAgent1 object = new InstructionForCreditorAgent1();
        object.setCd(enumValue(reader.readString("cd"), Instruction3Code::valueOf));
        object.setInstrInf(reader.readString("instrInf"));
        return object;
    }
}
