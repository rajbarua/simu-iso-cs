package com.hazelcast.isocs.serialization.generated;

import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.hz.demo.pmt.pain001_03.*;

import java.util.Arrays;

/** Generated typed Compact serializer. Do not edit manually. */
public final class CustomerCreditTransferInitiationV03CS extends GeneratedPain001CompactSerializer<CustomerCreditTransferInitiationV03> {
    public CustomerCreditTransferInitiationV03CS() {
        super(CustomerCreditTransferInitiationV03.class);
    }

    @Override
    public void write(CompactWriter writer, CustomerCreditTransferInitiationV03 object) {
        writer.writeCompact("grpHdr", object.getGrpHdr());
        writer.writeArrayOfCompact("pmtInf", toArray(object.getPmtInf(), PaymentInstructionInformation3[]::new));
    }

    @Override
    public CustomerCreditTransferInitiationV03 read(CompactReader reader) {
        CustomerCreditTransferInitiationV03 object = new CustomerCreditTransferInitiationV03();
        object.setGrpHdr(reader.readCompact("grpHdr"));
        var values0 = reader.readArrayOfCompact("pmtInf", PaymentInstructionInformation3.class);
        if (values0 != null) object.getPmtInf().addAll(Arrays.asList(values0));
        return object;
    }
}
