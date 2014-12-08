package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class checkVacation_14 extends BaseTest {

	@BeforeClass
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());

	}

	@AfterClass
	public void afterClass() {
		steps.deleteAllLetersInInbox();
		steps.openSentPage();
		steps.deleteAllLetersInSent();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user1, password1);
		steps.deleteAllLetersInInbox();
		steps.openSentPage();
		steps.deleteAllLetersInSent();
		steps.openSettingsPage();
		steps.deleteVacation();

	}

	@Test(description = "check vacation")
	public void checkVacation() {
		boolean status = true;
		String subject = "TestVacation";
		String message = "This is last test";
		steps.loginGmail(user1, password1);
		steps.openSettingsPage();
		steps.selectVacationResponderOn();
		steps.enterDataInVacation(subject, message);
		if (!steps.isVacationWasSet(subject)) {
			LoggerUtils
					.error("There aren't message at the top of page.The vacation wasn't set");
			Assert.fail();
		}
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		steps.clickCompose();
		steps.editRecepient(user1);
		String subjectOfTheLetter = Utils.getRandomString(5);
		String bodyIfTheLetter = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetter, bodyIfTheLetter);
		steps.clickSendInNewLetter();
		status = steps.isResponderLetterInInbox(subject, message);
		if (!status) {
			LoggerUtils
					.error("Error.There aren't  message with correct information in Inbox");
		}
		Assert.assertTrue(status, "something wrong");
		LoggerUtils.success("Success.The letter is in Inbox");
	}

}
