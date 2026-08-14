package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class GroupHeader32CS extends GeneratedPain001CompactSerializer<GroupHeader32> {
    public GroupHeader32CS() {
        super(GroupHeader32.class);
    }

    @Override
    public void write(CompactWriter writer, GroupHeader32 object) {
        writer.writeArrayOfCompact("authstn", toArray(object.getAuthstn(), Authorisation1Choice[]::new));
        writer.writeTimestampWithTimezone("creDtTm", object.getCreDtTm());
        writer.writeDecimal("ctrlSum", object.getCtrlSum());
        writer.writeCompact("fwdgAgt", object.getFwdgAgt());
        writer.writeCompact("initgPty", object.getInitgPty());
        writer.writeString("msgId", object.getMsgId());
        writer.writeString("nbOfTxs", object.getNbOfTxs());
    }

    @Override
    public GroupHeader32 read(CompactReader reader) {
        GroupHeader32 object = new GroupHeader32();
        var values0 = reader.readArrayOfCompact("authstn", Authorisation1Choice.class);
        if (values0 != null) object.getAuthstn().addAll(Arrays.asList(values0));
        object.setCreDtTm(reader.readTimestampWithTimezone("creDtTm"));
        object.setCtrlSum(reader.readDecimal("ctrlSum"));
        object.setFwdgAgt(reader.readCompact("fwdgAgt"));
        object.setInitgPty(reader.readCompact("initgPty"));
        object.setMsgId(reader.readString("msgId"));
        object.setNbOfTxs(reader.readString("nbOfTxs"));
        return object;
    }
}
