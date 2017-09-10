package com.hybridframework_Selenium.testcases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.constants.Constants;
import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC005_Flight_SearchListingTestCase extends TestBase{
	
	
	
	@Test
	public void flight_SearchListingTestCase() throws Exception{
		
		/*if(!TestUtils.isTestcasesExecutable("flightSearchlistTestCase", Keywords.xls)){
			throw new SkipException("User set this test case run mode is No");
		}*/
		Keywords k = Keywords.getInstance();
		k.executeKeywords("flightSearchlistTestCase",null);
		
	}

}
