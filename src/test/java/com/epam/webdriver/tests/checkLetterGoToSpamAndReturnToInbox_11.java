package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class checkLetterGoToSpamAndReturnToInbox_11 extends BaseTest {

	private final static String SUBJECT_OF_THE_LETTER = Utils
			.getRandomString(5);
	private final static String BODY_OF_THE_LETTER = Utils.getRandomString(15);

	@BeforeClass(description = "Login, sent new letter, open inbox page")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
		steps.clickCompose();
		steps.editRecepient(user1);
		steps.enterSubjectAntBobyOfLetter(SUBJECT_OF_THE_LETTER,
				BODY_OF_THE_LETTER);
		steps.clickSendInNewLetter();
		steps.openInboxPage();
	}

	@AfterClass(description = "Delete all leters in inbox")
	public void afterClass() {
		steps.deleteAllLetersInInbox();

	}

	@Test(description = "Mark item as spam and mark spam item as not spam")
	public void markImemAsSpamAndMarkSpamItemAsNotSpam() {
		boolean status = true;
		steps.addLetterToSpam(SUBJECT_OF_THE_LETTER);
		steps.openSpamPage();
		steps.markLetterAsNotSpam(SUBJECT_OF_THE_LETTER);
		steps.openInboxPage();
		String returnMessageInInbox = steps
				.getTopicAndBodyOfTheLetterInInboxFolder();
		status = returnMessageInInbox.contains(SUBJECT_OF_THE_LETTER);
		if (!status) {
			LoggerUtils
					.error("Error.There is tested item in the list of inbox messages");
		}
		Assert.assertTrue(status, "something wrong");
		LoggerUtils
				.success("Success.There is tested item in the list of inbox messages");

	}

}
