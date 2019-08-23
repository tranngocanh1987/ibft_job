package com.infoplus.ibft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ibft_job")
public class IbftJob implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="job_name")
	private String job_name;
	
	@Column(name="dir_name")
	private String dir_name;
	
	@Column(name="func_name")
	private String func_name;
	
	@Column(name="service_name")
	private String service_name;
	
	@Column(name="record_status")
	private String record_status;
	
	@Column(name="created_dt")
	private Date created_dt;
	
	@Column(name="created_by")
	private String created_by;
}
