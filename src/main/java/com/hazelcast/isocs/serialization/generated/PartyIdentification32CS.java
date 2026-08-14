package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PartyIdentification32CS extends GeneratedPain001CompactSerializer<PartyIdentification32> {
    public PartyIdentification32CS() {
        super(PartyIdentification32.class);
    }

    @Override
    public void write(CompactWriter writer, PartyIdentification32 object) {
        writer.writeCompact("ctctDtls", object.getCtctDtls());
        writer.writeString("ctryOfRes", object.getCtryOfRes());
        writer.writeCompact("id", object.getId());
        writer.writeString("nm", object.getNm());
        writer.writeCompact("pstlAdr", object.getPstlAdr());
    }

    @Override
    public PartyIdentification32 read(CompactReader reader) {
        PartyIdentification32 object = new PartyIdentification32();
        object.setCtctDtls(reader.readCompact("ctctDtls"));
        object.setCtryOfRes(reader.readString("ctryOfRes"));
        object.setId(reader.readCompact("id"));
        object.setNm(reader.readString("nm"));
        object.setPstlAdr(reader.readCompact("pstlAdr"));
        return object;
    }
}
