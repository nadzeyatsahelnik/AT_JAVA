package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class deleteShortcut_10 extends BaseTest {

	private static String shortcutName = "My shortcut";
	private static String insertShortcutName = "My inserted shortcut";

	@BeforeClass(description = "Login")
	public void beforeClass() {
		LoggerUtils.testInfo(this.toString());
		steps.loginGmail(user1, password1);
	}

	@AfterClass
	public void afterClass() {

	}

	@Test(description = "Delete shortcut", dependsOnGroups = "edit_shortcut")
	public void deleteShortcut() {
		boolean status = true;
		steps.clickTriangle(shortcutName);
		steps.deleteShortcut(shortcutName, insertShortcutName);
		status = steps.isBorthShortcutsWereDeleted(shortcutName,
				insertShortcutName);

		if (!status) {
			LoggerUtils.error("Error.Borth shortcuts were deleted");
		}
		Assert.assertTrue(status);
		LoggerUtils.success("Success.Borth shortcuts were deleted");

	}

}
