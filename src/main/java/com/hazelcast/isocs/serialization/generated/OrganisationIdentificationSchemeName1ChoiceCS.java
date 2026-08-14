package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class OrganisationIdentificationSchemeName1ChoiceCS extends GeneratedPain001CompactSerializer<OrganisationIdentificationSchemeName1Choice> {
    public OrganisationIdentificationSchemeName1ChoiceCS() {
        super(OrganisationIdentificationSchemeName1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, OrganisationIdentificationSchemeName1Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public OrganisationIdentificationSchemeName1Choice read(CompactReader reader) {
        OrganisationIdentificationSchemeName1Choice object = new OrganisationIdentificationSchemeName1Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
