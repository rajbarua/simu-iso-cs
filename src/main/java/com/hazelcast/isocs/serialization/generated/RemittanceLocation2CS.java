package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class RemittanceLocation2CS extends GeneratedPain001CompactSerializer<RemittanceLocation2> {
    public RemittanceLocation2CS() {
        super(RemittanceLocation2.class);
    }

    @Override
    public void write(CompactWriter writer, RemittanceLocation2 object) {
        writer.writeString("rmtId", object.getRmtId());
        writer.writeString("rmtLctnElctrncAdr", object.getRmtLctnElctrncAdr());
        writer.writeString("rmtLctnMtd", object.getRmtLctnMtd() == null ? null : object.getRmtLctnMtd().name());
        writer.writeCompact("rmtLctnPstlAdr", object.getRmtLctnPstlAdr());
    }

    @Override
    public RemittanceLocation2 read(CompactReader reader) {
        RemittanceLocation2 object = new RemittanceLocation2();
        object.setRmtId(reader.readString("rmtId"));
        object.setRmtLctnElctrncAdr(reader.readString("rmtLctnElctrncAdr"));
        object.setRmtLctnMtd(enumValue(reader.readString("rmtLctnMtd"), RemittanceLocationMethod2Code::valueOf));
        object.setRmtLctnPstlAdr(reader.readCompact("rmtLctnPstlAdr"));
        return object;
    }
}
