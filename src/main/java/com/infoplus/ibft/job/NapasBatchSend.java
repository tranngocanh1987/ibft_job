
package com.infoplus.ibft.job;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infoplus.ibft.service.CoreFileHandlerService;

@Component
public class NapasBatchSend extends ScheduledTasks {

	@Autowired
	CoreFileHandlerService service;

	@Scheduled(fixedDelay = 10000)
	public void execute() {
		logger.info("Call sending data function to NAPAS :: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
		//service.send();
	}
}
