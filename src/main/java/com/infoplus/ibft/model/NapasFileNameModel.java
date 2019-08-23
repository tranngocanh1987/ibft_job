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
	
	private String fullName;

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
	
	public NapasFileNameModel(String napasFileName) {
		this.fullName = napasFileName;
		String [] contents = napasFileName.split("\\.");
		
		if(contents != null && contents.length > 0) {
			String []  values = contents[0].split("\\_");
			
			if(values != null && values.length >=7) {
				int index = 0;
				this.createDate = values[index++];
				this.transCode = values[index++];
				this.moAbbreviation = values[index++];
				this.moCode = values[index++];
				this.paymentDay = values[index++];
				this.reportCode = values[index++];
				this.serviceCode = values[index];
			}
		}
	}
	
	/**
	 * anhtn5
	 * Create output file name
	 * @param reportCode: TC, SL, XL
	 * @param fileType: .dat, .batch 
	 * @return
	 */
	public String createOutputFileName(String reportCode, String fileType) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getCreateDate());
		sb.append("_");
		sb.append(this.getTransCode());
		sb.append("_");
		sb.append(this.getMoAbbreviation());
		sb.append("_");
		sb.append(this.getMoCode());
		sb.append("_");
		sb.append(this.getPaymentDay());
		sb.append("_");
		sb.append(reportCode);
		sb.append("_");
		sb.append(this.getServiceCode());
		sb.append(fileType);
		
		return sb.toString();
	}
}
