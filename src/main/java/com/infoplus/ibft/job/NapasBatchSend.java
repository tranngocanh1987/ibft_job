package com.infoplus.ibft.job;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;

public class NapasBatchSend extends ScheduledTasks{
	
	@Scheduled(cron = "0 0 23 * * *")
	public void scheduleTaskWithCronExpression() {
		logger.info("Call sending data function to NAPAS :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
	}
}
