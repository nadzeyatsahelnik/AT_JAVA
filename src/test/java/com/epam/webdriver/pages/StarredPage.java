package com.epam.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.webdriver.utils.WaitUtils;

public class StarredPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#starred";

	@FindBy(xpath = RADIO_BUTTON_FOR_CHOOSE_ALL_LETTERS)
	private WebElement radioButtonForChooseAllLetters;

	private static final String RADIO_BUTTON_FOR_CHOOSE_ALL_LETTERS = "//div[@gh][@class='Cq aqL']//span[@role='checkbox']";

	@FindBy(xpath = "//div[@aria-label='Delete'][ancestor::div[@style='']]")
	private WebElement buttonDeleteLetters;

	private final static String TOPIC_AND_BODY_OF_THE_LETTER = "//div[@class='y6']";

	@FindBy(xpath = TOPIC_AND_BODY_OF_THE_LETTER)
	private WebElement topicAndBodyOfTheLetter;

	private final static String MESSAGE_LOADING = "/span[text()='Loading...']";

	public StarredPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public String getTopicAndBodyOfTheLetterInStarredFolder() {
		WaitUtils.waitForElementVisible(driver, topicAndBodyOfTheLetter, 10);
		return topicAndBodyOfTheLetter.getText();

	}

	public void deleteAllLetters() {
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
		if (WaitUtils.isElementPresent(driver, TOPIC_AND_BODY_OF_THE_LETTER)) {

			radioButtonForChooseAllLetters.click();
			WaitUtils.waitForElementVisible(driver, buttonDeleteLetters, 2);
			buttonDeleteLetters.click();

			WaitUtils.waitForElementInvisibility(driver,
					"topicAndBodyOfTheLetter", 10);
		}

	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
