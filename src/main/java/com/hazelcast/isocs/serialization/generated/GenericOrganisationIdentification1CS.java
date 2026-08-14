package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class GenericOrganisationIdentification1CS extends GeneratedPain001CompactSerializer<GenericOrganisationIdentification1> {
    public GenericOrganisationIdentification1CS() {
        super(GenericOrganisationIdentification1.class);
    }

    @Override
    public void write(CompactWriter writer, GenericOrganisationIdentification1 object) {
        writer.writeString("id", object.getId());
        writer.writeString("issr", object.getIssr());
        writer.writeCompact("schmeNm", object.getSchmeNm());
    }

    @Override
    public GenericOrganisationIdentification1 read(CompactReader reader) {
        GenericOrganisationIdentification1 object = new GenericOrganisationIdentification1();
        object.setId(reader.readString("id"));
        object.setIssr(reader.readString("issr"));
        object.setSchmeNm(reader.readCompact("schmeNm"));
        return object;
    }
}
