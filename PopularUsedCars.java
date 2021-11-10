package com.identifyNewBikes;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PopularUsedCars {
	
	static Wait wait = new Wait(); 
	public static String linkTextUsedCars=KeywordDriven.getUsedCarsLinkText(); 
	public static String findUsedCarsButtonString= KeywordDriven.getFindUsedCars();
	public static String getChennai = KeywordDriven.getChennai();
	public static String allCars = KeywordDriven.getUsedCars();
	
	//Find Used Cars
	public static void findUsedCarsLinkText(WebDriver driver) {
		Actions act = new Actions(driver);
		WebElement used_cars=driver.findElement(By.linkText(linkTextUsedCars));
		act.moveToElement(used_cars).perform();
		wait.waitImplicit(driver);
		driver.findElement(By.xpath(findUsedCarsButtonString)).click();
	}
	//Select Location
	public static void SelectLocation(WebDriver driver) {
		wait.waitImplicit(driver);
		driver.findElement(By.xpath(getChennai)).click();
	}
	//Printing models
	public static ArrayList<String> printModels(WebDriver driver) throws Exception {
		
		//Reading the input from Excel file
		String locationPopularUsedCars=KeywordDriven.getLocationName();

		wait.waitImplicit(driver);
		String models_list=driver.findElement(By.xpath(allCars)).getText();
		ArrayList<String> models=new ArrayList<String>();
		Collections.addAll(models, models_list.split("\n"));
		
		//Printing the Popular used Cars in Chennai
		System.out.println("PART 2:");
		System.out.println("Popular Models of Used Cars in "+locationPopularUsedCars+" are:-");
		for(int i =0; i< models.size(); i++){
			System.out.println((i+1)+" "+models.get(i));
		}
		return models;
	}
	
	//Invoking the previous methods
	public static ArrayList<String> popularUsedCars(WebDriver driver) throws Exception{
		//Extracting the popular used cars
		findUsedCarsLinkText(driver);
		SelectLocation(driver);
		ArrayList<String> models=printModels(driver);
		return models;
	}

}
