package com.infoplus.ibft.model;

import lombok.Data;

@Data
public class NapasFileTrailerModel {
	private NapasDataFormat recordType;
	private NapasDataFormat transNo; 
	private NapasDataFormat fileCreator;
	private NapasDataFormat creationDate;
	private NapasDataFormat checkSum;

	public NapasFileTrailerModel() {
		this.recordType = new NapasDataFormat( "Record type", 2, null, "TR");
		this.transNo = new NapasDataFormat( "Number Of Transactions", 9, "[NOT]", null);
		this.fileCreator = new NapasDataFormat( "File creator", 20, "[CRE]", null);
		this.creationDate = new NapasDataFormat( "Creation date", 20, "[DATE]", null);
		this.checkSum = new NapasDataFormat( "Checksum value", 20, "[CSF]", null);
	}
}
