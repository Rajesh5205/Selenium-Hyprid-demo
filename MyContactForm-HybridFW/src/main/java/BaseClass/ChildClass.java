package BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ChildClass extends ParentClass {
@Test(priority=3,description=" it is going to perform valid login")

	public void validLogin() {
		WebElement username_MCF = driver.findElement(By.id("user"));
		username_MCF.sendKeys("Prabhu123");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("12345");
		// Password_MCF.sendKeys("12345");

		driver.findElement(By.cssSelector("#right_col_top > form > div > input")).click();
	}
@Test(priority=2,description=" it is going to perform ivlaid login")
	public void invalidLogin() {
		WebElement username_MCF = driver.findElement(By.id("user"));
		username_MCF.sendKeys("Prabhu123");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("123456666");
		// Password_MCF.sendKeys("12345");

		driver.findElement(By.cssSelector("#right_col_top > form > div > input")).click();
	}
@Test(priority=1,description=" it is going to perform illegal login")
	public void illegalalidLogin() {
		WebElement username_MCF = driver.findElement(By.id("user"));
		username_MCF.sendKeys("Prabhu123");

		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("@@@@@@@");
		// Password_MCF.sendKeys("12345");

		driver.findElement(By.cssSelector("#right_col_top > form > div > input")).click();
	}
}
