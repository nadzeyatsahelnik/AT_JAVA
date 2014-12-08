package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class createShortcut_08 extends BaseTest {
	private static String shortcutName = "My shortcut";
	private static String insertShortcutName = "My inserted shortcut";

	@BeforeClass(description = "Login and create parent label")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
		steps.createNewParentLabel(shortcutName);
	}

	@AfterClass
	public void afterClass() {

	}

	@Test(description = "Create new inserted shortcut", groups = {"create_shortcut"})
	public void createInsertedShortcut() {

		boolean status = true;
		steps.clickTriangle(shortcutName);
		steps.addInsertedLabel(shortcutName, insertShortcutName);
		steps.clickArrowNearParentShortcut();
		status = steps.isCreatedShortcutIsPresent(shortcutName,
				insertShortcutName);
		if (!status) {
			LoggerUtils.error("Error.Created insert shortcut is present");
		}
		Assert.assertTrue(status);
		LoggerUtils.success("Success.Created insert shortcut is present");

	}

}
