package com.epam.webdriver.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.webdriver.utils.WaitUtils;

public class ForwardPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#settings/fwdandpop";

	public ForwardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//input[@type='button']")
	private WebElement buttonAddAForwardingAdress;

	@FindBy(xpath = "//div[@class='PN']/input[@type='text']")
	private WebElement editForwardingEmail;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='next']")
	private WebElement buttonNextAfterEnteringForwardingAdress;

	@FindBy(xpath = "//iframe[@class='ds']")
	private WebElement frame;

	@FindBy(xpath = "//input[@type='submit'][@value='Proceed']")
	private WebElement buttonProceedForConfirmAdress;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='ok']")
	private WebElement buttonConfirmOk;

	@FindBy(xpath = "//input[@name='sx_em'][@value='1']")
	private WebElement radioButtonForwarardACopyOfIncomingMail;

	@FindBy(xpath = "//button[ancestor::div[@class='nH Tv1JD']][text()='Save Changes']")
	private WebElement buttonSaveChanges;

	@FindBy(xpath = MESSAGE_LOADING)
	private WebElement loading;

	private final static String MESSAGE_LOADING = "//span[@class='v1']";

	public void setForwardToUser3(String forwardingEmail) {

		buttonAddAForwardingAdress.click();
		editForwardingEmail.sendKeys(forwardingEmail);
		buttonNextAfterEnteringForwardingAdress.click();
		driver.switchTo().frame(frame);
		buttonProceedForConfirmAdress.click();
		driver.switchTo().parentFrame();
		buttonConfirmOk.click();

	}

	public void setForwardACopyOfIncomingMailTo() {
		radioButtonForwarardACopyOfIncomingMail.click();
		WaitUtils.waitForElementVisible(driver, buttonSaveChanges, 5);
		buttonSaveChanges.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);

	}

	@FindBy(xpath = "//span[contains(text(),'Forward a copy')]/select")
	private WebElement selectForwarding;

	@FindBy(xpath = "//span[contains(text(),'Forward a copy')]/select/option[@value=2]")
	private WebElement removeForward;
	@FindBy(xpath = "//div[@class='Kj-JD-Jl'][preceding-sibling::div[contains(text(),'to remove')]]/button[@name='ok']")
	private WebElement buttonOkToConfirmDeletionForward;

	public void deleteForward() throws AWTException {
		selectForwarding.click();
		Robot robot;

		robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);

		WaitUtils.waitForElementVisible(driver,
				buttonOkToConfirmDeletionForward, 5);
		buttonOkToConfirmDeletionForward.click();
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
