package com.identifyNewBikes;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtils {        

	public static String[] excelInputData=new String[9];
	private static XSSFWorkbook workbook;
	
	//Creating a method which will read the values from the Excel InputDetails.xlsx
	public static String[] readExcelData(String sheetName) throws Exception {
		
		//Excelfile path
		String workingDir=System.getProperty("user.dir");
		String excelFilePath=workingDir+File.separator+"InputDetails.xlsx";
		//Excel file
		FileInputStream excelFile=new FileInputStream(excelFilePath);
		//XSSFWorkbook
		workbook = new XSSFWorkbook(excelFile);
		//XSSFSheet
		XSSFSheet sheet=workbook.getSheet(sheetName);
		//XSSFRow
		XSSFRow row;
		//XSSFCell
		XSSFCell cell;
		//Reading the data from the excel
		row=sheet.getRow(0);
		for(int i=0;i<9;i++)
		{
			cell=row.getCell(i);
			excelInputData[i]=String.valueOf(cell);
		}
		
		//Returning the String array storing the excel data
		return excelInputData;
	}

}

