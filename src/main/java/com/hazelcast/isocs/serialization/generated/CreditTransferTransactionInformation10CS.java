package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CreditTransferTransactionInformation10CS extends GeneratedPain001CompactSerializer<CreditTransferTransactionInformation10> {
    public CreditTransferTransactionInformation10CS() {
        super(CreditTransferTransactionInformation10.class);
    }

    @Override
    public void write(CompactWriter writer, CreditTransferTransactionInformation10 object) {
        writer.writeCompact("amt", object.getAmt());
        writer.writeCompact("cdtr", object.getCdtr());
        writer.writeCompact("cdtrAcct", object.getCdtrAcct());
        writer.writeCompact("cdtrAgt", object.getCdtrAgt());
        writer.writeCompact("cdtrAgtAcct", object.getCdtrAgtAcct());
        writer.writeCompact("chqInstr", object.getChqInstr());
        writer.writeString("chrgBr", object.getChrgBr() == null ? null : object.getChrgBr().name());
        writer.writeArrayOfCompact("instrForCdtrAgt", toArray(object.getInstrForCdtrAgt(), InstructionForCreditorAgent1[]::new));
        writer.writeString("instrForDbtrAgt", object.getInstrForDbtrAgt());
        writer.writeCompact("intrmyAgt1", object.getIntrmyAgt1());
        writer.writeCompact("intrmyAgt1Acct", object.getIntrmyAgt1Acct());
        writer.writeCompact("intrmyAgt2", object.getIntrmyAgt2());
        writer.writeCompact("intrmyAgt2Acct", object.getIntrmyAgt2Acct());
        writer.writeCompact("intrmyAgt3", object.getIntrmyAgt3());
        writer.writeCompact("intrmyAgt3Acct", object.getIntrmyAgt3Acct());
        writer.writeCompact("pmtId", object.getPmtId());
        writer.writeCompact("pmtTpInf", object.getPmtTpInf());
        writer.writeCompact("purp", object.getPurp());
        writer.writeArrayOfCompact("rgltryRptg", toArray(object.getRgltryRptg(), RegulatoryReporting3[]::new));
        writer.writeArrayOfCompact("rltdRmtInf", toArray(object.getRltdRmtInf(), RemittanceLocation2[]::new));
        writer.writeCompact("rmtInf", object.getRmtInf());
        writer.writeCompact("tax", object.getTax());
        writer.writeCompact("ultmtCdtr", object.getUltmtCdtr());
        writer.writeCompact("ultmtDbtr", object.getUltmtDbtr());
        writer.writeCompact("xchgRateInf", object.getXchgRateInf());
    }

    @Override
    public CreditTransferTransactionInformation10 read(CompactReader reader) {
        CreditTransferTransactionInformation10 object = new CreditTransferTransactionInformation10();
        object.setAmt(reader.readCompact("amt"));
        object.setCdtr(reader.readCompact("cdtr"));
        object.setCdtrAcct(reader.readCompact("cdtrAcct"));
        object.setCdtrAgt(reader.readCompact("cdtrAgt"));
        object.setCdtrAgtAcct(reader.readCompact("cdtrAgtAcct"));
        object.setChqInstr(reader.readCompact("chqInstr"));
        object.setChrgBr(enumValue(reader.readString("chrgBr"), ChargeBearerType1Code::valueOf));
        var values0 = reader.readArrayOfCompact("instrForCdtrAgt", InstructionForCreditorAgent1.class);
        if (values0 != null) object.getInstrForCdtrAgt().addAll(Arrays.asList(values0));
        object.setInstrForDbtrAgt(reader.readString("instrForDbtrAgt"));
        object.setIntrmyAgt1(reader.readCompact("intrmyAgt1"));
        object.setIntrmyAgt1Acct(reader.readCompact("intrmyAgt1Acct"));
        object.setIntrmyAgt2(reader.readCompact("intrmyAgt2"));
        object.setIntrmyAgt2Acct(reader.readCompact("intrmyAgt2Acct"));
        object.setIntrmyAgt3(reader.readCompact("intrmyAgt3"));
        object.setIntrmyAgt3Acct(reader.readCompact("intrmyAgt3Acct"));
        object.setPmtId(reader.readCompact("pmtId"));
        object.setPmtTpInf(reader.readCompact("pmtTpInf"));
        object.setPurp(reader.readCompact("purp"));
        var values1 = reader.readArrayOfCompact("rgltryRptg", RegulatoryReporting3.class);
        if (values1 != null) object.getRgltryRptg().addAll(Arrays.asList(values1));
        var values2 = reader.readArrayOfCompact("rltdRmtInf", RemittanceLocation2.class);
        if (values2 != null) object.getRltdRmtInf().addAll(Arrays.asList(values2));
        object.setRmtInf(reader.readCompact("rmtInf"));
        object.setTax(reader.readCompact("tax"));
        object.setUltmtCdtr(reader.readCompact("ultmtCdtr"));
        object.setUltmtDbtr(reader.readCompact("ultmtDbtr"));
        object.setXchgRateInf(reader.readCompact("xchgRateInf"));
        return object;
    }
}
