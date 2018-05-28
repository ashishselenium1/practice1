package com.zoho.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.pages.base.BasePage;

public class SelectionPage extends BasePage{

	@FindBy(xpath="//div[text()='CRM']")
	public WebElement crmLink;

	public SelectionPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}
	
	public ZohoCRMHomePage gotoHomePage() {
		
		test.log(Status.INFO, "Clicking on CRM Link");
		crmLink.click();
		ZohoCRMHomePage hp = new ZohoCRMHomePage(driver,test);
		PageFactory.initElements(driver, hp);
		return hp;
	}
	
}
