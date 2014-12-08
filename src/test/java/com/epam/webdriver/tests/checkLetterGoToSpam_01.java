package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class checkLetterGoToSpam_01 extends BaseTest {

	@Test(description = "Spam")
	public void theLetterGoToSpam() {

		steps.loginGmail(user1, password1);
		steps.clickCompose();
		steps.editRecepient(user2);
		String subjectOfTheLetter_1 = Utils.getRandomString(5);
		String bodyOfTheLetter_1 = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetter_1,
				bodyOfTheLetter_1);
		steps.clickSendInNewLetter();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		steps.addLetterToSpam(subjectOfTheLetter_1);
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user1, password1);
		steps.clickCompose();
		steps.editRecepient(user2);
		String subjectOfTheLetter_2 = Utils.getRandomString(5);
		String bodyOfTheLetter_2 = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetter_2,
				bodyOfTheLetter_2);
		steps.clickSendInNewLetter();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		Assert.assertTrue(steps.isLetterGoToSpam(subjectOfTheLetter_2));
		LoggerUtils.success("Letter went to spam");

	}

	@BeforeClass
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
	}

	@AfterClass
	public void afterClass() {
		steps.removeAllMessagesAfterMethodFromSpamToInbox();
		steps.openInboxPage();
		steps.deleteAllLetersInInbox();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user1, password1);
		steps.openSentPage();
		steps.deleteAllLetersInSent();

	}

}
