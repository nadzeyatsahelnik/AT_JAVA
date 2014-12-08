package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class changeTheme_06 extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
	}

	@AfterClass(description = "Delete choosen theme")
	public void afterClass() {
		steps.chooseLightTheme();
	}

	@Test(description = "Theme")
	public void theThemeWasChanged() {
		boolean status = true;

		steps.openSettingsPage();
		steps.openThemesPage();
		steps.chooseBeachTheme();
		status = steps.isBeachThemeChoosen();
		if (status) {
			LoggerUtils.success("Success.The beach theme was set");

		} else {
			LoggerUtils.error("Error. The beach theme was set");

		}
		Assert.assertTrue(status, "Something wrong");

	}

}
