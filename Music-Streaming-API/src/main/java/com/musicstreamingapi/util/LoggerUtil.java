package com.musicstreamingapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	private final static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
	
	public static void logInfo(String message){
        logger.info(message);
    }
	
	public static void logDebug(String message){
        logger.debug(message);
    }
	
	public static void logWarning(String message){
        logger.warn(message);
    }
	
	public static void logError(String message) {
		logger.error(message);
	}
	
}
