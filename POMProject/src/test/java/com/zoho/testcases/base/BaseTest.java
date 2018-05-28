package com.zoho.testcases.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.pages.HomePage;
import com.zoho.pages.LaunchPage;
import com.zoho.pages.LoginPage;
import com.zoho.pages.SelectionPage;
import com.zoho.report.ExtentManager;
import com.zoho.util.Constants;
import com.zoho.util.Xls_Reader;

public class BaseTest {

	public ExtentReports rep;
	public ExtentTest test;
	public WebDriver driver;
	public Xls_Reader xls = new Xls_Reader(Constants.XLS_PATH);

	@BeforeMethod
	public void init() {
		//System.out.println();
		rep=ExtentManager.getInstance(Constants.REPORT_PATH);
	    test=rep.createTest(this.getClass().getSimpleName());
		
	}
	
	@AfterMethod
	public void quit() {
		if(rep!=null)
		  rep.flush();
		
		if(driver!=null)
		  driver.quit();
	}
	
	public void openBrowser(String browser) {
		if(Constants.GRID_RUN) {
			// DesiredCapabilities and RemoteWebDriver
			// send all tests to hub

			DesiredCapabilities cap=null;
			if(browser.equals("Mozilla")) {
				cap = DesiredCapabilities.firefox();
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}else if(browser.equals("Chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setJavascriptEnabled(true);
				cap.setPlatform(Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
		}else {
			if(browser.equals("Mozilla")) {
				driver = new FirefoxDriver();
			}else if (browser.equals("Chrome")) {
				System.out.println("zzzzzzz");
				
		         System.out.println("xxxxxxx1");
				driver = new ChromeDriver();
				System.out.println("xxxxx2");
			}else if (browser.equals("IE")) {
				driver = new InternetExplorerDriver();
			}else if (browser.equals("Edge")) {
				driver = new EdgeDriver();
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public Object doLogin(String usernameVal,String passwordVal) {
		LaunchPage lp = new LaunchPage(driver,test);
		PageFactory.initElements(driver, lp);

		HomePage hp = lp.gotoHomePage();
		hp.validateTitle(Constants.ZOHO_HOMEPAGE);
		LoginPage loginPage = hp.gotoLoginPage();
		test.log(Status.INFO, "Logging in");

		loginPage.validateElementPresent(Constants.USERNAME_TEXTBOX, "id");
		Object page=loginPage.doLogin(usernameVal, passwordVal);
		return page;

	}
	
	public SelectionPage defaultLogin() {
		
		Object page = doLogin(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		if(page instanceof LoginPage) {
			LoginPage lp = (LoginPage)page;
			lp.reportFailure("Not able to Login with default username/password");
		}
		SelectionPage sp = (SelectionPage)page;
		return sp;
	}
	
	
	
}
