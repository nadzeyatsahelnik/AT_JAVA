package com.epam.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.webdriver.logger.LoggerUtils;

public class changeShortcut_09 extends BaseTest {

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

	@Test(description = "Change shortcut", groups = {"edit_shortcut"}, dependsOnGroups = "create_shortcut")
	public void changeShortcut() {
		boolean status = true;

		steps.clickTriangle(shortcutName);
		steps.clickLabelColorForShortcut();
		steps.changeShortcutColor(7);
		steps.clickArrowNearParentShortcut();
		steps.clickTriangle(shortcutName);
		steps.clickLabelColorForShortcut();
		if (steps.isColorOfShortcutWasChanged(7)) {
			LoggerUtils.info("Color of parent shortcut was changed");
		} else {
			status = false;
			LoggerUtils.info("Color of parent shortcut was not changed");
		}
		steps.clickTriangle(insertShortcutName);
		steps.clickLabelColorForShortcut();

		if (steps.isColorOfShortcutWasChanged(7)) {
			LoggerUtils.info("The color of insert shortcut was changed");
		} else {
			status = false;
			LoggerUtils.info("The color of insert shortcut was not changed");
		}

		if (!status) {
			LoggerUtils
					.error("Error.The colour of both shortcuts is the same as choosen");
		}
		Assert.assertTrue(status);
		LoggerUtils
				.success("Success.The colour of both shortcuts is the same as choosen");

	}

}
