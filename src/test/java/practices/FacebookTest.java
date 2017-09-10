package practices;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FacebookTest {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 60);
	@BeforeClass
	public void logintest(){
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("velbca01@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("velmurugan93");
		driver.findElement(By.id("loginbutton")).click();
		
	}
	
	@Test
	public void searchProfile(){
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='appsNav']"))));
		driver.findElement(By.xpath("//div[@id='u_h_2']/input[1]")).sendKeys("velmurugan");
		driver.findElement(By.xpath("//button[@data-testid='facebar_search_button']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(By.xpath("//span[@itemcomponent='span']/span[1]")), "People"));
		System.out.println(driver.findElement(By.xpath("//a[@id='js_1qb']/div")).getText());
	}
	
	/*@DataProvider
	public Object[][] giveData(){
		
		
		
	}*/
}
