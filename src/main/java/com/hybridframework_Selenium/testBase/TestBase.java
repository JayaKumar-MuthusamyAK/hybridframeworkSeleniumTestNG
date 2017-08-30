package com.hybridframework_Selenium.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;

	public Properties prop;
	public FileInputStream fileinputstream;
	public static ExtentReports extentreport;
	public static ExtentTest extenttest;
	
	static{
		Calendar cal= Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		extentreport = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/com/hybridframework_Selenium/extentReport/"+formater.format(cal.getTime())+".html");
		try {
			extentreport.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName()).addSystemInfo("Environment", System.getenv("JAVA_HOME")).addSystemInfo("User Name", "Jakay M");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void selectBrowser(String browserName) {

		if(System.getProperty("os.name").contains("Windows")){
		if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", false);
			driver = new FirefoxDriver(cap);
			driver.manage().window().maximize();
			log("Opening the "+browserName+"Browser in "+System.getProperty("os.name")+"System");

		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			log("Opening the "+browserName+"Browser in "+System.getProperty("os.name")+"System");

		}
		}
		else if(System.getProperty("os.name").contains("Mac")){
			if (browserName.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("marionette", false);
				driver = new FirefoxDriver(cap);
				driver.manage().window().maximize();
				log("Opening the "+browserName+"Browser in "+System.getProperty("os.name")+"System");

			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
				driver = new ChromeDriver();
				log("Opening the "+browserName+"Browser in "+System.getProperty("os.name")+"System");

			}
			}
		
	}

	public void loadproperties() throws IOException {
		prop = new Properties();
		try {
			
			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\config\\confiq.properties");
			prop.load(fileinputstream);

			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\pageLocator\\homepage.properties");
			prop.load(fileinputstream);
			 
			
			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\pageLocator\\listingpage.properties");
			prop.load(fileinputstream);
			
			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\pageLocator\\flightreviewpage.properties");
			prop.load(fileinputstream);
			
			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\pageLocator\\hotelviewpage.properties");
			prop.load(fileinputstream);
			
			fileinputstream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\hybridframework_Selenium\\pageLocator\\bookingconfirmdetailspage.properties");
			prop.load(fileinputstream);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void navigateGivenDomain(String objectkeys){
		log("Navigating the URL. The Domain Name is "+ prop.getProperty(objectkeys));
		driver.get(prop.getProperty(objectkeys));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public  By getBylocator(String locator) throws Exception{
		
		return Bylocator(prop.getProperty(locator));
	}

	public static By Bylocator(String locator) throws Exception{
		
		 	String[] split = locator.split(":");
		 	
			String locatorType = split[0];
			String locatorValue = split[1];
			if (locatorType.toLowerCase().equals("id"))
				return By.id(locatorValue);
			else if (locatorType.toLowerCase().equals("name"))
				return By.name(locatorValue);
			else if ((locatorType.toLowerCase().equals("classname"))
					|| (locatorType.toLowerCase().equals("class")))
				return By.className(locatorValue);
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return By.className(locatorValue);
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return By.linkText(locatorValue);
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return By.partialLinkText(locatorValue);
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return By.cssSelector(locatorValue);
			else if (locatorType.toLowerCase().equals("xpath"))
				return By.xpath(locatorValue);
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		
	}
	public static WebElement getLocator(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public static List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(prop.getProperty(locator));
		
	}
	
	public List<WebElement> getWebElements(String locators) throws Exception{
		return getLocators(prop.getProperty(locators));
		
	}
	
	@BeforeMethod
	public void beforeExecution(Method result){
		
		extenttest = extentreport.startTest(result.getName());
	}
	
	@AfterMethod
	public void afterExecution(ITestResult result){
		getStatus(result);
		
	}



	@AfterClass
	public void endingTest(){
		closeBrowser();
		
	}

	public void closeBrowser() {
		if(driver!=null){
			//driver.quit();
		}
		extentreport.endTest(extenttest);
		extentreport.flush();
		
	}

	public void getStatus(ITestResult result) {

		if(result.getStatus()==ITestResult.SUCCESS){
			//Keywords.xls.setCellData("Test Data", "Status", TestUtils.getNum(result.getMethod().getMethodName(), Keywords.xls,"Status"), "PASS");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "PASS");
			extenttest.log(LogStatus.PASS, "test is pass"+result.getName());
		}
		else if(result.getStatus()==ITestResult.FAILURE){
			//Keywords.xls.setCellData("Test Data", "Status", TestUtils.getNum(result.getMethod().getMethodName(), Keywords.xls,"Status"), "FAIL");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "FAIL");
			extenttest.log(LogStatus.ERROR, result.getName()+"test is failed"+result.getThrowable());
			extenttest.log(LogStatus.FAIL, result.getName()+"test is failed"+extenttest.addScreenCapture(catureScreen("")));
		}
		else if(result.getStatus()==ITestResult.SKIP){
			//Keywords.xls.setCellData("Test Data", "Status", TestUtils.getNum(result.getMethod().getMethodName(), Keywords.xls,"Status"), "SKIP");
			Keywords.xls.setCellDataInparticularCell(result.getMethod().getMethodName(), "Test Data", "Status", "SKIP");
			extenttest.log(LogStatus.SKIP, result.getName()+"test is skip"+result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.STARTED){
			extenttest.log(LogStatus.INFO, result.getName()+"Test is Started");
		}
		
		
	}

	public String catureScreen(String filename) {
	
		if(filename==""){
			filename = "blank";
		}
		File destFile =null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			String path = new File(System.getProperty("user.dir"))+"//src//main//java//com//hybridframework_Selenium//";
			destFile = new File ((String)path+"FailureScreenShot//"+simpleformat.format(calendar.getTime())+".png");
			FileUtils.copyFile(scrFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	public void log(String data){
		
		log.info(data);
		Reporter.log(data);
	}

}
