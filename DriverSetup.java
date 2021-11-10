package com.identifyNewBikes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
	//Declaring the static variable
	private static WebDriver driver;
	
	//Crating invokeDriver method which will take browserName as a parameter
	public static WebDriver invokeDriver(String browserName) {
		
		//Invoking Chrome Driver in WindowsOS
		if (browserName.equalsIgnoreCase("ChromeDriver_WindowsOS")) {
			String chromeDriverPath = "\\Drivers\\chromedriver.exe";
			String path=System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path+chromeDriverPath);
			
			driver= new ChromeDriver();	
		}
		//Invoking Firefox Driver in WindowsOS
		else if (browserName.equalsIgnoreCase("FirefoxDriver_WindowsOS")) {
			String firefoxDriverPath="\\Drivers\\geckodriver.exe";
			String path= System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path+firefoxDriverPath);
			
			driver= new FirefoxDriver();	
		}
		//Invoking Opera Driver in WindowsOS
		else if(browserName.equalsIgnoreCase("OperaDriver_WindowsOS")) {
			String operaDriverPath="\\Drivers\\operadriver.exe";
			String path= System.getProperty("user.dir");
			System.setProperty("webdriver.opera.driver", path+operaDriverPath);
			
			driver= new OperaDriver();
		}
		//Invoking Chrome Driver in MacOS
		else if (browserName.equalsIgnoreCase("ChromeDriver_MacOS")) {
			String chromeDriverPath = "\\Drivers\\chromedriver";
			String path=System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path+chromeDriverPath);
			
			driver= new ChromeDriver();	
		}
		//Invoking Opera Driver in MacOS
		else if(browserName.equalsIgnoreCase("OperaDriver_MacOS")) {
			String operaDriverPath="\\Drivers\\operadriver";
			String path= System.getProperty("user.dir");
			System.setProperty("webdriver.opera.driver", path+operaDriverPath);
			
			driver= new OperaDriver();
		}
		//Returning the Driver
		return driver;
	}
	
}