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
	@Test(dataProvider="testDataProvider")
	public void NormalFlowLoginTest(Hashtable<String, String> data) throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("NormalLoginTest", Keywords.xls))
			throw new SkipException("User is set this test cases runmode is No");
		if(!data.get("Runmode").equals("Y")){
			throw new SkipException("User is set this test case data runmode is No");
		}
		else{
		Keywords keywords=Keywords.getInstance();
		keywords.executeKeywords("NormalLoginTest", data);
		}
	}
	
	@DataProvider
	public Object[][] testDataProvider(){
		return TestUtils.getData("NormalLoginTest", Keywords.xls);
		
	}

}
