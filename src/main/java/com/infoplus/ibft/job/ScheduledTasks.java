package com.infoplus.ibft.job;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ScheduledTasks {
	 final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	 final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

}
