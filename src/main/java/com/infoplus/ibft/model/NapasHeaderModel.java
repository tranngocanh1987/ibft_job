package com.infoplus.ibft.model;

import lombok.Data;

@Data
public class NapasHeaderModel {
	private NapasFileFormart recordType;
	private NapasFileFormart bankCode; 
	private NapasFileFormart transDate;

	public NapasHeaderModel() {
		this.recordType = new NapasFileFormart( "Record type", 2, null, "HR");
		this.bankCode = new NapasFileFormart( "Record type", 8, "[REV]", null);
		this.transDate = new NapasFileFormart( "Transaction date", 6, "[DATE]", null);
	}
}
