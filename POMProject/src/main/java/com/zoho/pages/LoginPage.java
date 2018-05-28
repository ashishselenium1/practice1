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

public class LoginPage extends BasePage{
	@FindBy(id=Constants.USERNAME_TEXTBOX)
	public WebElement username;
	
	@FindBy(id=Constants.PASSWORD_TEXTBOX)
	public WebElement password;
	
	@FindBy(id=Constants.SIGNIN_SUBMIT)
	public WebElement signInButton;
	
	
	public LoginPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}
	
	
	public Object doLogin(String usernameVal, String passwordVal) {
		// selenium script
		test.log(Status.INFO, "Logging in zoho.com with "+Constants.USERNAME_TEXTBOX);
		username.sendKeys(usernameVal);
		password.sendKeys(passwordVal);
		signInButton.click();
		// check login is success or not
		wait(5);
		boolean result=isElementPresent(Constants.USERNAME_TEXTBOX, "id");
		if(result) {// not able login
			LoginPage lp = new LoginPage(driver,test);
			PageFactory.initElements(driver, lp);
			return lp;
		}else {// able to login
			SelectionPage sp= new SelectionPage(driver,test);
			PageFactory.initElements(driver, sp);
			return sp;
		}
	}
}
