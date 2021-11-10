package com.tests;

import java.util.ArrayList;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.identifyNewBikes.DriverSetup;
import com.identifyNewBikes.GoogleSignIn;
import com.identifyNewBikes.KeywordDriven;
import com.identifyNewBikes.Main;
import com.identifyNewBikes.PopularUsedCars;
import com.identifyNewBikes.UpcomingBikes;
import com.identifyNewBikes.Wait;
import com.identifyNewBikes.WritingExcelFile;
import com.identifyNewBikes.WritingPropertiesFile;
public class TestNG extends Main{
	
	    //Declaration of static variables.
		public static WebDriver driver;
		public static String Url= "https://www.zigwheels.com";
		public static Scanner sc=new Scanner(System.in);
		public static Wait wait = new Wait();
		
		//Extent report
		public static ExtentReports report = ExtentReportManager.getReportInstance();
		public static ExtentTest test;
		
	    //Method invoking the browser depending on platform requirements.
		@Test
		public static void getDriver() throws Exception {
			
			test = report.createTest("Identify New Bikes");
			test.log(Status.INFO, "Opening the Browser");
			
	    	//Browser Choices
	    	String ChromeDriver_WindowsOS=KeywordDriven.getChromeWindows();
			String FirefoxDriver_WindowsOS=KeywordDriven.getFirefoxWindows();
			String OperaDriver_WindowsOS=KeywordDriven.getOperaWindows();
			String ChromeDriver_MacOS=KeywordDriven.getChromeMac();
			String OperaDriver_MacOS=KeywordDriven.getOperaMac();
			
			//Enter your browser choice in between the inverted Commas
			driver=DriverSetup.invokeDriver(ChromeDriver_WindowsOS);
			test.log(Status.PASS, "Browser Opened");
			
	    }
		
		//Google Sign-in
		@Test(dependsOnMethods = "getDriver")	
		public static void getGoogleSignIn() throws Exception {
			test.log(Status.INFO, "Opening the Google Sign-in Form");
			//Google Sign-in
			GoogleSignIn.googleSignIn(driver);
			
			String emailInvalid=KeywordDriven.getInvalidEmail();
			String passwordInvalid=KeywordDriven.getInvalidPassword();
			
			//Filling the Invalid details		
			GoogleSignIn.fillLoginForm(emailInvalid,passwordInvalid, driver);
			test.log(Status.PASS, "Error Message Captured");
			
			//Navigating to the official site of Zigwheels
			test.log(Status.INFO, "Opening the Site : Zigwheels.com");
			driver.manage().window().maximize();
			driver.get(Url);
			driver.navigate().to(Url);
			test.log(Status.PASS, "Zigwheels.com opened successfully");
		}
		
		//Popular Used Cars in Chennai
		@Test(dependsOnMethods = "getGoogleSignIn")
		public static void getPopularUsedCars() throws Exception {
			test.log(Status.INFO, "Getting popular used cars in Chennai");
			ArrayList<String> cars = PopularUsedCars.popularUsedCars(driver);
			//Writing them in a file
			WritingPropertiesFile.writingfile(cars, "Used-Cars");
			test.log(Status.PASS, "Popular Used Cars Successful");
		}
		
		//Upcoming Honda Bikes
		@Test(dependsOnMethods = "getPopularUsedCars")
		public static void getUpcomingBikes() throws Exception {
			test.log(Status.INFO, "Getting upcoming Honda Bikes");
			ArrayList<String> bikes = UpcomingBikes.findingUpcomingBikes(driver);
			//Writing them
			WritingExcelFile.writeExcel(bikes);
			test.log(Status.PASS, "Upcoming Bikes Successful");
			
			
		}
		
		//Closing the application
		@Test(dependsOnMethods = "getUpcomingBikes")
		public static void closeApplication(){
			
			test.log(Status.INFO, "Closing the browser");
			driver.close();
			driver.quit();
			report.flush();
			test.log(Status.PASS, "Closed browser successfully");
		}
		
		
		
}