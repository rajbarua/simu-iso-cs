package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class AccountIdentification4ChoiceCS extends GeneratedPain001CompactSerializer<AccountIdentification4Choice> {
    public AccountIdentification4ChoiceCS() {
        super(AccountIdentification4Choice.class);
    }

    @Override
    public void write(CompactWriter writer, AccountIdentification4Choice object) {
        writer.writeString("iban", object.getIBAN());
        writer.writeCompact("othr", object.getOthr());
    }

    @Override
    public AccountIdentification4Choice read(CompactReader reader) {
        AccountIdentification4Choice object = new AccountIdentification4Choice();
        object.setIBAN(reader.readString("iban"));
        object.setOthr(reader.readCompact("othr"));
        return object;
    }
}
