package practices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LinkedInTest {

	WebDriver driver;
	String email="velbca01@gmail.com";
	String pwd="velmurugan1993";
	@Test
	public void test() throws InterruptedException{
		
		//https://www.linkedin.com/company-beta/2741657/
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.linkedin.com/uas/login?goback=&trk=hb_signin");
		
		
		loginpage(email,pwd);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[@class='launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl']")));
		
		System.out.println(driver.findElement(By.xpath("//h4[@class='launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl']")).getText());
		driver.navigate().to("https://www.linkedin.com/company-beta/2741657/");
		//wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//h2[@class='org-about-us-organization-description__about-us-heading Sans-21px-black-85% mb4']")), "About Us"));
		Thread.sleep(7000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='org-about-us-organization-description__about-us-heading Sans-21px-black-85% mb4']")));
		
		String text2 = driver.findElement(By.xpath("//h2[@class='org-about-us-organization-description__about-us-heading Sans-21px-black-85% mb4 ']")).getText();
		System.out.println(text2);
		
		driver.findElement(By.xpath("//input[@class='ember-text-field ember-view']")).sendKeys("Rajkumar");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='type-ahead-results ember-view']/li[1]/figure")).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//div[@class='pv-top-card-section__information mt3']/h1")).getText());
		//driver.findElement(By.id("org-about-company-module__show-details-btn")).click();
		//String text1 = driver.findElement(By.xpath("//div[@class='org-about-company-module__company-page-url truncate Sans-15px-black-70% mb3']/a")).getText();
		//System.out.println(text1);
	}
	public void loginpage(String email2, String pwd2) {
		
		driver.findElement(By.id("session_key-login")).sendKeys(email2);
		driver.findElement(By.id("session_password-login")).sendKeys(pwd2);
		driver.findElement(By.id("btn-primary")).click();
	}
}
