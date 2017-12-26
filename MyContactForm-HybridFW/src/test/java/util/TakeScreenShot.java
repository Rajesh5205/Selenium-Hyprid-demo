package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
	
    public static void captureScreenshot(WebDriver driver,String filename)  {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			System.out.println("Taking snapshot of the page");
			FileUtils.copyFile(scrFile, new File("./src/test/java/screenshots/"+filename+".jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
}
