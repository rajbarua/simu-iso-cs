package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class OrganisationIdentification4CS extends GeneratedPain001CompactSerializer<OrganisationIdentification4> {
    public OrganisationIdentification4CS() {
        super(OrganisationIdentification4.class);
    }

    @Override
    public void write(CompactWriter writer, OrganisationIdentification4 object) {
        writer.writeString("bicOrBEI", object.getBICOrBEI());
        writer.writeArrayOfCompact("othr", toArray(object.getOthr(), GenericOrganisationIdentification1[]::new));
    }

    @Override
    public OrganisationIdentification4 read(CompactReader reader) {
        OrganisationIdentification4 object = new OrganisationIdentification4();
        object.setBICOrBEI(reader.readString("bicOrBEI"));
        var values0 = reader.readArrayOfCompact("othr", GenericOrganisationIdentification1.class);
        if (values0 != null) object.getOthr().addAll(Arrays.asList(values0));
        return object;
    }
}
