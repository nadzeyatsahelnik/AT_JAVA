package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class checkingStarSelection_13 extends BaseTest {

	@BeforeClass(description = "Login, send new letter, open inbox page")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
		steps.sendLetter(user1);
		steps.openInboxPage();
	}

	@AfterClass(description = "Delete all letters in starred page")
	public void afterClass() {
		steps.deleteAllLettersInStarredFolder();
	}

	@Test(description = "Check Star selection")
	public void isStarSelection() {
		boolean status = true;
		String messageInInbox = steps.clickStarNearMessage();
		steps.openStarredPage();
		String messageInStarredFolder = steps
				.getTopicAndBodyOfTheLetterInStarredFolder();
		status = messageInInbox.equals(messageInStarredFolder);
		if (!status) {
			LoggerUtils.error("Error.Message present");
		}
		Assert.assertTrue(status, "something wrong");
		LoggerUtils.success("Success.Message present");

	}

}
