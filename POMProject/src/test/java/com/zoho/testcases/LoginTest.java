package com.zoho.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.pages.HomePage;
import com.zoho.pages.LaunchPage;
import com.zoho.pages.LoginPage;
import com.zoho.pages.SelectionPage;
import com.zoho.report.ExtentManager;
import com.zoho.testcases.base.BaseTest;
import com.zoho.util.Constants;
import com.zoho.util.DataUtil;
import com.zoho.util.Xls_Reader;

public class LoginTest extends BaseTest{
	
	@Test(dataProvider="getData")
	public void doLogin(
			String runmode,
			String browser,
			String username,
			String password,
			String expectedResult
			) {
		if(!DataUtil.isRunnable("LoginTest", xls) || runmode.equals("N")) {
			test.log(Status.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		System.out.println("xxxxxxxxx");
		openBrowser(browser);
		test.log(Status.INFO, "Starting login test");
		Object page = doLogin(username, password);
		String actualResult="";
		LoginPage lp=null;
		SelectionPage sp=null;
		if(page instanceof LoginPage) {
			actualResult="LoginFailure";
			lp = (LoginPage)page;
		}else {
			actualResult="LoginSuccess";
			sp=(SelectionPage)page;
		}
		if(!actualResult.equals(expectedResult)) {
			if(lp!=null)
				lp.reportFailure("Got actual result as "+ actualResult);
			else if(sp != null)
				sp.reportFailure("Got actual result as "+ actualResult);
		}
		
		
		test.log(Status.PASS, "Login Test Passed");
		
		//rep.flush();
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(this.getClass().getSimpleName(), xls);
	}

}
