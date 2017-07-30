package com.hybridframework_Selenium.testScripts;

import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.hybridframework_Selenium.constants.Constants;
import com.hybridframework_Selenium.excelReader.Xls_Reader;
import com.hybridframework_Selenium.testBase.TestBase;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class Keywords extends TestBase{

	
	public static Xls_Reader xls = new Xls_Reader(
			System.getProperty("user.dir") + "/src/main/java/com/hybridframework_Selenium/testdata/Test Suite1.xlsx");
	public static final Logger log = Logger.getLogger(Keywords.class.getName());
	
	public static Keywords keywords = null;
	public String testCasesName = "";

	public void executeKeywords(String testcases, Hashtable<String, String> data) throws Exception {
	
		this.testCasesName=testcases;
		loadproperties();
		PropertyConfigurator.configure("log4j.properties");
		//System.out.println("Executable test cases is :" + testcases);
		log("Executable test cases name is :" + testcases);
		String keyword = null;
		String objectkeys = null;
		String datakeys = null;

		for (int rNum = 2; rNum <= xls.getRowCount("Test Steps"); rNum++) {
			if (testcases.equals(xls.getCellData("Test Steps", "TCID", rNum))) {
				keyword = xls.getCellData("Test Steps", "Keyword", rNum);
				objectkeys = xls.getCellData("Test Steps", "Object", rNum);
				datakeys = xls.getCellData("Test Steps", "Data", rNum);

				//System.out.println(keyword + "--" + objectkeys + "--" + datakeys);
				log(keyword + "--" + objectkeys + "--" + datakeys);
				String result = null;
				switch(keyword) {
				case "openBrowser":
					result= openBrowser(objectkeys);
					break;
				case "navigateURL":
					result= navigateURL(objectkeys);
					break;
				case "verifyTitle":
					result = verifyTitle(datakeys);
					break;
				case "verifyLink":
					result = verifyLink(objectkeys);
					break;
				case "click":
					result =click(objectkeys);
					break;
				case "input":
					result = input(objectkeys,data.get(datakeys));
					break;
				case "directInput":
					result = directInput(objectkeys,datakeys);
					break;
				case "verifySuccessMsg":
					result = verifySuccessMsg(objectkeys);
					break;
				case "selectdivOptions":
					result = selectdivOptions(objectkeys,datakeys);
					break;
				case "chooseDate":
					result = chooseDate(datakeys);
					break;
				case "chooseAdults":
					result = chooseAdults(Integer.parseInt(datakeys));
					break;
				case "chooseChildrans":
					result = chooseChildrans(Integer.parseInt(datakeys));
					break;
				case "chooseInfants":
					result = chooseInfants(Integer.parseInt(datakeys));
					break;
				case "verifyHiddenValue":
					result = verifyHiddenValue(objectkeys);
					break;
				case "chooseClass":
					result = chooseClass(datakeys);
					break;
				case "verifyTotalinSplit":
					result =verifyTotalinSplit(objectkeys);
					break;
				case "scrollDownVerifyCount":
					result = scrollDownVerifyCount(objectkeys);
					break;
				case "printAllprojectnames":
					result = printAllprojectnames(objectkeys);
					break;
				case "chooseOnBook":
					result = chooseOnBook(objectkeys,datakeys);
					break;
				case "verifyReviewPageGrandTotal":
					result = verifyReviewPageGrandTotal(objectkeys);
					break;
				case "close":
					result = close();
					break;
				case "chooseChildAge":
					result = chooseChildrenswiththeirAge(datakeys);
					break;
				default:
					System.out.println("Not Matching Keyword :"+keyword);
					break;
				}
			}

		}
	}


	

	public String openBrowser(String objectkeys) {
		selectBrowser(prop.getProperty(objectkeys));
		return "Pass";
	}
	

	public String navigateURL(String objectkeys) {
		navigateGivenDomain(objectkeys);
		return "Pass";
	}

	

	public String verifyTitle(String datakeys) throws Exception {
	
		log("Verifing the given domain Url title.And the Title is =>>"+driver.getTitle()+"Expected Title is: "+datakeys);
		Assert.assertEquals(driver.getTitle(), datakeys);
		return "Pass";
	}
	
	public String verifyLink(String objectkeys) throws Exception {
		//log("verifing the link. and the link name is "+getWebElement(objectkeys));
		if(getWebElements(objectkeys).size()!=0)
			return "Pass";
		return "Fail";
	}
	
	

	public String click(String objectkeys) throws Exception {
		log("Clicking the given object. and the element is =>>"+getWebElement(objectkeys));
		getWebElement(objectkeys).click();
		Thread.sleep(1000);
		return "Pass";
	}
	
	public String input(String objectkeys, String string) throws Exception {
		log("Entering the values in given webelement. The element name is =>>"+getWebElement(objectkeys));
		getWebElement(objectkeys).sendKeys(string);
		return "Pass";
	}

	public String verifySuccessMsg(String objectkeys) throws Exception {

		String name =objectkeys;
		
		if(name.contains("|")){
			int countnum= StringUtils.countMatches(name, "|");
			for(int i=0; i<=countnum;i++){
				if(getWebElements(name.split("\\|")[i]).size()!=0){
					System.out.println(name.split("\\|")[i]);
					//getWebElement(name.split("\\|")[i]);
					Thread.sleep(3000);
					System.out.println(getWebElement(name.split("\\|")[i]).getText());
					if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter an Email Address")){
						Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter an Email Address");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a password")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a password");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Username and password doesn't match")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Username and password doesn't match");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Hey ")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Hey ");
						clickOnlogout(prop.getProperty("logoutOptionlink"));
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter an Email Address")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter an Email Address");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a valid Phone Number")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a valid Phone Number");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a password")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a password");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a valid Email Address")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a valid Email Address");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Email already registered !")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Email already registered !");
						break;
					}
				}
				}
				
		}
		else{
			getWebElement(objectkeys);
		}
		
		return null;
	}

	public void waitForElementVisible(WebElement element) {
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public String verifyHiddenValue(String objectkeys) throws Exception {
		System.out.println(getWebElement(objectkeys).getAttribute("value"));
		return "Pass";
	}
	

	public String verifyTotalinSplit(String objectkeys) throws Exception {
		String total = getWebElement(objectkeys).getText().split(" ")[0];
		
		return total;
	}
	
	public String scrollDownVerifyCount(String objectkeys) throws Exception {
		
		List<WebElement> listofflightsnames = getWebElements(objectkeys.split("\\|")[0]);
		int flightnamelisttotal = listofflightsnames.size();
		int newcount=0;
		while(newcount<flightnamelisttotal){
			newcount=flightnamelisttotal;
			Actions act = new Actions(driver);
			act.moveToElement(listofflightsnames.get(flightnamelisttotal-1)).build().perform();
			listofflightsnames= driver.findElements(By.xpath("//span[@class='pull-left airline_info_detls']"));
			flightnamelisttotal = listofflightsnames.size();
			System.out.println(flightnamelisttotal);
		}
		int total = Integer.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().split(" ")[0]);
		
		Assert.assertTrue(total==flightnamelisttotal, "Total count is matching with expected count.");
		
		return "Pass";
	}


	public String printAllprojectnames(String objectkeys) throws Exception {
		List<WebElement> listofflightsnames = getWebElements(objectkeys.split("\\|")[0]);
		for(int i=0; i<listofflightsnames.size();i++){
			System.out.println((i+1)+"."+listofflightsnames.get(i).getText());
			
		}
		return "Pass";
	}

	public String chooseOnBook(String objectkeys, String datakeys) throws Exception {
		Actions act = new Actions(driver);
		int count =0;
		int calculatetot=0;
		
		List<WebElement> listofflightsnames = getWebElements(objectkeys.split("\\|")[0]);
		List<WebElement> listofflightsprice = getWebElements(objectkeys.split("\\|")[1]);
		HashMap<String, List<String>> flightpricemap= new HashMap<String, List<String>>();
		while(count<listofflightsnames.size()){
			if(listofflightsnames.get(count).getText().contains(datakeys)){
				List<WebElement> flight_timing = driver.findElements(By.xpath("(//div[@class='listing_top clearfix'])["+(count+1)+"]//span[@class='block timeCa RobotoRegular ng-binding'][1]"));
				List<String>timings=new LinkedList<String>();
				for(int i=0; i<flight_timing.size();i++){
					timings.add(flight_timing.get(i).getText());
				}
				flightpricemap.put(listofflightsnames.get(count).getText(), timings);
				act.moveToElement(driver.findElement(By.xpath("(//span[@class='open_icon_markwrapper pull-left noPadTop'])["+(count+1)+"]"))).build().perform();
				driver.findElement(By.xpath("(//span[@class='open_icon_markwrapper pull-left noPadTop'])["+(count+1)+"]")).click();
				driver.findElement(By.xpath("(//*[contains(text(),'Fare details')])["+(count+1)+"]")).click();
				//System.out.println(driver.findElement(By.xpath("//tr[1]/td[@class='ng-binding'][2]")).getText());
				//System.out.println(driver.findElement(By.xpath("//tr[2]/td[@class='ng-binding']")).getText());
				//System.out.println(driver.findElement(By.xpath("//tr[5]/td[@class='ng-binding']")).getText());
				
				
				int mark1 = Integer.parseInt(driver.findElement(By.xpath("//tr[1]/td[@class='ng-binding'][2]")).getText().replace(",", "").split("Rs ")[1]);
				int mark2 = Integer.parseInt(driver.findElement(By.xpath("//tr[2]/td[@class='ng-binding']")).getText().replace(",", "").split("Rs ")[1]);
				int mark3 = Integer.parseInt(driver.findElement(By.xpath("//tr[5]/td[@class='ng-binding']")).getText().replace(",", "").split("Rs ")[1]);
				calculatetot = mark1+mark2;
				System.out.println(calculatetot+"-"+mark3);
				if(mark3==calculatetot){
					System.out.println("Matching the actual result");
				}
				WebElement ele = driver.findElement(By.xpath("(//a[@class='btn btn-md select_button pull-right hidden-xs btn-primary-red'])["+(count+1)+"]"));
				act.moveToElement(ele).build().perform();
				ele.click();
				
				break;
			}
			count++;
		}
		String text = objectkeys.split("\\|")[2];
		System.out.println(flightpricemap.get(listofflightsnames.get(0)));
		Assert.assertTrue(calculatetot==Integer.parseInt(getWebElement(text).getText()), "Total price is matching with Expected Result");
		
		return "Pass";
	}
	
	public String verifyReviewPageGrandTotal(String objectkeys) throws Exception {
		if(objectkeys.contains("|")){
			String text1 = objectkeys.split("\\|")[0];
			String text2 = objectkeys.split("\\|")[1];
			String text3 = objectkeys.split("\\|")[2];
			int val1= Integer.parseInt(getWebElement(text1).getText().replace("Rs.", "").replace(",", "").trim());
			int val2=Integer.parseInt(getWebElement(text2).getText().replace("Rs.", "").replace(",", "").trim());
			int val3 = Integer.parseInt(getWebElement(text3).getText().replace("Rs.", "").replace(",", "").trim());
			
			int tot = val1+val2;
			Assert.assertTrue(val3==tot, "Total price calculation is Correct!!");
			
		}
		return null;
	}
	
	public String close() {
		if(driver!=null){
			driver.quit();
		}
		return "PASS";
	}


	public String directInput(String objectkeys, String datakeys) throws Exception {
		log("Entering the values in given webelement. The element name is =>>"+getWebElement(objectkeys));
		getWebElement(objectkeys).sendKeys(datakeys);
		return "Pass";
	}

	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
	public static Keywords getInstance(){
	
		if (keywords == null) {
			try {
				keywords = new Keywords();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
		return keywords;
	
	}
	//User Generic Methods//
	
	public void clickOnlogout(String property) throws Exception {
		
		getWebElement(property).click();;
		
	}
	public String selectdivOptions(String objectkeys, String datakeys) throws Exception {
		List<WebElement> options = getWebElements(objectkeys);
		for(WebElement opt: options){
			//System.out.println(opt.getText());
			if(opt.getText().contains(datakeys)){
				opt.click();
				break;
			}
			
		}
		return "Pass";
	}
	public String chooseDate(String datakeys) throws Exception {
		String day = datakeys.split("-")[0];
		String month_year = datakeys.split("-")[1];
		System.out.println(day);
		System.out.println(month_year);
		Thread.sleep(3000);
		String websiteLeftsiteMonthYear= getWebElement("homepage.datepicker.leftside.monthyear").getText();
		String websiteRightsiteMonthYear= getWebElement("homepage.datepicker.rightside.monthyear").getText();
		if(month_year.toLowerCase().equals(websiteLeftsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = getWebElements("homepage.datepicker.daylist.first");
		
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(month_year.toLowerCase().equals(websiteRightsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = getWebElements("homepage.datepicker.daylist.second");
			
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(getWebElements("homepage.datepicker.nexticon").size()!=0){
			//System.out.println(getWebElement("homepage.datepicker.nexticon"));
			getWebElement("homepage.datepicker.nexticon").click();
			chooseDate(datakeys);
		}
		return "Pass";
	}
	
	
	// Specific Application dependend keywords and objects;
	
	public String chooseInfants(int infantsCount) {
		if(infantsCount>=1){
			driver.findElement(By.xpath("//*[@id='js-infant_counter']/li["+infantsCount+"]")).click();
		}
		return "Pass";
		
	}
	public String chooseChildrans(int childrenCount) {
		if(childrenCount>=1){
		driver.findElement(By.xpath("//*[@id='js-child_counter']/li["+childrenCount+"]")).click();
		}
		return "Pass";
	}
	public String chooseAdults(int adultCount) {
		if(adultCount>=1){
		driver.findElement(By.xpath("//*[@id='js-adult_counter']/li["+adultCount+"]")).click();
		}
		return "Pass";
	}
	public String chooseClass(String classnameString) {
		
		driver.findElement(By.xpath("//*[@id='js-classFilters']/div/label[.//text()='"+classnameString+"']")).click();
		return "Pass";
		
	}
	
	public String chooseChildrenswiththeirAge(String ageWithNoChild){
		
		int no_ofChild= Integer.parseInt(ageWithNoChild.split("\\|")[0]);
		driver.findElement(By.xpath("//*[@id='js-child_counter']/li["+Integer.parseInt(ageWithNoChild.split("\\|")[0])+"]")).click();
		for(int i=0; i<no_ofChild;i++){
			driver.findElement(By.xpath("//*[@id='allPaxAge']/div[2]/ul/li['"+Integer.parseInt(ageWithNoChild.split("\\|")[i+1])+"']")).click();
		}
		
		return "Pass";
		
	}
}
