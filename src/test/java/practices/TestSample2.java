package practices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestSample2 {

	WebDriver driver;
	@Test
	public void test(){
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		driver = new FirefoxDriver(cap);
		driver.get("https://flights.makemytrip.com/makemytrip/review/O/O/E/1/0/0/BOM_DEL_20-09-2017/0-false-2299-2299-false-"
				+ "false|BOM-DEL-OS-SG-152-false-false-false-1505872200000-1505880300000-1859-0-0-236-UHBO152-U"
				+ "-OSOWDHRUV/18054ac06a2|48ba|a51c|4330|3b03e9da/odc/asyncResponsiveReview#/review");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div[2]/div[3]/div[1]/div[2]/div[1]/div/p[1]/span[2]/strong")));
		String text = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[3]/div[1]/div[2]/div[1]/div/p[1]/span[2]/strong")).getText().replace("Rs.", "").replace(",", "").trim();
		System.out.println(text);
		//(//strong[@class='ng-binding'])[6]
	}
}
