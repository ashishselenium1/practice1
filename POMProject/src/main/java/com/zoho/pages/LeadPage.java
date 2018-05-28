package com.zoho.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.zoho.pages.base.BasePage;


public class LeadPage extends BasePage{
	
	@FindBy(id="CustomizeTools")
	public WebElement custTools;
	@FindBy(id="Delete2")
	public WebElement delete;
	
	@FindBy(name="deleteButton")
	public WebElement delButton;
	
	
	
	public LeadPage(WebDriver dr,ExtentTest t){
		super(dr,t);
	}
	
	public void deleteLead(String lastName){
		driver.findElement(By.linkText(lastName)).click();
		custTools.click();
		delete.click();
		delButton.click();
	}
}
