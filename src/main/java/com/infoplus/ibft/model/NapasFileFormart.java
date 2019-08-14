package com.infoplus.ibft.model;

import lombok.Data;

@Data
public class NapasFileFormart {
	private String fieldName;
	private int length;
	private String value;
	private String indetify;
	
	public NapasFileFormart(String fiedName, int length, String indentify, String value) {
		this.fieldName = fiedName;
		this.length = length;
		this.indetify = indentify;
		this.value = value;
	}
}
