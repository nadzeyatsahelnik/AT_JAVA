package com.epam.webdriver.pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

	protected WebDriver driver;

	public abstract void openPage();

	public AbstractPage(WebDriver driver) {		
		this.driver = driver;	
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1100, 800));
		//driver.manage().window().maximize();
	}
	
	
	
	

}
