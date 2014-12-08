package com.epam.webdriver.tests;

import java.io.File;
import java.util.ResourceBundle;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.steps.Steps;
import com.epam.webdriver.utils.Utils;

public abstract class BaseTest {
	protected static Steps steps;
	protected ResourceBundle resource = ResourceBundle.getBundle("users");
	protected String user1 = resource.getString("user1");
	protected String user2 = resource.getString("user2");
	protected String user3 = resource.getString("user3");
	protected String password1 = resource.getString("password1");
	protected String password2 = resource.getString("password2");
	protected String password3 = resource.getString("password3");

	@BeforeClass(description = "Init Browser")
	public void setUp() {
		Utils.deleteFile(new File("log.html"));
		steps = new Steps();
		LoggerUtils.testInfo("TEST:");
		steps.initBrowser();
		
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();

	}

}
