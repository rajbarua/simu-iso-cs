package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PostalAddress6CS extends GeneratedPain001CompactSerializer<PostalAddress6> {
    public PostalAddress6CS() {
        super(PostalAddress6.class);
    }

    @Override
    public void write(CompactWriter writer, PostalAddress6 object) {
        writer.writeArrayOfString("adrLine", toArray(object.getAdrLine(), String[]::new));
        writer.writeString("adrTp", object.getAdrTp() == null ? null : object.getAdrTp().name());
        writer.writeString("bldgNb", object.getBldgNb());
        writer.writeString("ctry", object.getCtry());
        writer.writeString("ctrySubDvsn", object.getCtrySubDvsn());
        writer.writeString("dept", object.getDept());
        writer.writeString("pstCd", object.getPstCd());
        writer.writeString("strtNm", object.getStrtNm());
        writer.writeString("subDept", object.getSubDept());
        writer.writeString("twnNm", object.getTwnNm());
    }

    @Override
    public PostalAddress6 read(CompactReader reader) {
        PostalAddress6 object = new PostalAddress6();
        var values0 = reader.readArrayOfString("adrLine");
        if (values0 != null) object.getAdrLine().addAll(Arrays.asList(values0));
        object.setAdrTp(enumValue(reader.readString("adrTp"), AddressType2Code::valueOf));
        object.setBldgNb(reader.readString("bldgNb"));
        object.setCtry(reader.readString("ctry"));
        object.setCtrySubDvsn(reader.readString("ctrySubDvsn"));
        object.setDept(reader.readString("dept"));
        object.setPstCd(reader.readString("pstCd"));
        object.setStrtNm(reader.readString("strtNm"));
        object.setSubDept(reader.readString("subDept"));
        object.setTwnNm(reader.readString("twnNm"));
        return object;
    }
}
