package com.epam.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.WaitUtils;

public class TrashPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#trash";

	@FindBy(xpath = "//div[@class='yW']/span[@class='zF'][ancestor::div[@class='ae4 UI UJ']]")
	private WebElement newLetter;

	@FindBy(xpath = "//img[@class='ajz']")
	private WebElement imgInfoAboutLetter;

	@FindBy(xpath = "//div[@class='ajB gt']")
	private WebElement infoAboutLetter;

	private final static String TOPIC_OF_THE_LETTER = "//div[@class='y6']/span/b[contains(text(),'%s')]";

	private final static String IMAGE_ATTACHMENT = "//img[@alt='Attachments']";

	public TrashPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public boolean isLetterFromUser1WithAttachInTrash(String subject)

	{

		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("There are not new message more then 2 minutes!!!");
				Assert.fail();
			}
		}
		WebElement letter = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));

		letter.click();
		if (WaitUtils.isElementPresent(driver, IMAGE_ATTACHMENT)) {
			imgInfoAboutLetter.click();
			if (infoAboutLetter.getText().contains("Important")) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}
}
