package com.hybridframework_Selenium.practice;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class SearchingFlights {
	
	WebDriver driver;
	String fromCity="Delhi";
	String toCity="Mumbai";
	String fromOptionXpath="//*[@id='ui-id-1']/li/div/p/span[1]";
	String toOptionxpath="//*[@id='ui-id-2']/li/div/p/span[1]";
	String bookingdate="20-October 2017";
	int adults=2;
	int childrens=0;
	int infants =0;
	String classname="Economy";
	
	@Test
	public void chooseFlights() throws InterruptedException{
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.makemytrip.com/");
		driver.findElement(By.id("hp-widget__sfrom")).click();
		
		selectFrom_toCities(fromOptionXpath,fromCity);
		Thread.sleep(3000);
		selectFrom_toCities(toOptionxpath,toCity);
		driver.findElement(By.id("hp-widget__depart")).click();
		String day = bookingdate.split("-")[0];
		String month_year = bookingdate.split("-")[1];
		selectDate(day,month_year);
		driver.findElement(By.id("hp-widget__paxCounter")).click();
		chooseAdults(adults);
		chooseChildrans(childrens);
		chooseInfants(infants);
		driver.findElement(By.xpath("//*[@id='js-filterOptins']/div/div[9]/div/p/a")).click();
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String text =(String) js.executeScript("return arguments[0].value;",driver.findElement(By.id("hp-widget__paxCounter")));
		System.out.println(text);
		System.out.println("Text1 -->"+driver.findElement(By.cssSelector("#hp-widget__paxCounter")).getAttribute("value"));
		driver.findElement(By.id("hp-widget__class")).click();
		chooseClass(classname);
		driver.findElement(By.id("searchBtn")).click();
		String webtotal= driver.findElement(By.xpath("//span[@class='light_gray ng-binding ng-scope']")).getText();
		System.out.println(webtotal);
		String stotal= webtotal.split(" ")[0];
		int flightavailabletotal= Integer.parseInt(stotal);
		Actions act = new Actions(driver);
		//div[@class='listing_top clearfix']/div[3]/div[1]/span[1]/span[2]
		List<WebElement> listofflightsnames= driver.findElements(By.xpath("//span[@class='block logo_name hidden-xs visible-stb light_gray flt_number_less600 ng-binding ng-scope']"));
		List<WebElement> listofflightsprice = driver.findElements(By.xpath("//span[@class='num ng-binding']"));
		int flightnamelisttotal = listofflightsnames.size();
		List<String>timings=new LinkedList<String>();
		int newcount=0;
		while(newcount<flightnamelisttotal){
			newcount=flightnamelisttotal;
			act.moveToElement(listofflightsnames.get(flightnamelisttotal-1)).build().perform();
			Thread.sleep(2000);
			listofflightsnames= driver.findElements(By.xpath("//span[@class='block logo_name hidden-xs visible-stb light_gray flt_number_less600 ng-binding ng-scope']"));
			flightnamelisttotal = listofflightsnames.size();
			System.out.println(flightnamelisttotal);
			//span[@class='pull-left airline_info_detls']
		}
		Thread.sleep(3000);
		int j=0;
		for(WebElement ele : listofflightsnames){
			System.out.println((j+1)+"."+ele.getText());
			j++;
		}
		/*for(int i=0; i<flightnamelisttotal;i++){
			Thread.sleep(500);
			System.out.println((i+1)+"."+listofflightsnames.get(i).getText());
			
		}*/
		
		System.out.println("-------------------------------------------------------------");
		//span[@class='pull-left airline_info_detls']/span[contains(text(),'9W-301')]
		//div/div[@class='listing_top clearfix']/div[3]//a[contains(text(),'Book')]
		//https://flights.makemytrip.com/makemytrip/review/O/O/E/2/0/0/DEL_BOM_20-09-2017/0-false-2103-2103-false-false|DEL-BOM-6E-6E-171-false-false-false-1505864400000-1505872200000-821-0-0-1067-QF15AP171-Q-6EFAMILY/2b45e180d28|043a|4a40|9037|348a0c04/odc/asyncResponsiveReview#/review
		int count =0;
		HashMap<String, List<String>> flightpricemap= new HashMap<String, List<String>>();

		while(count<flightnamelisttotal){
			if(listofflightsnames.get(count).getText().contains("6E-167")){
				List<WebElement> flight_timing = driver.findElements(By.xpath("(//div[@class='listing_top clearfix'])["+(count+1)+"]//span[@class='block timeCa RobotoRegular ng-binding'][1]"));
				
				for(int i=0; i<flight_timing.size();i++){
					timings.add(flight_timing.get(i).getText());
				}
				System.out.println(listofflightsnames.get(count).getText());
				System.out.println(listofflightsnames.get(count+1).getText());
				//flightpricemap.put(listofflightsnames.get(count).getText(), listofflightsprice.get(count).getText());
				flightpricemap.put(listofflightsnames.get(count).getText(),timings);
				System.out.println("Times are ------------->"+timings);
				act.moveToElement(driver.findElement(By.xpath("(//span[@class='open_icon_markwrapper pull-left noPadTop'])["+(count+1)+"]"))).build().perform();
				driver.findElement(By.xpath("(//span[@class='open_icon_markwrapper pull-left noPadTop'])["+(count+1)+"]")).click();
				driver.findElement(By.xpath("(//*[contains(text(),'Fare details')])["+(count+1)+"]")).click();
				//System.out.println(driver.findElement(By.xpath("//tr[1]/td[@class='ng-binding'][2]")).getText());
				//System.out.println(driver.findElement(By.xpath("//tr[2]/td[@class='ng-binding']")).getText());
				//System.out.println(driver.findElement(By.xpath("//tr[5]/td[@class='ng-binding']")).getText());
				
				
				int mark1 = Integer.parseInt(driver.findElement(By.xpath("//tr[1]/td[@class='ng-binding'][2]")).getText().replace(",", "").split("Rs ")[1]);
				int mark2 = Integer.parseInt(driver.findElement(By.xpath("//tr[2]/td[@class='ng-binding']")).getText().replace(",", "").split("Rs ")[1]);
				int mark3 = Integer.parseInt(driver.findElement(By.xpath("//tr[5]/td[@class='ng-binding']")).getText().replace(",", "").split("Rs ")[1]);
				int calculatetot = mark1+mark2;
				System.out.println(calculatetot+"-"+mark3);
				if(mark3==calculatetot){
					System.out.println("TOTAL Calculation result Matching the actual result");
				}
				
				
				WebElement ele = driver.findElement(By.xpath("(//a[@class='btn btn-md select_button pull-right hidden-xs btn-primary-red'])["+(count+1)+"]"));
				act.moveToElement(ele).build().perform();
				ele.click();
				break;
			}
			count++;
		}
		
		verifyTimePrice(flightpricemap,timings);
		
	}
	public void verifyTimePrice(HashMap<String, List<String>> flightpricemap, List<String> timings) throws InterruptedException {
		Thread.sleep(3000);
		String text1 = driver.findElement(By.xpath("(//span[@class='block review_time RobotoRegular ng-binding'])[1]")).getText();
		System.out.println(text1);
		System.out.println(flightpricemap.get("6E-167"));
		System.out.println(timings.get(0));
		System.out.println(timings.get(1));
		if(timings.get(0).equals(text1)){
			System.out.println("Pass");
		}
		else{
			System.out.println("Fail");
		}
		/*for(Entry<String, List<String>> entry:flightpricemap.entrySet()){
			System.out.println("Mapping keys"+entry.getKey());
			System.out.println("Map values"+entry.getValue());
			
		}*/
		
		
	}
	public void chooseClass(String classnameString) {
		
		driver.findElement(By.xpath("//*[@id='js-classFilters']/div/label[.//text()='"+classnameString+"']")).click();
		
	}
	public void chooseInfants(int infantsCount) {
		if(infantsCount>=1){
			driver.findElement(By.xpath("//*[@id='js-infant_counter']/li["+infantsCount+"]")).click();
		}
		
	}
	public void chooseChildrans(int childrenCount) throws InterruptedException {
		if(childrenCount>=1){
			Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='js-child_counter']/li["+childrenCount+"]")).click();
		}
	}
	public void chooseAdults(int adultCount) throws InterruptedException {
		if(adultCount>=1){
			Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='js-adult_counter']/li["+adultCount+"]")).click();
		}
	}
	public void selectDate(String day1, String monthyear) throws InterruptedException {
	
		System.out.println(day1);
		System.out.println(monthyear);
		
		String websiteLeftsiteMonthYear= driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']/div")).getText();
		String websiteRightsiteMonthYear= driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-right']/div")).getText();
		if(monthyear.toLowerCase().equals(websiteLeftsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']/table/tbody/tr/td/a"));
		
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day1)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(monthyear.toLowerCase().equals(websiteRightsiteMonthYear.toLowerCase())){
			List<WebElement> websiteDatelist = driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']/table/tbody/tr/td/a"));
			
			for(int i=0; i<websiteDatelist.size();i++){
				if(websiteDatelist.get(i).getText().equals(day1)){
					System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
					
					websiteDatelist.get(i).click();
					break;
				}
			}
		}
		else if(driver.findElements(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).size()!=0){
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			selectDate(day1, monthyear);
		}
		
	}
	public void selectFrom_toCities(String xpath,String city) {
		List<WebElement> options = driver.findElements(By.xpath(xpath));
		for(WebElement opt: options){
			//System.out.println(opt.getText());
			if(opt.getText().contains(city)){
				opt.click();
				break;
			}
			
		}
	}
	
	public void reviewpageCalculation(){
		
		driver.findElement(By.xpath("(//span[@class='inline-block fare_ruleicn'])[2]")).click();
		driver.findElement(By.xpath("(//span[@class='pull-left col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right ng-binding'])[13]")).getText();
		driver.findElement(By.xpath("(//span[@class='pull-left col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right ng-binding'])[14]")).getText();
		driver.findElement(By.xpath("(//span[@class='pull-left col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right ng-binding'])[15]")).getText();
		driver.findElement(By.xpath("(//span[@class='pull-left col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right ng-binding'])[16]")).getText();
		System.out.println(driver.findElement(By.xpath("//p[@class='clearfix review_detls_strp total_base']/span[2]/strong[@class='ng-binding']")).getText());
		
		//keysIndiGo 6E-957
	}

}
/*List<WebElement> actual_times= driver.findElements(By.xpath("//span[@class='block review_time RobotoRegular ng-binding']"));
List<String> expected_result = new LinkedList<String>();
for(int i=0;i<actual_times.size();i++){
	System.out.println(actual_times.get(i).getText());
	expected_result.add(actual_times.get(i).getText());
}
for(Entry<String, List<String>> entry:flightpricemap.entrySet()){
	System.out.println("Mapping keys"+entry.getKey());
	System.out.println("Map values"+entry.getValue());
	System.out.println(expected_result);
}*/