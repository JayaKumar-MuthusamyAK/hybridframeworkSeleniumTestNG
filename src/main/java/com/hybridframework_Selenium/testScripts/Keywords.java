package com.hybridframework_Selenium.testScripts;

import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
				case "clickOngivenName":
					result =clickOngivenName(datakeys);
					break;
				case "input":
					result = input(objectkeys,data.get(datakeys));
					break;
				case "directInput":
					result = directInput(objectkeys,datakeys);
					break;
				case "verifySuccessMsg":
					result = verifySuccessMsg(objectkeys,data.get(datakeys));
					break;
				case "selectdivOptions":
					result = selectdivOptions(objectkeys,datakeys);
					break;
				case "chooseDate":
					result = chooseDate(datakeys);
					break;
				case "chooseDateReturn":
					result =chooseDateReturn(datakeys);
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
				case "chooseChildrenswiththeirAge":
					result =chooseChildrenswiththeirAge(datakeys);
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
				case "waitForTextPresent":
					result = waitForTextPresent(objectkeys,datakeys);
					break;
				case "scrollDownUntilCountMatch":
					result = scrollDownUntilCountMatch(objectkeys);
					break;
				case "waitForVisibilityOfElement":
					result = waitForVisibilityOfElement(objectkeys,Integer.parseInt(datakeys));
					break;
				case "selectHotelInlist":
					result = selectGivenHotelInalist(objectkeys,datakeys);
					break;
				case "verifyTextPresent":
					result = verifyTextPresent(objectkeys,datakeys);
					break;
				case "switchtoWindow":
					result = switchtoWindow();
					break;
				case "verify_BookingConfirmationDetailsprice":
					result=verify_BookingConfirmationDetailsprice(objectkeys);
					break;
				case "selectDropdown":
					result =selectDropdown(objectkeys,datakeys);
					break;
				case "moveCusoronElement":
					result =moveCusoronElement(objectkeys);
					break;
				case "waitForElementPresent":
					result = waitForElementPresent(objectkeys);
					break;
				case "waitForInvisibilityElement":
					result = waitForInvisibilityElement(objectkeys);
					break;
				default:
					System.out.println("Not Matching Keyword :"+keyword);
					break;
				}
				
				if(result!=null){
					if(result.equals("Pass"))Keywords.xls.setCellData1("Test Steps", "Status", (rNum-1), "Executed");
					else Keywords.xls.setCellData1("Test Steps", "Status", (rNum-1), "Not Executed");
				}
				
			}

		}
		
		
	}


	public String waitForElementPresent(String objectkeys) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getBylocator(objectkeys)));
		return "Pass";
	}
	
	public String waitForInvisibilityElement(String objectkeys) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocator(objectkeys)));
		
		return "Pass";
		
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
	
	public String verifySuccessMsg(String objectkeys, String string) throws InterruptedException, Exception {
		
		String name =objectkeys;
	
		if(name.contains("|")){
			int countnum= StringUtils.countMatches(name, "|");
			for(int i=0; i<=countnum;i++){
				if(getWebElements(name.split("\\|")[i]).size()!=0){
					System.out.println(name.split("\\|")[i]);
					//getWebElement(name.split("\\|")[i]);
					Thread.sleep(3000);
					System.out.println(getWebElement(name.split("\\|")[i]).getText());
					
					if(getWebElement(name.split("\\|")[i]).getText().equals(string)){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						//Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), string);
						break;
					}
				}
			}
			}
		else{
			if(getWebElement(objectkeys).getText().equals(string)){
				Assert.assertEquals(getWebElement(objectkeys).getText(), string);
			}
		}
		return "Pass";
			
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
		//Thread.sleep(2000);
		//waitForElementVisible(getWebElement("homepage.datepicker.leftside.monthyear"));
		waitForElementPresent("homepage.datepicker.leftside.monthyear");
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
			//elementClickableWait(getWebElement("homepage.datepicker.nexticon"));
			Thread.sleep(2000);
			getWebElement("homepage.datepicker.nexticon").click();
			chooseDate(datakeys);
		}
		return "Pass";
	}
	
	public String chooseDateReturn(String datakeys) throws Exception {
		String day = datakeys.split("-")[0];
		String month_year = datakeys.split("-")[1];
		System.out.println(day);
		System.out.println(month_year);
		Thread.sleep(3000);
		//waitForElementVisible(getWebElement("homepage.datepicker.leftside.monthyear.return"));
		String websiteLeftsiteMonthYear= getWebElement("homepage.datepicker.leftside.monthyear.return").getText();
		String websiteRightsiteMonthYear= getWebElement("homepage.datepicker.rightside.monthyear.return").getText();
		if(month_year.toLowerCase().equals(websiteLeftsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = getWebElements("homepage.datepicker.daylist.first.return");
		
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(month_year.toLowerCase().equals(websiteRightsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = getWebElements("homepage.datepicker.daylist.second.return");
			
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(getWebElements("homepage.datepicker.nexticon.return").size()!=0){
			//System.out.println(getWebElement("homepage.datepicker.nexticon"));
			getWebElement("homepage.datepicker.nexticon.return").click();
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
	
	public String chooseChildrenswiththeirAge(String ageWithNoChild) throws Exception{
		
		int no_ofChild= Integer.parseInt(ageWithNoChild.split("\\|")[0]);
		driver.findElement(By.xpath("//*[@id='js-child_counter']/li["+Integer.parseInt(ageWithNoChild.split("\\|")[0])+"]")).click();
		for(int i=0; i<no_ofChild;i++){
			driver.findElement(By.xpath("//*[@id='allPaxAge']/div[2]/ul/li['"+Integer.parseInt(ageWithNoChild.split("\\|")[i+1])+"']")).click();
		}
		getWebElement("homepage.hotel.roomguests.done").click();
		return "Pass";
		
	}
	public String waitForTextPresent(String objectkeys, String datakeys) throws Exception{
		
		WebElement element = getWebElement(objectkeys);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.textToBePresentInElement(element, datakeys));
		
		return "Pass";
	}
	
	public String scrollDownUntilCountMatch(String objectkeys) throws Exception{
		
		List<WebElement> hotelNameslist=getWebElements(objectkeys);
		
		int total = hotelNameslist.size();
		Actions act = new Actions(driver);
		int newCount=0;
		while(newCount!=total){
			
			act.moveToElement(hotelNameslist.get(total-1)).build().perform();
			act.moveToElement(hotelNameslist.get(newCount+1)).build().perform();
			newCount=total;
			hotelNameslist=getWebElements(objectkeys);
			act.moveToElement(hotelNameslist.get(total-1)).build().perform();
			
			//waitForElementVisible(hotelNameslist.get(total));
			Thread.sleep(4000);
			hotelNameslist=getWebElements(objectkeys);
			total = hotelNameslist.size();
			System.out.println("Scrolling Down. Now the Count is :"+total);
		}
		System.out.println("Final COunt is :"+total);
		return "Pass";
	}
	
	public String waitForVisibilityOfElement(String objectkeys,int seconds) throws Exception{
		WebElement element = getWebElement(objectkeys);
		WebDriverWait wait = new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		return "Pass";
	}
	
	public String selectGivenHotelInalist(String objectkeys, String datakeys) throws Exception{
		System.out.println(objectkeys.split("\\|")[0]);
		List<WebElement> hotelNamelist = getWebElements(objectkeys.split("\\|")[0]);
		List<WebElement> hotelPricelist=getWebElements(objectkeys.split("\\|")[1]);
		List<WebElement> hotelSearchImg= getWebElements(objectkeys.split("\\|")[2]);
		Actions act = new Actions(driver);
		for(int i=0; i<hotelNamelist.size();i++){
			System.out.println(hotelNamelist.get(i).getText());
			if(hotelNamelist.get(i).getText().equals(datakeys)){
				act.moveToElement(hotelSearchImg.get(i)).build().perform();
				System.out.println("Selected Hotel name is :"+hotelNamelist.get(i).getText());
				System.out.println("Selected Hotel min Price :"+hotelPricelist.get(i).getText());
				//hotelNamelist.get(i).click();
				hotelSearchImg.get(i).click();
			}
		}
		return "Pass";
		
	}
	
	public String verifyTextPresent(String objectkeys, String datakeys) throws Exception{
		
		//getWebElement(objectkeys).getText();
		Assert.assertTrue(getWebElement(objectkeys).getText().equals(datakeys), "Given Hotel only is Opened");
		return "Pass";
		
	}
	
	public String clickOngivenName(String datakeys) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("(//*[contains(text(),'"+datakeys+"')])[2]"))).build().perform();
		driver.findElement(By.xpath("(//*[contains(text(),'"+datakeys+"')])[2]")).click();
		
		return "Pass";
	}
	
	public String verify_BookingConfirmationDetailsprice(String objectkeys) throws NumberFormatException, Exception {
		//Integer.parseInt(getWebElement("hotel.viewpage.roomonly.price").getText().replace(",", ""));
		
		//div[@mt-class='room_details_visible']/div[@class='room-info clearfix' and @list_view_info_id='0']/div/div[2]/a
		//div[@mt-class='room_details_visible']/div[@class='room-info clearfix' and @list_view_info_id='0']/div/div[1]/div[2]/div/p[2]/span[2]
		
		waitForElementPresent(objectkeys.split("\\|")[5]);
		int roomprice = 0, servicecharge= 0, coupon=0, promodiscount=0, extracost=0, total =0;
		
		if(getWebElements(objectkeys.split("\\|")[0]).size()==1){
			roomprice = Integer.parseInt(getWebElement(objectkeys.split("\\|")[0]).getText().replace(",", ""));
		}
		if(getWebElements(objectkeys.split("\\|")[1]).size()==1){
			servicecharge = Integer.parseInt(getWebElement(objectkeys.split("\\|")[1]).getText().replace(",", ""));
		}
		if(getWebElements(objectkeys.split("\\|")[2]).size()==1){
			coupon = Integer.parseInt(getWebElement(objectkeys.split("\\|")[2]).getText().replace(",", ""));
		}
		if(getWebElements(objectkeys.split("\\|")[3]).size()==1){
			promodiscount = Integer.parseInt(getWebElement(objectkeys.split("\\|")[3]).getText().replace(",", ""));
		}
		if(getWebElements(objectkeys.split("\\|")[4]).size()==1){
			extracost = Integer.parseInt(getWebElement(objectkeys.split("\\|")[4]).getText().replace(",", ""));
		}
		if(getWebElements(objectkeys.split("\\|")[5]).size()==1){
			total = Integer.parseInt(getWebElement(objectkeys.split("\\|")[5]).getText().replace(",", ""));
		}
		 
		
	
		
		System.out.println(roomprice);
		System.out.println(servicecharge);
		System.out.println(coupon);
		System.out.println(promodiscount);
		System.out.println(extracost);
		System.out.println(total);
		int actualprice=(roomprice+servicecharge+extracost)-(coupon-promodiscount);
		Assert.assertTrue(total==actualprice, "Price is Matching");
		
		return "Pass";
		
	}




	public String switchtoWindow() throws InterruptedException{
		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println(browserName);
		if(browserName.equals("firefox")){
			String parent_window = driver.getWindowHandle();
			for(String windowHandles :driver.getWindowHandles()){
				driver.switchTo().window(windowHandles);
			}
		}
		else if(browserName.equals("chrome")){
			Thread.sleep(3000);
			String oldTab=driver.getWindowHandle();
			//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
			 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		     //newTab.remove(oldTab);
		     driver.switchTo().window(newTab.get(1));
			 
		}
		
		return "Pass";
		
	}
	
	public void elementClickableWait(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String selectDropdown(String objectkeys,String datakeys) throws Exception{
		Select select = new Select(getWebElement(objectkeys));
		select.selectByVisibleText(datakeys);
		return "Pass";
	}
	
	public String moveCusoronElement(String objectkeys) throws Exception{
		
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(objectkeys)).build().perform();
		return "Pass";
		
	}
	}
