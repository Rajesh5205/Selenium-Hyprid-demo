package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ParentClass {
	WebDriver driver;
	
	@BeforeTest
	public void setupBrowser(){
		
		System.setProperty("webdriver.chrome.driver", "/Users/prabhu/Downloads/chromedriver");
		 driver = new ChromeDriver();
		 driver.get("https://www.mycontactform.com/index.php");
		
		
	}
	
	
	@AfterTest
	public void closetheBrowser(){
		
	
		driver.quit();
		
	}
	

}
