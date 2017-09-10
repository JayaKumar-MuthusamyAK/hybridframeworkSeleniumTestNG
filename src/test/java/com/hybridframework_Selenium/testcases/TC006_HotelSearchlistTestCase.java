package com.hybridframework_Selenium.testcases;

import org.openqa.selenium.WebDriver;
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

public class TC006_HotelSearchlistTestCase extends TestBase {
	

	@Test
	public void hotelSearchlistTestCase() throws Exception{
	
		
		Keywords k = Keywords.getInstance();
		k.executeKeywords("hotelSearchlistTestCase", null);
	} 

}
