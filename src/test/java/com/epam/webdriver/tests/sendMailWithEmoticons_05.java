package com.epam.webdriver.tests;

import java.awt.AWTException;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class sendMailWithEmoticons_05 extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
	}

	@AfterClass(description = "Delete all letters in inbox")
	public void afterClass() {
		steps.openInboxPage();
		steps.deleteAllLetersInInbox();
	}

	@Test(description = "Send mail with some attached emoticons")
	public void theMessageWithEmoticonsWasSent() throws AWTException {

		boolean status = true;
		steps.clickCompose();
		steps.editRecepient(user1);
		List<String> sendEmoticons = steps.attachEmoticonsInNewLetter(6, 59);
		steps.clickSendInNewLetter();
		steps.openInboxPage();
		steps.openNewLetter();
		status = steps.isTheSentEmoticonsAreAtTheTextArea(sendEmoticons);
		if (status) {
			LoggerUtils
					.success("Success.The sent emoticons are at the mail text area");
		}

		else
			LoggerUtils
					.error("Error.The sent emoticons are at the mail text area");
		Assert.assertTrue(status, "Something wrong");
	}
}
