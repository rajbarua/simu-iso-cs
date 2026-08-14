package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class Cheque6CS extends GeneratedPain001CompactSerializer<Cheque6> {
    public Cheque6CS() {
        super(Cheque6.class);
    }

    @Override
    public void write(CompactWriter writer, Cheque6 object) {
        writer.writeCompact("chqFr", object.getChqFr());
        writer.writeTimestampWithTimezone("chqMtrtyDt", object.getChqMtrtyDt());
        writer.writeString("chqNb", object.getChqNb());
        writer.writeString("chqTp", object.getChqTp() == null ? null : object.getChqTp().name());
        writer.writeCompact("dlvrTo", object.getDlvrTo());
        writer.writeCompact("dlvryMtd", object.getDlvryMtd());
        writer.writeString("frmsCd", object.getFrmsCd());
        writer.writeString("instrPrty", object.getInstrPrty() == null ? null : object.getInstrPrty().name());
        writer.writeArrayOfString("memoFld", toArray(object.getMemoFld(), String[]::new));
        writer.writeString("prtLctn", object.getPrtLctn());
        writer.writeString("rgnlClrZone", object.getRgnlClrZone());
    }

    @Override
    public Cheque6 read(CompactReader reader) {
        Cheque6 object = new Cheque6();
        object.setChqFr(reader.readCompact("chqFr"));
        object.setChqMtrtyDt(reader.readTimestampWithTimezone("chqMtrtyDt"));
        object.setChqNb(reader.readString("chqNb"));
        object.setChqTp(enumValue(reader.readString("chqTp"), ChequeType2Code::valueOf));
        object.setDlvrTo(reader.readCompact("dlvrTo"));
        object.setDlvryMtd(reader.readCompact("dlvryMtd"));
        object.setFrmsCd(reader.readString("frmsCd"));
        object.setInstrPrty(enumValue(reader.readString("instrPrty"), Priority2Code::valueOf));
        var values0 = reader.readArrayOfString("memoFld");
        if (values0 != null) object.getMemoFld().addAll(Arrays.asList(values0));
        object.setPrtLctn(reader.readString("prtLctn"));
        object.setRgnlClrZone(reader.readString("rgnlClrZone"));
        return object;
    }
}
