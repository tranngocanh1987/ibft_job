package com.infoplus.ibft.model;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public abstract class FileModel {
	private File fileInfo;
	private String fileType;
	private Date receivedDate;
	private List<String> contents;
	
	public FileModel() {
		super();
	}
	
	public FileModel(File fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public FileModel(File fileInfo, String fileType, Date receivedDate, List<String> contents) {
		this.fileInfo = fileInfo;
		this.fileType = fileType;
		this.receivedDate = receivedDate;
		this.contents = contents;
	}
}
