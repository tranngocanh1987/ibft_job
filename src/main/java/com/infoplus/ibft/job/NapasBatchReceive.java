package com.infoplus.ibft.job;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infoplus.ibft.service.NapasReconciliationService;

@Component
public class NapasBatchReceive extends ScheduledTasks {
	
	
	@Autowired
	NapasReconciliationService service;

	@Scheduled(fixedRate = 10000)
	public void scheduleFixedRateTask() {
		logger.info("Call receiving data function from NAPAS :: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
		service.readNapasReconciliationFiles();
	}

	@Scheduled(cron = "20 55 17 * * *")
	public void scheduleTaskWithCronExpression() {
		logger.info("Call receiving data function from NAPAS :: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
		
		//NapasReconciliationService service = new NapasReconciliationService();
		service.readNapasReconciliationFiles();
	}

}
