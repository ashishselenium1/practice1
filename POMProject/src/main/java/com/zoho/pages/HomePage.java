package com.zoho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.pages.base.BasePage;
import com.zoho.util.Constants;

public class HomePage extends BasePage{
	@FindBy(linkText=Constants.LOGIN_LINK)
	public WebElement loginLink;
	
	public HomePage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}
	
	public LoginPage gotoLoginPage() {
		//selenium code
		test.log(Status.INFO, "Going to login page");
		validateElementPresent(Constants.LOGIN_LINK, "linktext");
		loginLink.click();
		LoginPage lp = new LoginPage(driver,test);
		PageFactory.initElements(driver, lp);
		return lp;
	}
	

}
