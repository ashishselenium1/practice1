package com.zoho.pages.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zoho.report.ExtentManager;

public class BasePage {
	public WebDriver driver;
	public ExtentTest test;
	
	public BasePage(WebDriver driver,ExtentTest test) {
		// screenshot for everypage
		this.driver=driver;// new page loaded
		this.test=test;
	}
	
	public void validateTitle(String expectedTitle) {
		String actualTitle=driver.getTitle();
		if(!actualTitle.equals(expectedTitle)) {
			reportFailure("Titles did not match");
		}
	}
	
	public void validateElementPresent(String locator, String locatorType) {
		
		if(!isElementPresent(locator,locatorType)) {
			reportFailure("Element not found "+ locator);
		}
	}
	
	public boolean isElementPresent(String locator, String locatorType) {
		List<WebElement> e=null;
		
		if(locatorType.equals("xpath"))
			e = driver.findElements(By.xpath(locator));
		else if(locatorType.equals("id"))
			e = driver.findElements(By.id(locator));
		else if(locatorType.equals("name"))
			e = driver.findElements(By.name(locator));
		else if(locatorType.equals("linktext"))
			e = driver.findElements(By.linkText(locator));
		
		if(e.size()==0)
			return false;
		else 
			return true;
		
	}
	
	public void reportFailure(String failureMsg) {
		// report in extent rep
		test.log(Status.FAIL, failureMsg);
		// take screenshot and put in rep
		takeSceenShot();
		// fail in testng
		Assert.fail(failureMsg);
	}


	public void takeSceenShot(){
		// fileName of the screenshot file
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.FAIL,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void wait(int time) {
		try {
			Thread.sleep(1000*time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
