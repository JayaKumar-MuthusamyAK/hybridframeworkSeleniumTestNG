package com.hybridframework_Selenium.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.constants.Constants;
import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC001_LoginPageTestcase extends TestBase{
	
	
	
	@Test(dataProvider="testDataProvider")
	public void loginTestwithDifferentscenarios(Hashtable<String, String> data) throws Exception{
		
	/*	if(!TestUtils.isTestcasesExecutable("loginTestwithDifferentscenarios", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");*/
		
		if(!data.get("Runmode").equals("Y")){
			//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum("loginTestwithDifferentscenarios", Keywords.xls,"Expected_Result"), "-");
			//Keywords.xls.setCellDataInparticularCell("loginTestwithDifferentscenarios", "Test Data", "Expected_Result", "-");
			throw new SkipException("User is set this test case data runmode is No");
		}
			
	
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("loginTestwithDifferentscenarios", data);
	}
	
	@DataProvider
	public Object[][] testDataProvider(){
		return TestUtils.getData("loginTestwithDifferentscenarios", Keywords.xls);
		
	}
	

}
