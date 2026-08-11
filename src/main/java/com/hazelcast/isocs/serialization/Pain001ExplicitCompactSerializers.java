package com.hazelcast.isocs.serialization;

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hz.demo.pmt.pain001_03.*;

/** All explicit serializers for the generated pain.001.001.03 object model. */
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

    public static final class AccountIdentification4ChoiceCS extends ReflectivePain001CompactSerializer<AccountIdentification4Choice> {
        public AccountIdentification4ChoiceCS() { super(AccountIdentification4Choice.class); }
    }

    public static final class AccountSchemeName1ChoiceCS extends ReflectivePain001CompactSerializer<AccountSchemeName1Choice> {
        public AccountSchemeName1ChoiceCS() { super(AccountSchemeName1Choice.class); }
    }

    public static final class ActiveOrHistoricCurrencyAndAmountCS extends ReflectivePain001CompactSerializer<ActiveOrHistoricCurrencyAndAmount> {
        public ActiveOrHistoricCurrencyAndAmountCS() { super(ActiveOrHistoricCurrencyAndAmount.class); }
    }

    public static final class AmountType3ChoiceCS extends ReflectivePain001CompactSerializer<AmountType3Choice> {
        public AmountType3ChoiceCS() { super(AmountType3Choice.class); }
    }

    public static final class Authorisation1ChoiceCS extends ReflectivePain001CompactSerializer<Authorisation1Choice> {
        public Authorisation1ChoiceCS() { super(Authorisation1Choice.class); }
    }

    public static final class BranchAndFinancialInstitutionIdentification4CS extends ReflectivePain001CompactSerializer<BranchAndFinancialInstitutionIdentification4> {
        public BranchAndFinancialInstitutionIdentification4CS() { super(BranchAndFinancialInstitutionIdentification4.class); }
    }

    public static final class BranchData2CS extends ReflectivePain001CompactSerializer<BranchData2> {
        public BranchData2CS() { super(BranchData2.class); }
    }

    public static final class CashAccount16CS extends ReflectivePain001CompactSerializer<CashAccount16> {
        public CashAccount16CS() { super(CashAccount16.class); }
    }

    public static final class CashAccountType2CS extends ReflectivePain001CompactSerializer<CashAccountType2> {
        public CashAccountType2CS() { super(CashAccountType2.class); }
    }

    public static final class CategoryPurpose1ChoiceCS extends ReflectivePain001CompactSerializer<CategoryPurpose1Choice> {
        public CategoryPurpose1ChoiceCS() { super(CategoryPurpose1Choice.class); }
    }

    public static final class Cheque6CS extends ReflectivePain001CompactSerializer<Cheque6> {
        public Cheque6CS() { super(Cheque6.class); }
    }

    public static final class ChequeDeliveryMethod1ChoiceCS extends ReflectivePain001CompactSerializer<ChequeDeliveryMethod1Choice> {
        public ChequeDeliveryMethod1ChoiceCS() { super(ChequeDeliveryMethod1Choice.class); }
    }

    public static final class ClearingSystemIdentification2ChoiceCS extends ReflectivePain001CompactSerializer<ClearingSystemIdentification2Choice> {
        public ClearingSystemIdentification2ChoiceCS() { super(ClearingSystemIdentification2Choice.class); }
    }

    public static final class ClearingSystemMemberIdentification2CS extends ReflectivePain001CompactSerializer<ClearingSystemMemberIdentification2> {
        public ClearingSystemMemberIdentification2CS() { super(ClearingSystemMemberIdentification2.class); }
    }

    public static final class ContactDetails2CS extends ReflectivePain001CompactSerializer<ContactDetails2> {
        public ContactDetails2CS() { super(ContactDetails2.class); }
    }

    public static final class CreditTransferTransactionInformation10CS extends ReflectivePain001CompactSerializer<CreditTransferTransactionInformation10> {
        public CreditTransferTransactionInformation10CS() { super(CreditTransferTransactionInformation10.class); }
    }

    public static final class CreditorReferenceInformation2CS extends ReflectivePain001CompactSerializer<CreditorReferenceInformation2> {
        public CreditorReferenceInformation2CS() { super(CreditorReferenceInformation2.class); }
    }

    public static final class CreditorReferenceType1ChoiceCS extends ReflectivePain001CompactSerializer<CreditorReferenceType1Choice> {
        public CreditorReferenceType1ChoiceCS() { super(CreditorReferenceType1Choice.class); }
    }

    public static final class CreditorReferenceType2CS extends ReflectivePain001CompactSerializer<CreditorReferenceType2> {
        public CreditorReferenceType2CS() { super(CreditorReferenceType2.class); }
    }

    public static final class CustomerCreditTransferInitiationV03CS extends ReflectivePain001CompactSerializer<CustomerCreditTransferInitiationV03> {
        public CustomerCreditTransferInitiationV03CS() { super(CustomerCreditTransferInitiationV03.class); }
    }

    public static final class DateAndPlaceOfBirthCS extends ReflectivePain001CompactSerializer<DateAndPlaceOfBirth> {
        public DateAndPlaceOfBirthCS() { super(DateAndPlaceOfBirth.class); }
    }

    public static final class DatePeriodDetailsCS extends ReflectivePain001CompactSerializer<DatePeriodDetails> {
        public DatePeriodDetailsCS() { super(DatePeriodDetails.class); }
    }

    public static final class DocumentCS extends ReflectivePain001CompactSerializer<Document> {
        public DocumentCS() { super(Document.class); }
    }

    public static final class DocumentAdjustment1CS extends ReflectivePain001CompactSerializer<DocumentAdjustment1> {
        public DocumentAdjustment1CS() { super(DocumentAdjustment1.class); }
    }

    public static final class EquivalentAmount2CS extends ReflectivePain001CompactSerializer<EquivalentAmount2> {
        public EquivalentAmount2CS() { super(EquivalentAmount2.class); }
    }

    public static final class ExchangeRateInformation1CS extends ReflectivePain001CompactSerializer<ExchangeRateInformation1> {
        public ExchangeRateInformation1CS() { super(ExchangeRateInformation1.class); }
    }

    public static final class FinancialIdentificationSchemeName1ChoiceCS extends ReflectivePain001CompactSerializer<FinancialIdentificationSchemeName1Choice> {
        public FinancialIdentificationSchemeName1ChoiceCS() { super(FinancialIdentificationSchemeName1Choice.class); }
    }

    public static final class FinancialInstitutionIdentification7CS extends ReflectivePain001CompactSerializer<FinancialInstitutionIdentification7> {
        public FinancialInstitutionIdentification7CS() { super(FinancialInstitutionIdentification7.class); }
    }

    public static final class GenericAccountIdentification1CS extends ReflectivePain001CompactSerializer<GenericAccountIdentification1> {
        public GenericAccountIdentification1CS() { super(GenericAccountIdentification1.class); }
    }

    public static final class GenericFinancialIdentification1CS extends ReflectivePain001CompactSerializer<GenericFinancialIdentification1> {
        public GenericFinancialIdentification1CS() { super(GenericFinancialIdentification1.class); }
    }

    public static final class GenericOrganisationIdentification1CS extends ReflectivePain001CompactSerializer<GenericOrganisationIdentification1> {
        public GenericOrganisationIdentification1CS() { super(GenericOrganisationIdentification1.class); }
    }

    public static final class GenericPersonIdentification1CS extends ReflectivePain001CompactSerializer<GenericPersonIdentification1> {
        public GenericPersonIdentification1CS() { super(GenericPersonIdentification1.class); }
    }

    public static final class GroupHeader32CS extends ReflectivePain001CompactSerializer<GroupHeader32> {
        public GroupHeader32CS() { super(GroupHeader32.class); }
    }

    public static final class InstructionForCreditorAgent1CS extends ReflectivePain001CompactSerializer<InstructionForCreditorAgent1> {
        public InstructionForCreditorAgent1CS() { super(InstructionForCreditorAgent1.class); }
    }

    public static final class LocalInstrument2ChoiceCS extends ReflectivePain001CompactSerializer<LocalInstrument2Choice> {
        public LocalInstrument2ChoiceCS() { super(LocalInstrument2Choice.class); }
    }

    public static final class NameAndAddress10CS extends ReflectivePain001CompactSerializer<NameAndAddress10> {
        public NameAndAddress10CS() { super(NameAndAddress10.class); }
    }

    public static final class OrganisationIdentification4CS extends ReflectivePain001CompactSerializer<OrganisationIdentification4> {
        public OrganisationIdentification4CS() { super(OrganisationIdentification4.class); }
    }

    public static final class OrganisationIdentificationSchemeName1ChoiceCS extends ReflectivePain001CompactSerializer<OrganisationIdentificationSchemeName1Choice> {
        public OrganisationIdentificationSchemeName1ChoiceCS() { super(OrganisationIdentificationSchemeName1Choice.class); }
    }

    public static final class Party6ChoiceCS extends ReflectivePain001CompactSerializer<Party6Choice> {
        public Party6ChoiceCS() { super(Party6Choice.class); }
    }

    public static final class PartyIdentification32CS extends ReflectivePain001CompactSerializer<PartyIdentification32> {
        public PartyIdentification32CS() { super(PartyIdentification32.class); }
    }

    public static final class PaymentIdentification1CS extends ReflectivePain001CompactSerializer<PaymentIdentification1> {
        public PaymentIdentification1CS() { super(PaymentIdentification1.class); }
    }

    public static final class PaymentInstructionInformation3CS extends ReflectivePain001CompactSerializer<PaymentInstructionInformation3> {
        public PaymentInstructionInformation3CS() { super(PaymentInstructionInformation3.class); }
    }

    public static final class PaymentTypeInformation19CS extends ReflectivePain001CompactSerializer<PaymentTypeInformation19> {
        public PaymentTypeInformation19CS() { super(PaymentTypeInformation19.class); }
    }

    public static final class PersonIdentification5CS extends ReflectivePain001CompactSerializer<PersonIdentification5> {
        public PersonIdentification5CS() { super(PersonIdentification5.class); }
    }

    public static final class PersonIdentificationSchemeName1ChoiceCS extends ReflectivePain001CompactSerializer<PersonIdentificationSchemeName1Choice> {
        public PersonIdentificationSchemeName1ChoiceCS() { super(PersonIdentificationSchemeName1Choice.class); }
    }

    public static final class PostalAddress6CS extends ReflectivePain001CompactSerializer<PostalAddress6> {
        public PostalAddress6CS() { super(PostalAddress6.class); }
    }

    public static final class Purpose2ChoiceCS extends ReflectivePain001CompactSerializer<Purpose2Choice> {
        public Purpose2ChoiceCS() { super(Purpose2Choice.class); }
    }

    public static final class ReferredDocumentInformation3CS extends ReflectivePain001CompactSerializer<ReferredDocumentInformation3> {
        public ReferredDocumentInformation3CS() { super(ReferredDocumentInformation3.class); }
    }

    public static final class ReferredDocumentType1ChoiceCS extends ReflectivePain001CompactSerializer<ReferredDocumentType1Choice> {
        public ReferredDocumentType1ChoiceCS() { super(ReferredDocumentType1Choice.class); }
    }

    public static final class ReferredDocumentType2CS extends ReflectivePain001CompactSerializer<ReferredDocumentType2> {
        public ReferredDocumentType2CS() { super(ReferredDocumentType2.class); }
    }

    public static final class RegulatoryAuthority2CS extends ReflectivePain001CompactSerializer<RegulatoryAuthority2> {
        public RegulatoryAuthority2CS() { super(RegulatoryAuthority2.class); }
    }

    public static final class RegulatoryReporting3CS extends ReflectivePain001CompactSerializer<RegulatoryReporting3> {
        public RegulatoryReporting3CS() { super(RegulatoryReporting3.class); }
    }

    public static final class RemittanceAmount1CS extends ReflectivePain001CompactSerializer<RemittanceAmount1> {
        public RemittanceAmount1CS() { super(RemittanceAmount1.class); }
    }

    public static final class RemittanceInformation5CS extends ReflectivePain001CompactSerializer<RemittanceInformation5> {
        public RemittanceInformation5CS() { super(RemittanceInformation5.class); }
    }

    public static final class RemittanceLocation2CS extends ReflectivePain001CompactSerializer<RemittanceLocation2> {
        public RemittanceLocation2CS() { super(RemittanceLocation2.class); }
    }

    public static final class ServiceLevel8ChoiceCS extends ReflectivePain001CompactSerializer<ServiceLevel8Choice> {
        public ServiceLevel8ChoiceCS() { super(ServiceLevel8Choice.class); }
    }

    public static final class StructuredRegulatoryReporting3CS extends ReflectivePain001CompactSerializer<StructuredRegulatoryReporting3> {
        public StructuredRegulatoryReporting3CS() { super(StructuredRegulatoryReporting3.class); }
    }

    public static final class StructuredRemittanceInformation7CS extends ReflectivePain001CompactSerializer<StructuredRemittanceInformation7> {
        public StructuredRemittanceInformation7CS() { super(StructuredRemittanceInformation7.class); }
    }

    public static final class TaxAmount1CS extends ReflectivePain001CompactSerializer<TaxAmount1> {
        public TaxAmount1CS() { super(TaxAmount1.class); }
    }

    public static final class TaxAuthorisation1CS extends ReflectivePain001CompactSerializer<TaxAuthorisation1> {
        public TaxAuthorisation1CS() { super(TaxAuthorisation1.class); }
    }

    public static final class TaxInformation3CS extends ReflectivePain001CompactSerializer<TaxInformation3> {
        public TaxInformation3CS() { super(TaxInformation3.class); }
    }

    public static final class TaxParty1CS extends ReflectivePain001CompactSerializer<TaxParty1> {
        public TaxParty1CS() { super(TaxParty1.class); }
    }

    public static final class TaxParty2CS extends ReflectivePain001CompactSerializer<TaxParty2> {
        public TaxParty2CS() { super(TaxParty2.class); }
    }

    public static final class TaxPeriod1CS extends ReflectivePain001CompactSerializer<TaxPeriod1> {
        public TaxPeriod1CS() { super(TaxPeriod1.class); }
    }

    public static final class TaxRecord1CS extends ReflectivePain001CompactSerializer<TaxRecord1> {
        public TaxRecord1CS() { super(TaxRecord1.class); }
    }

    public static final class TaxRecordDetails1CS extends ReflectivePain001CompactSerializer<TaxRecordDetails1> {
        public TaxRecordDetails1CS() { super(TaxRecordDetails1.class); }
    }
}

