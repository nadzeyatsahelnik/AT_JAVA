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

public class attachFileBigger25mb_03 extends BaseTest {

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

	@Test(description = "Attach file bigger then 25 mb")
	public void tryToAttachFileBiggerThen25mb() throws IOException,
			AWTException {
		boolean status = true;
		steps.clickCompose();
		steps.editRecepient(user2);
		String subjectOfTheLetter = Utils.getRandomString(5);
		String bodyOfTheLetter = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetter, bodyOfTheLetter);
		steps.attachFile(1024 * 1024 * 26);
		status = steps.isWarningMessageAppeared();
		if (!status) {

			LoggerUtils
					.error("Error.Warning message that size of file is bigger than 25 mb.");
		}
		Assert.assertTrue(status, "Something wrong");
		LoggerUtils
				.success("Success.Warning message that size of file is bigger than 25 mb. ");

	}
}
