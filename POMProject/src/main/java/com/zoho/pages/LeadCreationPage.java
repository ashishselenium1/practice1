package com.zoho.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.zoho.pages.base.BasePage;
import com.zoho.util.Constants;


public class LeadCreationPage extends BasePage{
	@FindBy(xpath="//*[@id='Crm_Leads_COMPANY']")
	public WebElement companyName; 
	@FindBy(xpath="//*[@id='Crm_Leads_LASTNAME']")
	public WebElement lastName; 
	@FindBy(xpath="//input[@name='save']")
	public WebElement save; 
	
	public LeadCreationPage(WebDriver dr, ExtentTest t) {
		super(dr,t);
	}

	public void createLead(String company, String lastNm) {
		companyName.sendKeys(company);
		lastName.sendKeys(lastNm);
		save.click();
		
	}

}
