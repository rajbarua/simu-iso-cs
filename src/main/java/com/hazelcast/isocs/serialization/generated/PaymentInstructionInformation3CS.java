package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class PaymentInstructionInformation3CS extends GeneratedPain001CompactSerializer<PaymentInstructionInformation3> {
    public PaymentInstructionInformation3CS() {
        super(PaymentInstructionInformation3.class);
    }

    @Override
    public void write(CompactWriter writer, PaymentInstructionInformation3 object) {
        writer.writeNullableBoolean("btchBookg", object.isBtchBookg());
        writer.writeArrayOfCompact("cdtTrfTxInf", toArray(object.getCdtTrfTxInf(), CreditTransferTransactionInformation10[]::new));
        writer.writeString("chrgBr", object.getChrgBr() == null ? null : object.getChrgBr().name());
        writer.writeCompact("chrgsAcct", object.getChrgsAcct());
        writer.writeCompact("chrgsAcctAgt", object.getChrgsAcctAgt());
        writer.writeDecimal("ctrlSum", object.getCtrlSum());
        writer.writeCompact("dbtr", object.getDbtr());
        writer.writeCompact("dbtrAcct", object.getDbtrAcct());
        writer.writeCompact("dbtrAgt", object.getDbtrAgt());
        writer.writeCompact("dbtrAgtAcct", object.getDbtrAgtAcct());
        writer.writeString("nbOfTxs", object.getNbOfTxs());
        writer.writeString("pmtInfId", object.getPmtInfId());
        writer.writeString("pmtMtd", object.getPmtMtd() == null ? null : object.getPmtMtd().name());
        writer.writeCompact("pmtTpInf", object.getPmtTpInf());
        writer.writeTimestampWithTimezone("poolgAdjstmntDt", object.getPoolgAdjstmntDt());
        writer.writeTimestampWithTimezone("reqdExctnDt", object.getReqdExctnDt());
        writer.writeCompact("ultmtDbtr", object.getUltmtDbtr());
    }

    @Override
    public PaymentInstructionInformation3 read(CompactReader reader) {
        PaymentInstructionInformation3 object = new PaymentInstructionInformation3();
        object.setBtchBookg(reader.readNullableBoolean("btchBookg"));
        var values0 = reader.readArrayOfCompact("cdtTrfTxInf", CreditTransferTransactionInformation10.class);
        if (values0 != null) object.getCdtTrfTxInf().addAll(Arrays.asList(values0));
        object.setChrgBr(enumValue(reader.readString("chrgBr"), ChargeBearerType1Code::valueOf));
        object.setChrgsAcct(reader.readCompact("chrgsAcct"));
        object.setChrgsAcctAgt(reader.readCompact("chrgsAcctAgt"));
        object.setCtrlSum(reader.readDecimal("ctrlSum"));
        object.setDbtr(reader.readCompact("dbtr"));
        object.setDbtrAcct(reader.readCompact("dbtrAcct"));
        object.setDbtrAgt(reader.readCompact("dbtrAgt"));
        object.setDbtrAgtAcct(reader.readCompact("dbtrAgtAcct"));
        object.setNbOfTxs(reader.readString("nbOfTxs"));
        object.setPmtInfId(reader.readString("pmtInfId"));
        object.setPmtMtd(enumValue(reader.readString("pmtMtd"), PaymentMethod3Code::valueOf));
        object.setPmtTpInf(reader.readCompact("pmtTpInf"));
        object.setPoolgAdjstmntDt(reader.readTimestampWithTimezone("poolgAdjstmntDt"));
        object.setReqdExctnDt(reader.readTimestampWithTimezone("reqdExctnDt"));
        object.setUltmtDbtr(reader.readCompact("ultmtDbtr"));
        return object;
    }
}
