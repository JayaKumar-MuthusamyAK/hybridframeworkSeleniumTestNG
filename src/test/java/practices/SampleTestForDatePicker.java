package practices;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SampleTestForDatePicker {
	WebDriver driver;
	
	@Test
	public void datePickerselect(){
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		driver = new FirefoxDriver(cap);
		
		driver.get("https://www.makemytrip.com/hotels");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//*[@id='hp-widget__chkIn']")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']/div")).getText());
		driver.findElement(By.xpath("//div[2]/table/tbody/tr[3]/td[2]/a")).click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='ui-datepicker-title'])[1]"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='dateFilterReturn hasDatepicker']//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']/div")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='dateFilterReturn hasDatepicker']//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-left']/div")).getText());
		driver.findElement(By.xpath("//div[2]/table/tbody/tr[3]/td[4]/a")).click();
	}

}
