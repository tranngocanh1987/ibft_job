package com.infoplus.ibft.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.infoplus.ibft.entity.IbftHistory;
import com.infoplus.ibft.repository.IbftHistoryReps;

public abstract class BaseService {
	@Autowired
	IbftHistoryReps historyReps;
	
	
	String historyMessage;
	
	final Logger logger = LogManager.getLogger(this.getClass());
	
	IbftHistory insertHistory(String message) {
		IbftHistory historyEntity = new IbftHistory(null, this.getClass().getName(), this.getClass().toString(), new Date(), null, message);
		return historyReps.save(historyEntity);
	}
	
	void updateHistory(IbftHistory ibftHistory, String message, String status){
		ibftHistory.setMessage(message);
		ibftHistory.setEnded_date(new Date());
		
		historyReps.save(ibftHistory);
	}
	
	public void send() {};
}
