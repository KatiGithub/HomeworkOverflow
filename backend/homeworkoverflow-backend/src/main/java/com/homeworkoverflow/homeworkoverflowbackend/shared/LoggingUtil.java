package com.homeworkoverflow.homeworkoverflowbackend.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {
    private static Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    static public void logDebug(String logText) {
        logger.debug(logText);
    }
}
