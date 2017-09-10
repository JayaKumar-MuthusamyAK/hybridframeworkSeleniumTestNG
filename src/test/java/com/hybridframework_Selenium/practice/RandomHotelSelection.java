package com.hybridframework_Selenium.practice;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class RandomHotelSelection {
	WebDriver driver;
	public WebDriverWait wait;
  @Test
  public void randomHotelSelect() throws InterruptedException {
	  
	  driver.get("http://www.makemytrip.com/hotels");
	  Thread.sleep(5000);
	  driver.findElement(By.id("hp-widget__sDest")).click();
	  driver.findElement(By.xpath("//*[@class='ui-menu-item']/div/p[1]/span[1][text()='Mumbai, India']")).click();
	  driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']/table/tbody/tr/td/a[text()='24']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//div[@class='dateFilterReturn hasDatepicker']//div[@class='ui-datepicker-group ui-datepicker-group-first']/table/tbody/tr/td/a[text()='29']")).click();
	  driver.findElement(By.xpath("//*[@id='searchBtn']")).click();
	  Thread.sleep(2000);
	
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='hotel_lis_main_sec']/div[1]/span[2]")));
	  
	  if(driver.findElement(By.xpath("//a[@id='listing-error-search1']")).isDisplayed()){
		  System.out.println("oops error");
	  }
	  else{
	  //wait = new WebDriverWait(driver, 45);
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='hotel_lis_main_sec']/div[7]/header/div/div[1]/h1/span"))));
	  
	  driver.findElement(By.xpath("//span[@class='o-f-label'][text()='Location']")).click();
	  driver.findElement(By.xpath("//*[@class='filterListRow']/a[text()='Thane']")).click();
	  
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='hotel_lis_main_sec']/div[7]/header/div/div[1]/h1/span"))));
	  
	  List<WebElement> listof_name=driver.findElements(By.xpath("//span[@id='js-hotelName-0']"));
	  
	  Actions act = new Actions(driver);
	  
	  
	  while(driver.findElements(By.xpath("//div[@class='bounce']")).size()!=0){
		  
		  System.out.println("Scrolling");
		  act.moveToElement(listof_name.get(listof_name.size()-1)).build().perform();
		  Thread.sleep(3000);
		  listof_name=driver.findElements(By.xpath("//span[@id='js-hotelName-0']"));
		  act.moveToElement(listof_name.get(listof_name.size()-3)).build().perform();
		  if(driver.findElement(By.xpath("//div[@class='bounce']")).isDisplayed()){
			  break;
		  }
	  }
	  
	 // List<WebElement> imaglink = driver.findElements(By.xpath("//img[@class='img-responsive']"));
	 /* for(int i=0; i<listof_name.size();i++){
		  if(listof_name.get(i).getText().equals("Hotel Sea N Rock Pure Veg")){
			  act.moveToElement(listof_name.get(i)).build().perform();
			  listof_name.get(i).click();
		  }
	  }*/
	  System.out.println("Choose any hotel");
	  act.moveToElement(driver.findElement(By.xpath("//*[@id='hotel_lis_main_sec']/div[7]/header/div/div[1]/h1/span"))).build().perform();
	  Thread.sleep(2000);
	  System.out.println(listof_name.get(0).getText());
	  listof_name.get(0).click();
	 
	  }
	  
	  
  }
  @BeforeClass
  public void beforeClass() {
	  
	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

}
