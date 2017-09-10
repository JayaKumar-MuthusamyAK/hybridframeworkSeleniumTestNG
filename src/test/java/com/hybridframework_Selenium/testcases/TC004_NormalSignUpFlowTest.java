package com.hybridframework_Selenium.testcases;

import java.util.Hashtable;

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

public class TC004_NormalSignUpFlowTest extends TestBase{
	
	
	@Test
	public void normalSignUpTestcase() throws Exception{
		
		/*if(!TestUtils.isTestcasesExecutable("normalSignUpTestcase", Keywords.xls))
			throw new SkipException("user set this test cases runmode is no");*/
	
			Keywords keywords=Keywords.getInstance();
			keywords.executeKeywords("normalSignUpTestcase", null);

	}
	
	

}
