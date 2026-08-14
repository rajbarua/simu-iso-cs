package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ChequeDeliveryMethod1ChoiceCS extends GeneratedPain001CompactSerializer<ChequeDeliveryMethod1Choice> {
    public ChequeDeliveryMethod1ChoiceCS() {
        super(ChequeDeliveryMethod1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, ChequeDeliveryMethod1Choice object) {
        writer.writeString("cd", object.getCd() == null ? null : object.getCd().name());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public ChequeDeliveryMethod1Choice read(CompactReader reader) {
        ChequeDeliveryMethod1Choice object = new ChequeDeliveryMethod1Choice();
        object.setCd(enumValue(reader.readString("cd"), ChequeDelivery1Code::valueOf));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
