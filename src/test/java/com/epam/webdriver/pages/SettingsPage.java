package com.epam.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.webdriver.utils.WaitUtils;

public class SettingsPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#settings/general";

	WebDriverWait driverWait = new WebDriverWait(driver, 10);

	public SettingsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/fwdandpop']")
	private WebElement linkForwardingAndPopImap;

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/filters']")
	private WebElement linkFilters;

	@FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/#settings/themes']")
	private WebElement linkThemes;

	// @FindBy(xpath = "//input[@name='sx_sg'][@value='1']")
	// private WebElement radioButtonSetSignature;

	@FindBy(xpath = "//input[@name='sx_sg'][@value='0']")
	private WebElement radioButtonSetNoSignature;

	@FindBy(xpath = "//button[@guidedhelpid='save_changes_button']")
	private WebElement buttonSaveChanges;

	@FindBy(xpath = "//div[@aria-label='Signature']")
	private WebElement textOfSignature;

	@FindBy(xpath = "//input[ancestor::td[following-sibling::td//label[contains(text(),'Vacation responder on')]][@class='C6']]")
	private WebElement radioButtonVacationOn;

	@FindBy(xpath = "//input[ancestor::td[following-sibling::td//label[contains(text(),'Vacation responder of')]][@class='C6']]")
	private WebElement radioButtonVacationOff;

	@FindBy(xpath = "//input[@class='Da']")
	private WebElement textboxSubjectOfVacation;

	@FindBy(xpath = "//div[@aria-label='Vacation responder']")
	private WebElement textboxMessageOfVacation;

	private final static String MESSAGE_SAVING = "//span[text()='Saving...']";
	private final static String MESSAGE_LOADING = "/span[text()='Loading...']";

	public void goToForwardPage() {
		linkForwardingAndPopImap.click();
	}

	public void goToFiltersPage() {
		linkFilters.click();

	}

	public void goToThemesPage() {
		linkThemes.click();

	}

	public void createSignature(String signature) {

		textOfSignature.sendKeys(signature);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		buttonSaveChanges.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
	}

	public void deleteSignatureAfterTest() {
		textOfSignature.clear();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		radioButtonSetNoSignature.click();
		buttonSaveChanges.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
	}

	public void selectVacationResponderOn() {
		radioButtonVacationOn.click();
	}

	public void enterDataInVacation(String subject, String message) {
		textboxSubjectOfVacation.sendKeys(subject);
		textboxMessageOfVacation.sendKeys(message);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonSaveChanges.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);

	}

	public void deleteVacation() {

		textboxSubjectOfVacation.clear();
		textboxMessageOfVacation.clear();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		radioButtonVacationOff.click();
		buttonSaveChanges.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_SAVING, 10);
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);

	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}
}
