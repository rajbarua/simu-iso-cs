package com.hazelcast.isocs.serialization;

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.isocs.serialization.generated.*;
import com.hazelcast.nio.serialization.compact.CompactSerializer;

/** All generated, non-reflective serializers for the pain.001.001.03 object model. */
public final class Pain001ExplicitCompactSerializers {
    private Pain001ExplicitCompactSerializers() {
    }

    public static CompactSerializer<?>[] all() {
        return new CompactSerializer<?>[]{
            new AccountIdentification4ChoiceCS(),
            new AccountSchemeName1ChoiceCS(),
            new ActiveOrHistoricCurrencyAndAmountCS(),
            new AmountType3ChoiceCS(),
            new Authorisation1ChoiceCS(),
            new BranchAndFinancialInstitutionIdentification4CS(),
            new BranchData2CS(),
            new CashAccount16CS(),
            new CashAccountType2CS(),
            new CategoryPurpose1ChoiceCS(),
            new Cheque6CS(),
            new ChequeDeliveryMethod1ChoiceCS(),
            new ClearingSystemIdentification2ChoiceCS(),
            new ClearingSystemMemberIdentification2CS(),
            new ContactDetails2CS(),
            new CreditTransferTransactionInformation10CS(),
            new CreditorReferenceInformation2CS(),
            new CreditorReferenceType1ChoiceCS(),
            new CreditorReferenceType2CS(),
            new CustomerCreditTransferInitiationV03CS(),
            new DateAndPlaceOfBirthCS(),
            new DatePeriodDetailsCS(),
            new DocumentCS(),
            new DocumentAdjustment1CS(),
            new EquivalentAmount2CS(),
            new ExchangeRateInformation1CS(),
            new FinancialIdentificationSchemeName1ChoiceCS(),
            new FinancialInstitutionIdentification7CS(),
            new GenericAccountIdentification1CS(),
            new GenericFinancialIdentification1CS(),
            new GenericOrganisationIdentification1CS(),
            new GenericPersonIdentification1CS(),
            new GroupHeader32CS(),
            new InstructionForCreditorAgent1CS(),
            new LocalInstrument2ChoiceCS(),
            new NameAndAddress10CS(),
            new OrganisationIdentification4CS(),
            new OrganisationIdentificationSchemeName1ChoiceCS(),
            new Party6ChoiceCS(),
            new PartyIdentification32CS(),
            new PaymentIdentification1CS(),
            new PaymentInstructionInformation3CS(),
            new PaymentTypeInformation19CS(),
            new PersonIdentification5CS(),
            new PersonIdentificationSchemeName1ChoiceCS(),
            new PostalAddress6CS(),
            new Purpose2ChoiceCS(),
            new ReferredDocumentInformation3CS(),
            new ReferredDocumentType1ChoiceCS(),
            new ReferredDocumentType2CS(),
            new RegulatoryAuthority2CS(),
            new RegulatoryReporting3CS(),
            new RemittanceAmount1CS(),
            new RemittanceInformation5CS(),
            new RemittanceLocation2CS(),
            new ServiceLevel8ChoiceCS(),
            new StructuredRegulatoryReporting3CS(),
            new StructuredRemittanceInformation7CS(),
            new TaxAmount1CS(),
            new TaxAuthorisation1CS(),
            new TaxInformation3CS(),
            new TaxParty1CS(),
            new TaxParty2CS(),
            new TaxPeriod1CS(),
            new TaxRecord1CS(),
            new TaxRecordDetails1CS()
        };
    }

    public static void register(CompactSerializationConfig config) {
        for (CompactSerializer<?> serializer : all()) {
            config.addSerializer(serializer);
        }
    }
}
