package com.zoho.testcases;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.zoho.pages.LeadCreationPage;
import com.zoho.pages.LoginPage;
import com.zoho.pages.SelectionPage;
import com.zoho.pages.ZohoCRMHomePage;
import com.zoho.testcases.base.BaseTest;
import com.zoho.util.Constants;
import com.zoho.util.DataUtil;

public class CreateLeadTest extends BaseTest{

	
	@Test(dataProvider="getData")
	public void createLead(
			String runmode,
			String browser,
			String firstName,
			String lastName,
			String company
			) {
		if(!DataUtil.isRunnable(this.getClass().getSimpleName(), xls) || runmode.equals("N")) {
			test.log(Status.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		openBrowser(browser);
		
		SelectionPage sp=defaultLogin();
		ZohoCRMHomePage cp = sp.gotoHomePage();
		LeadCreationPage leadPage= cp.gotoLeadCreationPage();
		boolean result = leadPage.isElementPresent("//*[@id='Crm_Leads_COMPANY']", "xpath");
		if(!result){
			leadPage.reportFailure("Company name text Field not found on lead Page");
		}
		leadPage.createLead(company,lastName);
		// validate the lead creation
	
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(this.getClass().getSimpleName(), xls);
	}
}
