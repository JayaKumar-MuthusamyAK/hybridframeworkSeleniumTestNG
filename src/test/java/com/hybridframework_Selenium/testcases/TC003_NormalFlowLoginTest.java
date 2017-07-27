package com.hybridframework_Selenium.testcases;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC003_NormalFlowLoginTest {
	
	//NormalLoginTest
	
	WebDriver driver;
	@Test
	public void normalFlowLoginTestcase() throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("normalFlowLoginTestcase", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");
		
		
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("normalFlowLoginTestcase", null);
		
	}
	

}
