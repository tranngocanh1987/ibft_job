package com.infoplus.ibft.model;

import java.io.File;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FileModel {
	private File fileInfo;
	private String fileType;
	private Date createdDate;
	private List<String> contents;
	
	public FileModel() {
		super();
	}
	
	public FileModel(File fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public FileModel(File fileInfo, String fileType, Date createDate, List<String> contents) {
		this.fileInfo = fileInfo;
		this.fileType = fileType;
		this.createdDate = createDate;
		this.contents = contents;
	}
}
