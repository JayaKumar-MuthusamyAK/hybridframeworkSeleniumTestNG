package com.hybridframework_Selenium.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC001_LoginPageTestcase extends TestBase{
	
	WebDriver driver;
	@Test(dataProvider="testDataProvider")
	public void LoginPagetestCase(Hashtable<String, String> data) throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("LoginTest", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");
		if(!data.get("Runmode").equals("Y")){
			throw new SkipException("User is set this test case data runmode is No");
		}
		else{
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("LoginTest", data);
		}
	}
	
	@DataProvider
	public Object[][] testDataProvider(){
		return TestUtils.getData("LoginTest", Keywords.xls);
		
	}
	

}
