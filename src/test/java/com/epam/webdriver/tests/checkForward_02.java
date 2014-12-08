package com.epam.webdriver.tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class checkForward_02 extends BaseTest {
	@Test(description = "Forward")
	public void forward() throws IOException, AWTException {
		boolean status = true;
		steps.loginGmail(user2, password2);
		steps.openSettingsPage();
		steps.openForwardPage();
		steps.setForwardToUser3(user3);
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user3, password3);
		steps.confirmForwarding();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		steps.openSettingsPage();
		steps.openForwardPage();
		steps.setForwardACopyOfIncomingMailTo();
		steps.openSettingsPage();
		steps.openFilterPage();
		steps.createANewFilter(user1);
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user1, password1);
		steps.clickCompose();
		steps.editRecepient(user2);
		String subjectOfTheLetterWithAttach = Utils.getRandomString(5);
		String bodyOfTheLetterWithAttach = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetterWithAttach,
				bodyOfTheLetterWithAttach);
		steps.attachFile(1024 * 10);
		steps.clickSendInNewLetter();
		steps.clickCompose();
		steps.editRecepient(user2);
		String subjectOfTheLetterWithoutAttach = Utils.getRandomString(5);
		String bodyOfTheLetterWithoutAttach = Utils.getRandomString(15);
		steps.enterSubjectAntBobyOfLetter(subjectOfTheLetterWithoutAttach,
				bodyOfTheLetterWithoutAttach);
		steps.clickSendInNewLetter();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		steps.openTrashPage();

		if (steps
				.isLetterFromUser1WithAttachInTrash(subjectOfTheLetterWithAttach)) {
			LoggerUtils
					.success("Success. There is letter from user1 with attach and mark as important in trash");
		} else {
			status = false;
			LoggerUtils
					.error("Error. There is letter from user1 with attach and mark as important in trash");
		}

		steps.openInboxPage();
		if (steps
				.isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(subjectOfTheLetterWithoutAttach)) {
			LoggerUtils
					.success("Success. There is letter from user1 without attach and not mark as important in inbox");
		} else {
			status = false;
			LoggerUtils
					.error("Error.There is letter from user1 without attach and not mark as important in inbox");
		}

		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user3, password3);
		if (steps
				.isLetterFromUser1WithoutAttachInInbox(subjectOfTheLetterWithoutAttach)) {
			LoggerUtils
					.success("Success. There is letter from user1 without attach in inbox");
		} else {
			status = false;
			LoggerUtils
					.error("Error. There is letter from user1 without attach in inbox");
		}

		Assert.assertTrue(status, "Something wrong");
		LoggerUtils.success("All asserts are success");

	}

	@BeforeClass
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
	}

	@AfterClass
	public void afterClass() throws AWTException {
		Utils.deleteFile(new File("file.txt"));
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user3, password3);
		steps.deleteAllLetersInInbox();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user1, password1);
		steps.openSentPage();
		steps.deleteAllLetersInSent();
		steps.closeDriver();
		steps.initBrowser();
		steps.loginGmail(user2, password2);
		steps.deleteAllLetersInInbox();
		steps.openSettingsPage();
		steps.openFilterPage();
		steps.deleteFilter();
		steps.openForwardPage();
		steps.deleteForward();

	}

}
