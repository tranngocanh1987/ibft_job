package com.infoplus.ibft.job;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infoplus.ibft.service.NapasFileHandlerService;

@Component("receive_from_napas")
public class NapasBatchReceive extends ScheduledTasks {

//	@Autowired
//	NapasReconciliationService service;
	
	@Autowired
	NapasFileHandlerService service;

	@Scheduled(fixedDelay = 10000)
	public void execute() {
		logger.info("Call receiving data function from NAPAS :: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
		service.send();
	}

	/*
	 * @Scheduled(cron = "20 55 17 * * *") public void
	 * scheduleTaskWithCronExpression() {
	 * logger.info("Call receiving data function from NAPAS :: Execution Time - {}",
	 * dateTimeFormatter.format(LocalDateTime.now())); // NapasReconciliationService
	 * service = new NapasReconciliationService(); //
	 * service.readNapasReconciliationFiles(); }
	 */

}
