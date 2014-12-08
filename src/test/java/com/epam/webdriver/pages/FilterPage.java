package com.epam.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.webdriver.utils.WaitUtils;

public class FilterPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#settings/filters";

	@FindBy(xpath = "//span[@class='sA'][text()='Create a new filter']")
	private WebElement linkForCreateANewFilter;

	@FindBy(xpath = "//input[@class='ZH nr aQa']")
	private WebElement editFieldFrom;

	@FindBy(xpath = "//label[text()='Has attachment']/../input[@type='checkbox']")
	private WebElement checkBoxHasAttachment;

	@FindBy(xpath = "//div[@class='acM']")
	private WebElement linkCreateFilterWithThisSearch;

	@FindBy(xpath = "//button[@class='J-at1-auR J-at1-atl']")
	private WebElement buttonOkForConfirmDiscardChanges;

	@FindBy(xpath = "//label[text()='Delete it']/../input")
	private WebElement checkBoxDeleteIt;

	@FindBy(xpath = "//label[text()='Always mark it as important']/../input")
	private WebElement checkBoxAlwaysMarkItAsImportant;

	@FindBy(xpath = "//div[@class='T-I J-J5-Ji Zx acL T-I-atl L3']")
	private WebElement buttonCreateFilter;

	private final static String MESSAGE_LOADING = "/span[text()='Loading...']";

	@FindBy(xpath = "//div[@class='Kj-JD-Jl'][preceding-sibling::div[contains(text(),'delete this filter')]]/button[@name='ok']")
	private WebElement buttonOkToConfirmDeletionFilter;

	@FindBy(xpath = "//div[@class='Kj-JD']")
	private WebElement windowForConfirm;

	public FilterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void createANewFilter(String fieldFrom) {
		linkForCreateANewFilter.click();
		editFieldFrom.sendKeys(fieldFrom);
		checkBoxHasAttachment.click();
		linkCreateFilterWithThisSearch.click();
		checkBoxDeleteIt.click();
		checkBoxAlwaysMarkItAsImportant.click();
		buttonCreateFilter.click();
		WaitUtils.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);

	}

	private final static String LINK_DELETE_FILTER = "//span[@class='sA'][contains(text(),'delete')]";
	@FindBy(xpath = LINK_DELETE_FILTER)
	private WebElement linkDeleteFilter;

	public void deleteFilter() {
		if (WaitUtils.isElementPresent(driver, LINK_DELETE_FILTER)) {
			linkDeleteFilter.click();
			WaitUtils.waitForElementVisible(driver,
					buttonOkToConfirmDeletionFilter, 5);
			buttonOkToConfirmDeletionFilter.click();

		}

	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
