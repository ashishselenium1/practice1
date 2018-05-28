package com.zoho.testcases;


import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.zoho.pages.LeadPage;
import com.zoho.pages.SelectionPage;
import com.zoho.pages.ZohoCRMHomePage;
import com.zoho.testcases.base.BaseTest;
import com.zoho.util.DataUtil;


public class DeleteLeadTest extends BaseTest{

	String testName="DeleteLeadTest";

	
	
	@Test(dataProvider="getData")
	public void createLead(String runmode,String browser,String lastName){
		if(!DataUtil.isRunnable("DeleteLeadTest", xls) || runmode.equals("N")){
			test.log(Status.SKIP, "Skipping the test as runmode was NO");
			throw new SkipException("Skipping the test as runmode was NO");
		}
		test.log(Status.INFO, "Starting "+testName);
		openBrowser(browser);
		
		SelectionPage sp=defaultLogin();
		ZohoCRMHomePage cp = sp.gotoHomePage();
		LeadPage leadpage=cp.gotoLeadPage();
		leadpage.deleteLead(lastName);
		// validate deletion of the lead
	
	
	}
	
	@DataProvider
	public Object[][] getData(){
	    return DataUtil.getData(testName,xls);
	}
}
