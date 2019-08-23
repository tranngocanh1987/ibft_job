package com.infoplus.ibft.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FileModel {
	private String fileType;
	private Date receivedDate;
	private List<String> contents;
	
	private NapasFileNameModel napasFileName;
	
	public FileModel() {
		super();
	}
	
	
	public FileModel(NapasFileNameModel napasFileName, String fileType, Date receivedDate, List<String> contents) {
		this.napasFileName = napasFileName;
		this.fileType = fileType;
		this.receivedDate = receivedDate;
		this.contents = contents;
	}
}
