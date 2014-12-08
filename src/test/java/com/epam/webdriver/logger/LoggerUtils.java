package com.epam.webdriver.logger;

import org.apache.log4j.Logger;

public class LoggerUtils {
	private static final Logger logger = Logger.getLogger(LoggerUtils.class);

	public static void success(String msg) {
		
		logger.info("<p><span style='background-color:#7CFC00'>" + msg
				+ "</span></p>");
	}

	public static void error(String msg) {
		logger.error("<p><span style='background-color:#FF0000'>" + msg
				+ "</span></p>");
	}

	public static void info(String msg) {
		logger.info("<p>" + msg + "</p>");
	}

	public static void testInfo(String msg) {
		logger.info("<p><span style='background-color:#6495ED'><u>" + msg
				+ "</u></span></p>");
	}
}
