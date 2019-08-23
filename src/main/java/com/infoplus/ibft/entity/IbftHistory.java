package com.infoplus.ibft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infoplus.ibft.model.HistoryModel;

import lombok.Data;

@Entity
@Data
@Table(name = "ibft_history")
public class IbftHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="ended_date")
	private Date ended_date;
	
	@Column(name="message")
	private String message;
	
	
	public IbftHistory() {
		super();
	}
	
	public IbftHistory(HistoryModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.location = model.getLocation();
		this.created_date = model.getCreatedDate();
		this.ended_date = model.getEndedDate();
		this.message = model.getMessage();
	}
	
	public IbftHistory(Long id, String name, String location, Date createdDate, Date endedDate, String message) {
		if(id != null)
			this.id = id;
		
		this.name = name;
		this.location = location;
		this.created_date = createdDate;
		this.ended_date =endedDate;
		this.message = message;
	}
}
