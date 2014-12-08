package com.epam.webdriver.tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class changeThemeWithNotImageExtentionFile_04 extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);

	}

	@AfterClass(description = "Delete generated file")
	public void afterClass() {
		Utils.deleteFile(new File("file.txt"));
		LoggerUtils.info("Generated file was deleted");
	}

	@Test(description = "Themes")
	public void warningMessageThereWasAnUploadError() throws IOException,
			AWTException {
		boolean status = true;
		steps.openSettingsPage();
		steps.openThemesPage();
		steps.changeBackgroundPtoto(1024 * 1024 * 24);
		status = steps.isErrorMessageApeared();
		if (status == true)
			LoggerUtils.success("Success.Meesage There was an upload error.");
		else
			LoggerUtils.error("Error.There was an upload error.");
		Assert.assertTrue(status, "Something wrong");

	}
}
