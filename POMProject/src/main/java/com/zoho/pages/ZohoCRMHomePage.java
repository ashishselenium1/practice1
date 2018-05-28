package com.zoho.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.zoho.pages.base.BasePage;


public class ZohoCRMHomePage extends BasePage{

	@FindBy(xpath="//*[@id='createIcon']")
	public WebElement expand;
	
	@FindBy(xpath="//a[@id='submenu_Leads']")
	public WebElement createNewLead;
	
	@FindBy(xpath="//a[text()='Leads']")
	public WebElement leadsTab;
	
	public ZohoCRMHomePage(WebDriver dr,ExtentTest t){
		super(dr,t);
	}

	public LeadCreationPage gotoLeadCreationPage() {
		expand.click();
		createNewLead.click();
		LeadCreationPage lp = new LeadCreationPage(driver, test);
		PageFactory.initElements(driver, lp);
		return lp;
		
	}

	public LeadPage gotoLeadPage() {
		leadsTab.click();
		LeadPage lp = new LeadPage(driver, test);
		PageFactory.initElements(driver, lp);
		return lp;
		
		
	}
}
