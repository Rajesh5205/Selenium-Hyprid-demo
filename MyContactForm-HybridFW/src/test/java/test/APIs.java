package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static test.DriverScript.*;
import util.TakeScreenShot;

public class APIs {
	
	public WebDriver driver;
	private static short counter = 0;
	
	//open the chrome browser
	public String openBrowser (String object, String value) throws IOException {
		
		counter ++;
		
		LOGS.debug("Opening the browser");
		LOGS.debug("object = " + CONFIG.getProperty(object) + ". value = " + CONFIG.getProperty(value) + ".");
		
		System.setProperty(CONFIG.getProperty(object), CONFIG.getProperty(value));
		
		driver = new ChromeDriver();
		
		return Constants.PASS;
		
	}
	
	//navigate to a url
	public String navigate (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "navigate" + counter;
		
		LOGS.debug ("Navigating to URL");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = No value for this method.");
		
		try {
			driver.navigate().to(OR.getProperty(object));
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error ("Navigation to URL failed " + e.getMessage());
			return Constants.FAIL;
		}
		
		return Constants.PASS;
	}

	//get the required url
	public String loadPage (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "loadPage" + counter;
		
		LOGS.debug ("Load the URL");
		LOGS.debug("object = " + CONFIG.getProperty(object) + ". value = No value for this method .");
		
		try {
			driver.get(CONFIG.getProperty(object));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error ("Failed to load the page " + e.getMessage());
			return Constants.FAIL;
		}
		
		return Constants.PASS;
		
	}
	
	//login
	public String login (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "login" + counter;
		
		LOGS.debug ("Logging in");

		String [] objects = object.split("\\|");
		String username = objects [0];
		String password = objects [1];
		String submitbutton = objects [2];
		
		String [] values = value.split("\\|");
		String usernametext = values [0];
		String passwdtext = values [1];

		LOGS.debug("username object = "+username);
		LOGS.debug("password object = "+password);
		LOGS.debug("button object = "+submitbutton);
		LOGS.debug("username value = "+usernametext);
		LOGS.debug("password value = "+passwdtext);
		
		try {
			driver.findElement(By.id(OR.getProperty(username))).sendKeys(CONFIG.getProperty(usernametext));
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			driver.findElement(By.id(OR.getProperty(password))).sendKeys(CONFIG.getProperty(passwdtext));
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			driver.findElement(By.name(OR.getProperty(submitbutton))).click();
			driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error ("Failed to login " + e.getMessage());
			return Constants.FAIL;
		}
		
		return Constants.PASS;
	}
	
	//logout
	public String logout (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "logout" + counter;
		
		LOGS.debug("Logging out");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = No value for this method .");
		
		try {
			driver.findElement(By.linkText(OR.getProperty(object))).click();
		}catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error ("Failed to logout " + e.getMessage());
			return Constants.FAIL;
		}
		
