package com.epam.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.webdriver.utils.WaitUtils;

public class SentPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#sent";

	private final static String CHOOSE_ALL_LETTERS_FROM_SENT = "//div[@class='T-Jo-auh'][ancestor::div[@gh='mtb']]";

	@FindBy(xpath = CHOOSE_ALL_LETTERS_FROM_SENT)
	private WebElement chooseAllLeterFromSent;

	private final static String TOPIC_AND_BODY_OF_THE_LETTER = "//div[@class='y6']";

	@FindBy(xpath = TOPIC_AND_BODY_OF_THE_LETTER)
	private WebElement topicAndBodyOfTheLetter;

	@FindBy(xpath = "//div[@aria-label='Delete'][ancestor::div[@style='']]")
	private WebElement deleteAllLetters;

	private final String BUTTON_OK_FOR_CONFIRM_DELETION = "//button[@class='J-at1-auR J-at1-atl']";

	@FindBy(xpath = BUTTON_OK_FOR_CONFIRM_DELETION)
	private WebElement buttonOkForConfirmDeletion;

	public SentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void deleteAllLetters() {

		if (WaitUtils.isElementPresent(driver, TOPIC_AND_BODY_OF_THE_LETTER)) {

			chooseAllLeterFromSent.click();
			deleteAllLetters.click();
			if (WaitUtils.isElementPresent(driver,
					BUTTON_OK_FOR_CONFIRM_DELETION)) {
				buttonOkForConfirmDeletion.click();
			}

			WaitUtils.waitForElementInvisibility(driver,
					"topicAndBodyOfTheLetter", 10);
		}

	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
