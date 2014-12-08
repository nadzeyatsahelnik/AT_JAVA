package com.epam.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.webdriver.utils.WaitUtils;

public class LoginPage extends AbstractPage {

	private final String BASE_URL = "http://www.gmail.com";

	@FindBy(xpath = "id('Email')")
	private WebElement inputLogin;

	@FindBy(xpath = "id('Passwd')")
	private WebElement inputPassword;

	@FindBy(xpath = "id('signIn')")
	private WebElement buttonSignIn;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void login(String username, String password) {
		inputLogin.sendKeys(username);
		inputPassword.sendKeys(password);
		buttonSignIn.click();
		WaitUtils
				.waitForElementInvisibility(driver, "//div[@class='lpb']", 120);
		WaitUtils.waitForElementVisible(driver,
				driver.findElement(By.xpath("//iframe[@class='a1j']")), 10);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

}
