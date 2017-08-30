package com.hybridframework_Selenium.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TC002_SignUpPageTestcases extends TestBase{

	
	@Test(dataProvider="testDataProvider")
	public void signUpPageTestcases(Hashtable<String, String> data) throws Exception{
		
		if(!TestUtils.isTestcasesExecutable("signUpPageTestcases", Keywords.xls))
			throw new SkipException("user set this test cases runmode is no");
		if(!data.get("Runmode").equals("Y")){
			//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum("signUpPageTestcases", Keywords.xls,"Expected_Result"), "-");
			//Keywords.xls.setCellDataInparticularCell("signUpPageTestcases", "Test Data", "Expected_Result", "-");
			throw new SkipException("user set this test case data runmode is no");	
		}
		else{
			Keywords keywords=Keywords.getInstance();
			keywords.executeKeywords("signUpPageTestcases", data);
		}
	}
	
	@DataProvider
	public Object[][] testDataProvider(){
		return TestUtils.getData("signUpPageTestcases", Keywords.xls);
		
	}
	
}
