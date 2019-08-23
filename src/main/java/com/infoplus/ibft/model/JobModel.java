package com.infoplus.ibft.model;

import java.util.Date;

import com.infoplus.ibft.entity.IbftJob;

import lombok.Data;

@Data
public class JobModel {
	private Long id;
	private String jobName;
	private String dirName;
	private String funcName;
	private String serviceName;
	private Date createdDt;
	private String createdBy;
	private String recordStatus;	
	
	private String workingStatus;
	
	public JobModel() {
		super();
	}
	
	public JobModel(IbftJob entity) {
		this.id = entity.getId();
		this.jobName = entity.getJob_name();
		this.dirName = entity.getDir_name();
		this.funcName = entity.getFunc_name();
		this.serviceName = entity.getService_name();
		this.createdDt = entity.getCreated_dt();
		this.createdBy = entity.getCreated_by();
		this.recordStatus = entity.getRecord_status();
	}
}
