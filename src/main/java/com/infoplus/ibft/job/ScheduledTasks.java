package com.infoplus.ibft.job;

import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ScheduledTasks {
	 final Logger logger = LogManager.getLogger(this.getClass());
	 final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
}
