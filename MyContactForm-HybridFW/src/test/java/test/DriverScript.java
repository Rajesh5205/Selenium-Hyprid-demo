package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DriverScript {
	
	public static Logger LOGS;
	public static Properties OR;
	public static Properties CONFIG;
	public static FileInputStream fis;
	public static Method [] method;
	public static int totalapis;
	public static APIs apis;
	public static XcelReader xcelfile;
	
	public static void main(String args[]) throws IOException, FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		//Start Logging
		LOGS = Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure("./src/test/java/config/log4j.properties");
		
		//Load the OR properties file
		FileInputStream fis = new FileInputStream ("./src/test/java/config/or.properties");
		OR = new Properties();
		OR.load(fis);
		fis.close();
		
		LOGS.debug("OR Property file loaded");
		
		//Load the CONFIG properties file
		fis = new FileInputStream ("./src/test/java/config/config.properties");
		CONFIG = new Properties ();
		CONFIG.load(fis);
		fis.close();
		
		LOGS.debug("CONFIG Property file loaded");
		
		//Load all the APIs
		apis = new APIs();
		method = apis.getClass().getMethods();
		totalapis = method.length;
		
		LOGS.debug("All the APIs loaded");
		
		//Load the excel file
		xcelfile = new XcelReader("/Users/jarvis/Downloads/DonloadsSelenium/Selenium Frameworks/MyContactForm-HybridFW/src/test/java/xcel/My Contact Form - Copy.xlsx");
		
		LOGS.debug("Loaded My Contact Form.xlsx");
		
		//Get the total number of steps/apis to be executed
		int totalrows = xcelfile.getTotalRows(Constants.SHEET_NAME);
		if (totalrows == 0) {
			LOGS.error("No data in the test steps sheet");
		}
			
		//iterate through each row
		for (int index = 2; index <= totalrows; index++ ) {
			
			//get the api name, its object and value
			String currentapi = xcelfile.getCellValue (Constants.SHEET_NAME,Constants.API,index);
			String currentobject = xcelfile.getCellValue (Constants.SHEET_NAME,Constants.OBJECT,index);
			String currentvalue = xcelfile.getCellValue (Constants.SHEET_NAME,Constants.VALUE,index);
			
			LOGS.debug("Execute " + currentapi + " method. Objects = " + currentobject + ". Values = " + currentvalue);
			
			if (currentapi != "") {
				//iterate through all the loaded apis
				for (int apiindex = 0; apiindex < totalapis; apiindex++) {
					//check if the api name is already loaded	
					if (method[apiindex].getName().equals(currentapi)) {
						//execute api
						if (method[apiindex].invoke(apis,currentobject,currentvalue).equals(Constants.FAIL) )  {								
							LOGS.error("API execution failed. API name = " + currentapi + ". Object name = " + currentobject + ". Value = " + currentvalue + ".");
							xcelfile.setCellValue(Constants.SHEET_NAME, Constants.RESULT, index, Constants.FAIL);
						} else {
							xcelfile.setCellValue(Constants.SHEET_NAME, Constants.RESULT, index, Constants.PASS);
						}
					}
				}			
			}			
		}
	}
}
