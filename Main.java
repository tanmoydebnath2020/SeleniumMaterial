package com.identifyNewBikes;

import java.util.ArrayList;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;

public class Main {
	
	    //Declaration of static variables.
		public static String Url= "https://www.zigwheels.com";
		public static Scanner sc=new Scanner(System.in);
		public static Wait wait = new Wait();
		public static WebDriver driver;
		
	    
	    //Instantiating the driver 
	    public static void getDriver() throws Exception {

	    	//Browser Choices
			String ChromeDriver_WindowsOS=KeywordDriven.getChromeWindows();
			String FirefoxDriver_WindowsOS=KeywordDriven.getFirefoxWindows();
			String OperaDriver_WindowsOS=KeywordDriven.getOperaWindows();
			String ChromeDriver_MacOS=KeywordDriven.getChromeMac();
			String OperaDriver_MacOS=KeywordDriven.getOperaMac();
			
			//Enter your browser choice in between the inverted Commas
			driver=DriverSetup.invokeDriver(ChromeDriver_WindowsOS);
	    }
	    //Navigating to the official site of Zigwheels
		public static void navigation(WebDriver driver){
			driver.manage().window().maximize();
			driver.get(Url);
			driver.navigate().to(Url);
		}
		//Google Sign-in
		public static void getGoogleSignIn() throws Exception {
			//Google Sign-in
			GoogleSignIn.googleSignIn(driver);
			
			//Reading from Excel
			String emailInvalid=KeywordDriven.getInvalidEmail();
			String passwordInvalid=KeywordDriven.getInvalidPassword();
			
			//Filling the Invalid details		
			GoogleSignIn.fillLoginForm(emailInvalid,passwordInvalid, driver);
			
		}
		//Popular Used Cars
		public static void getPopularUsedCars() throws Exception {
			ArrayList<String> cars = PopularUsedCars.popularUsedCars(driver);
			//Writing them in a file
			WritingPropertiesFile.writingfile(cars, "Used-Cars");
		}
		//Upcoming Honda Bikes
		public static void getUpcomingBikes() throws Exception {
			ArrayList<String> bikes = UpcomingBikes.findingUpcomingBikes(driver);
			//Writing them
			WritingExcelFile.writeExcel(bikes);
		}
		//Closing the Driver
		public static void closeApplication(WebDriver driver){
			driver.close();
			driver.quit();
		}
		
		public static void main(String[] args) throws Exception{
			//Invoking the Driver.
		    getDriver();
			
		    //Google Sign-in
			getGoogleSignIn();
			
			//Navigating to the official site of Zigwheels.
			navigation(driver);
			
			//Popular Cars in Chennai
			getPopularUsedCars();
						
			//Upcoming Honda Bikes
			getUpcomingBikes();
			
			//Closing the driver.
			closeApplication(driver);
	     } 
	

}
