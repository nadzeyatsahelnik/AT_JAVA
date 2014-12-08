package com.epam.webdriver.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.WaitUtils;

public class SpamPage extends AbstractPage {
	private final String BASE_URL = "https://mail.google.com/mail/#spam";

	@FindBy(xpath = "//div[@class='yW']/span")
	private List<WebElement> sendersOfAllLetter;

	@FindBy(xpath = "//div[@class='yW']")
	private WebElement senderOfFirstLetter;

	@FindBy(css = "div.T-I.J-J5-Ji.aFk.T-I-ax7.ar7")
	private WebElement markAsNotSpam;

	@FindBy(xpath = "//div[@class='akh J-J5-Ji J-JN-I']")
	private WebElement logoForWait;

	@FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji.T-Jo-JW")
	private WebElement chooseFirstLetter;

	@FindBy(xpath = "//span[@class='bofITb'][contains(text(),'unmarked as spam')]")
	private WebElement messageMarkedAsNotSpam;

	@FindBy(xpath = "//div[@class='T-Jo-auh'][ancestor::tr][ancestor::div[@style='']]")
	private WebElement radioButtonForChooseFirstLetter;

	private final static String CHOOSE_ALL_LETTERS_FROM_SPAM = "//div[@class='T-Jo-auh'][ancestor::div[@gh='mtb']]";

	@FindBy(xpath = CHOOSE_ALL_LETTERS_FROM_SPAM)
	private WebElement chooseAllLeterFromSpam;

	private final static String TOPIC_AND_BODY_OF_THE_LETTER = "//div[@class='y6']";

	@FindBy(xpath = TOPIC_AND_BODY_OF_THE_LETTER)
	private WebElement topicAndBodyOfTheLetter;

	private final static String TOPIC_OF_THE_LETTER = "//div[@class='y6']/span/b[contains(text(),'%s')]";

	public SpamPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public boolean isTheLetterInSpam(String subject) {

		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("Error. There are not  message in spam more then 2 minutes!!!");
				Assert.fail();
			}
		}
		return true;

	}

	public void markLetterAsNotSpam(String subject) {
		WaitUtils.waitForElementVisible(driver,
				radioButtonForChooseFirstLetter, 10);
		radioButtonForChooseFirstLetter.click();
		markAsNotSpam.click();
		WaitUtils.waitForElementVisible(driver, messageMarkedAsNotSpam, 5);
		WaitUtils.waitForElementInvisibility(driver, "topicAndBodyOfTheLetter",
				10);

	}

	public void removeAllMessagesAfterMethodFromSpamToInbox() {

		if (WaitUtils.isElementPresent(driver, TOPIC_AND_BODY_OF_THE_LETTER)) {
			chooseAllLeterFromSpam.click();
			WaitUtils.waitForElementVisible(driver, markAsNotSpam, 5);
			markAsNotSpam.click();
			WaitUtils.waitForElementVisible(driver, messageMarkedAsNotSpam, 5);
			WaitUtils.waitForElementInvisibility(driver,
					"topicAndBodyOfTheLetter", 10);

		}

	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}

}
