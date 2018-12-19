package Linkedin.Outlook.helper.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
public class ExcelHelper {
	
	public int getExcelColumnNumber(String excellocation, String sheetName, String columnName) {
		
		int colNumber = -1;
		
		try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);
			
			HSSFRow row = sheet.getRow(0);
			
			for (int i = 0; i < row.getLastCellNum(); i++) 
			{
				if (row.getCell(i).getStringCellValue().trim().equals(columnName))
					colNumber = i;
			}
	 	//	workbook.close(); 
			file.close();
			return colNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colNumber;		
	}

	public ArrayList<String> getExcelColumnValue(String excellocation, String sheetName, int columnNumber) {
		
		HSSFRow row ;
		ArrayList<String> arrExcel = new ArrayList<String>(); 
		try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);			
			
			int totalNoOfRows = sheet.getLastRowNum()+1;
			for(int i=1; i<totalNoOfRows; i++)
			{
				row = sheet.getRow(i);
				HSSFCell cell = row.getCell(columnNumber);
				String value = String.valueOf(cell.getStringCellValue());
				arrExcel.add(value);
			}
		 
		//	workbook.close(); 
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrExcel;		
	}

public String getExcelColumnSingleValue(String excellocation, String sheetName, int columnNumber,int rowNum) {
		
		HSSFRow row ;
		String arrExcel = null ;
		
		try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);			
			
			row = sheet.getRow(rowNum);
			HSSFCell cell = row.getCell(columnNumber);
			arrExcel = String.valueOf(cell.getStringCellValue());
		 
		//	workbook.close(); 
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrExcel;		
	}
	
public void UpdateExcelColumnValue(String excellocation, String sheetName, Map<String,String>data,int rowNum, int colNum,String colName)
{
		
	HSSFRow row = null;
	HSSFCell cell = null;
	 	try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			System.out.println("Data in Excel : "+colName +"--"+data.get(colName));
			if (cell == null)
			{
			    cell = row.createCell(colNum);
			}
			
			cell.setCellValue(data.get(colName));
			
			file.close();
			
			FileOutputStream outFile = new FileOutputStream(new File(excellocation));
			workbook.write(outFile);
	//		workbook.close();
			outFile.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		 		
	}

public void UpdateExcelColumnStringValue(String excellocation, String sheetName, String data,int rowNum, int colNum,String colName)
{
	
	HSSFRow row = null;
	HSSFCell cell = null;
	 	try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			System.out.println("Data in Excel : "+colName +"--"+data);
			if (cell == null)
			{
			    cell = row.createCell(colNum);
			}
			
			cell.setCellValue(data);
			
			file.close();
			
			FileOutputStream outFile = new FileOutputStream(new File(excellocation));
			workbook.write(outFile);
		//	workbook.close();
			outFile.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		 		
	}	
}
