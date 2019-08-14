package com.infoplus.ibft.model;

import lombok.Data;

@Data
public class NapasFileNameModel {
	private String createDate; // MMDDYY
	private String transCode; // ISS: Issuer, ACQ: Acquirer, BNB: Beneficiary (IBFT).
	private String moAbbreviation;
	private String moCode;
	private String paymentDay;
	private String reportCode; // TC: success trans, SL: dispute trans; XL: processed trans
	private String serviceCode; // SWC: ATM / POS, PC: Payment Code, Ecom: switching/Tokenization, UPI:UPI

	public NapasFileNameModel() {
		super();
	}

	public NapasFileNameModel(String createDate, String transCode, String moAbbreviation, String moCode,
			String paymentDay, String reportCode, String serviceCode) {
		this.createDate = createDate;
		this.transCode = transCode;
		this.moAbbreviation = moAbbreviation;
		this.moCode = moCode;
		this.paymentDay = paymentDay;
		this.reportCode = reportCode;
		this.serviceCode = serviceCode;
	}
}
