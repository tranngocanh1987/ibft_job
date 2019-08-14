package com.infoplus.ibft.model;

public class NapasDetailModel {
	private NapasFileFormart recordType;
	private NapasFileFormart mti; //Message Type Indicator
	private NapasFileFormart primaryAcctNo; //Primary Account Number
	private NapasFileFormart processingCode; 
	private NapasFileFormart serviceCode;
	private NapasFileFormart transChannelCode;
	private NapasFileFormart transAmout;
	private NapasFileFormart realTransAmout;
	private NapasFileFormart currenyCode;
	private NapasFileFormart settlementAmount;
	private NapasFileFormart settlementCurrencyAmount;
	private NapasFileFormart settlementExchangeRate;
	private NapasFileFormart billingAmount;
	private NapasFileFormart realCardHolderAmount;
	private NapasFileFormart realCardHolderBillingCurrencyCode;	//Cardholder Billing currency code
	private NapasFileFormart realCardHolderBillingConversionRate;	//Cardholder billing conversion rate
	private NapasFileFormart sysTraceAuditNo; //System Trace Audit Number
	private NapasFileFormart localTransTime; //Local transaction time (HHmmss)
	private NapasFileFormart localTransDate; //Local transaction date (MMdd)
	private NapasFileFormart settlementDate; //Settlement date (MMdd)
	private NapasFileFormart merchantCategoryCode; //Merchant category code
	private NapasFileFormart pointServiceEntryMode; //Point-of service Entry Mode
	private NapasFileFormart pointServiceConditionCode; //Point-ofservice Condition Code
	private NapasFileFormart cardAcceptorTerminalInd; //Card acceptor terminal identification
	private NapasFileFormart acquiringInstitutionCode; //Acquiring institution code
	
	public NapasDetailModel() {
		this.recordType = new NapasFileFormart("Record type", 2, null, "DR");
		
	}
	
}
