package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;

public class chekingSignature_12 extends BaseTest {

	@BeforeClass(description = "Login")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
	}

	@AfterClass(description = "Delete created signature")
	public void afterClass() {
		steps.openSettingsPage();
		steps.deleteSignatureAfterTest();
	}

	@Test(description = "Cheking signature")
	public void newMessageHasSignature() {
		boolean status = true;
		String signature = Utils.getRandomString(5);
		steps.openSettingsPage();
		steps.createSignature(signature);
		steps.clickCompose();
		steps.getSignatureOfNewMessage();
		status = steps.getSignatureOfNewMessage().equals(signature);
		if (!status) {
			LoggerUtils.error("Error.New message has text equals signature");
		}
		Assert.assertTrue(status, "Something wrong");
		LoggerUtils.success("Success.New message has text equals signature");
		steps.closeMessageWindow();

	}

}
