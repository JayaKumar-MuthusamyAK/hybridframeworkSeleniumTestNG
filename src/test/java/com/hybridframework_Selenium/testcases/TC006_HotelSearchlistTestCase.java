package com.hybridframework_Selenium.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC006_HotelSearchlistTestCase extends TestBase {
	
	WebDriver driver;
	@Test
	public void hotelSearchlistTestCase() throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("hotelSearchlistTestCase", Keywords.xls))
			throw new SkipException("User Set the test case Run mode is NO");
		Keywords k = Keywords.getInstance();
		k.executeKeywords("hotelSearchlistTestCase", null);
	} 

}