		return Constants.PASS;
	}
	
	//click a link using the linktext
	public String clickLink (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "clickLink" + counter;
		
		LOGS.debug("Clicking the link");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = No value for this method .");
		
		try {
			driver.findElement(By.linkText(OR.getProperty(object))).click();
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to click the link " + e.getMessage());
			return Constants.FAIL;
		}
				
		return Constants.PASS;
	}
	
	//click a button using its name
	public String clickButton (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "clickButton" + counter;
		
		LOGS.debug ("Clicking the button");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = No value for this method .");
		
		try {
			driver.findElement(By.name(OR.getProperty(object))).click();
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to click the button " + e.getMessage());
			return Constants.FAIL;
		}
				
		return Constants.PASS;
	}
	
	//select a dropdown element using its id and visible text
	public String selectDropdown (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "selectDropdown" + counter;
		
		LOGS.debug ("Selecting a drop down element");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = " + value.trim() + ".");
		
		try {
			Select dropdown = new Select(driver.findElement(By.id(OR.getProperty(object))));
			dropdown.selectByVisibleText(value.trim());			
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to select the dropdown element " + e.getMessage());
			return Constants.FAIL;
		}
		
		return Constants.PASS;
	}
	
	//select radiobutton using name and its value attribute
	public String selectRadioButton (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "selectRadioButton" + counter;
		
		LOGS.debug ("Selecting a Radio button");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = " + value.trim() + ".");
		
		try {
			List <WebElement> radiobuttons = driver.findElements(By.name(OR.getProperty(object)));
			
			for (WebElement mybutton : radiobuttons) {						 
					 if (mybutton.getAttribute("value").equalsIgnoreCase(value)){				 
						 mybutton.click();
						 return Constants.PASS;
					 }
			}			
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to select the radio button " + e.getMessage());
			return Constants.FAIL;
		}
		return Constants.FAIL;
	}
	
	//select a check box using name
	public String selectCheckBox (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "selectCheckBox" + counter;
		
		LOGS.debug ("Selecting a Check Box");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = No value for this method.");
		
		try {
				WebElement mybox = driver.findElement(By.name(OR.getProperty(object)));
				if (!(mybox.isSelected())) {
					mybox.click ();
					return Constants.PASS;
				}	
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to select the check box " + e.getMessage());
			return Constants.FAIL;
		}
		return Constants.FAIL;
	}
	
	//write in a text box using id
	public String writeInTextBox (String object, String value) throws IOException {
		
		counter ++; 
		String filename = "writeInTextBox" + counter;
		
		LOGS.debug ("Writing in a text box");
		LOGS.debug("object = " + OR.getProperty(object) + ". value = " + value.trim() + ".");
		
		try {
			WebElement textbox = driver.findElement(By.id(OR.getProperty(object)));
			textbox.clear();
			textbox.sendKeys(value);
		} catch (Exception e) {
			TakeScreenShot.captureScreenshot(driver, filename);
			LOGS.error("Unable to write in the text box " + e.getMessage());
			return Constants.FAIL;		
		}
		
		return Constants.PASS;
	}
	
	//close browser
    public  String closeBrowser(String object, String value) throws IOException{
    	
		counter ++; 
		String filename = "closeBrowser" + counter;
		
        LOGS.debug("Closing the browser");
        
        try{
            driver.close();
        }catch(Exception e){
        	TakeScreenShot.captureScreenshot(driver, filename);
            return Constants.FAIL;
        }
        
        return Constants.PASS;

    }
    
    //delete the form by locating it from the table using the formname. Click the delete link associated with it and confirm deletion of the form.
    public String deleteForm (String object, String value) throws IOException {
    	
		counter ++; 
		String filename = "deleteForm" + counter;
		
    	LOGS.debug("Delete the form");
    	
    	String objects [] = object.split("\\|");
    	String tableclassname = objects [0];
    	String deletebutton = objects [1];
    	String deletetextbox = objects [2];
    	String buttonname = objects [3];
    	
    	LOGS.debug("tableclassname = " + tableclassname + ". deletebutton = " + deletebutton + ". deletetextbox = " + deletetextbox + ". buttonname = " + buttonname + ".");
    	LOGS.debug("formname = " + value);
    	
     	try {
    		List<WebElement> tablerows = driver.findElements(By.className(OR.getProperty(tableclassname)));
    		int tablesize = tablerows.size();
    		String rowname = "";
    		CharSequence formname = value;
    		
    		for (short index = 0; index < tablesize; index ++)
    		{
    		   rowname = tablerows.get(index).getText();

    		   if (rowname.contains(formname))
    		   {
    			   //Delete it
    			   LOGS.debug("I am deleting the form");
    			   
    			   tablerows.get(index).findElement(By.linkText(OR.getProperty(deletebutton))).click();
    			   //Confirm deletion page
    			   driver.findElement(By.id(OR.getProperty(deletetextbox))).sendKeys("yes");
    			   driver.findElement(By.name(OR.getProperty(buttonname))).click();
    			   break;
    		   }
    		}
    	} catch (Exception e) {
    		TakeScreenShot.captureScreenshot(driver, filename);
    		LOGS.error("Cannot delete the form " + e.getMessage());
    		return Constants.FAIL;
    	}
     	
     	return Constants.PASS;
    }
	    
}
