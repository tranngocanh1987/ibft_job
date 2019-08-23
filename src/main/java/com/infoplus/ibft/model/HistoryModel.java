package com.infoplus.ibft.model;

import java.util.Date;

import lombok.Data;

@Data
public class HistoryModel {
	
	private Long id;
	private String name;
	private String location;
	private Date createdDate;
	private Date endedDate;
	private String message;	
	
}
