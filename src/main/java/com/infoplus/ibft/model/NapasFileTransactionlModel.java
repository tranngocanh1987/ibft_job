package com.infoplus.ibft.model;

import java.util.List;

import lombok.Data;

@Data
public class NapasFileTransactionlModel {
	private String generatedCheckSum;
	private boolean isMapped;
	private List<NapasDataFormat> transactions;
	
}
