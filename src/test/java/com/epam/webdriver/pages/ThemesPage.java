package com.epam.webdriver.pages;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.webdriver.utils.Utils;
import com.epam.webdriver.utils.WaitUtils;

public class ThemesPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#settings/themes";
	private WebDriverWait driverWait = new WebDriverWait(driver, 10);

	public ThemesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//span[text()='Custom Light']")
	private WebElement linkCustomTheme;

	@FindBy(xpath = "//div[text()='Upload photos']")
	private WebElement buttonUploadPhotos;

	@FindBy(xpath = "//iframe[@class='KA-JQ']")
	private WebElement frameForChangePhoto;

	@FindBy(xpath = "//div[text()='Select photos from your computer']")
	private WebElement buttonSelectPhotosFromYourComputer;

	@FindBy(xpath = "//div[@class='d-Jb d-Jb-Lb d-Jb-Ob']/span")
	private WebElement messageUploadError;

	@FindBy(xpath = "//span[@class='sf']")
	private WebElement curentTheme;

	@FindBy(xpath = "//span[text()='Beach']")
	private WebElement beachTheme;

	@FindBy(xpath = "//span[text()='Light']")
	private WebElement lightTheme;

	@FindBy(xpath = "//div[@class='vh'][contains(text(),'saved')]")
	private WebElement messageThatChoosenThemeWasSet;

	private final static String MESSAGE_WITH_ERROR = "//div[@class='d-Jb d-Jb-Lb d-Jb-Ob']";

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}

	public void changeBackgroundPhoto(long size) throws IOException,
			AWTException {

		linkCustomTheme.click();
		driver.switchTo().frame(frameForChangePhoto);
		buttonUploadPhotos.click();
		driverWait.until(ExpectedConditions
				.visibilityOf(buttonSelectPhotosFromYourComputer));
		buttonSelectPhotosFromYourComputer.click();
		File file = Utils.getRandomFile(size);
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		Utils.javaRobotForAttachFile(ss);

		// if (WaitUtils.isElementPresent(driver, MESSAGE_WITH_ERROR, 10)
		// && messageUploadError.getText().equals(
		// "There was an upload error.  Dismiss")) {
		// return true;
		// }
		// return false;

	}

	public boolean isErrorMessageApeared() {
		if (WaitUtils.isElementPresent(driver, MESSAGE_WITH_ERROR, 10)
				&& messageUploadError.getText().equals(
						"There was an upload error.  Dismiss")) {
			return true;
		}
		return false;
	}

	public void chooseBeachTheme() {
		beachTheme.click();
		WaitUtils.waitForElementVisible(driver, messageThatChoosenThemeWasSet,
				5);

	}

	public boolean isBeachThemeWasChoosen() {
		driver.navigate().refresh();
		if (curentTheme.getText().equals("Beach")) {
			return true;
		}
		return false;
	}

	public void chooseLightTheme() {
		lightTheme.click();
		WaitUtils.waitForElementVisible(driver, messageThatChoosenThemeWasSet,
				5);

	}

}
