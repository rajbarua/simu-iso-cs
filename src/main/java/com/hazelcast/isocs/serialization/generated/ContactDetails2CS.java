package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class ContactDetails2CS extends GeneratedPain001CompactSerializer<ContactDetails2> {
    public ContactDetails2CS() {
        super(ContactDetails2.class);
    }

    @Override
    public void write(CompactWriter writer, ContactDetails2 object) {
        writer.writeString("emailAdr", object.getEmailAdr());
        writer.writeString("faxNb", object.getFaxNb());
        writer.writeString("mobNb", object.getMobNb());
        writer.writeString("nm", object.getNm());
        writer.writeString("nmPrfx", object.getNmPrfx() == null ? null : object.getNmPrfx().name());
        writer.writeString("othr", object.getOthr());
        writer.writeString("phneNb", object.getPhneNb());
    }

    @Override
    public ContactDetails2 read(CompactReader reader) {
        ContactDetails2 object = new ContactDetails2();
        object.setEmailAdr(reader.readString("emailAdr"));
        object.setFaxNb(reader.readString("faxNb"));
        object.setMobNb(reader.readString("mobNb"));
        object.setNm(reader.readString("nm"));
        object.setNmPrfx(enumValue(reader.readString("nmPrfx"), NamePrefix1Code::valueOf));
        object.setOthr(reader.readString("othr"));
        object.setPhneNb(reader.readString("phneNb"));
        return object;
    }
}
