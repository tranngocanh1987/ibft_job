package com.infoplus.ibft.model;

import lombok.Data;

@Data
public class NapasFileHeaderModel {
	private NapasDataFormat recordType;
	private NapasDataFormat bankCode; 
	private NapasDataFormat transDate;

	public NapasFileHeaderModel() {
		this.recordType = new NapasDataFormat( "Record type", 2, null, "HR");
		this.bankCode = new NapasDataFormat( "Bank code", 8, "[REV]", null);
		this.transDate = new NapasDataFormat( "Transaction date", 6, "[DATE]", null);
	}
}
