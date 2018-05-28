package com.zoho.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.pages.base.BasePage;

public class LaunchPage extends BasePage{
  
 
	public LaunchPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}
	
	public HomePage gotoHomePage() {
		test.log(Status.INFO, "Going to zoho home Page");
		driver.get("http://zoho.com");
		HomePage hp = new HomePage(driver,test);
		PageFactory.initElements(driver, hp);
		return hp;
	}
	
	
}
