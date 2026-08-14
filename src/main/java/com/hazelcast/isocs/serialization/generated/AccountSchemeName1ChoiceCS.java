package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class AccountSchemeName1ChoiceCS extends GeneratedPain001CompactSerializer<AccountSchemeName1Choice> {
    public AccountSchemeName1ChoiceCS() {
        super(AccountSchemeName1Choice.class);
    }

    @Override
    public void write(CompactWriter writer, AccountSchemeName1Choice object) {
        writer.writeString("cd", object.getCd());
        writer.writeString("prtry", object.getPrtry());
    }

    @Override
    public AccountSchemeName1Choice read(CompactReader reader) {
        AccountSchemeName1Choice object = new AccountSchemeName1Choice();
        object.setCd(reader.readString("cd"));
        object.setPrtry(reader.readString("prtry"));
        return object;
    }
}
