package Linkedin.Outlook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.testng.annotations.Test;

import Linkedin.Outlook.PageObject.GetEmailFromOutlookPage;
import Linkedin.Outlook.helper.Generic.ExcelHelper;
import Linkedin.Outlook.testBase.Config;
import Linkedin.Outlook.testBase.TestBase;

public class GetLinkedinEmailFromOutlook extends TestBase{
	
	public static File f1;
	public static FileInputStream file;
	public static Config config;
 
	
	@Test(priority= 0)
	public static void setup() {
		
		LoginOutlook();		
	}	
	
	public static void ReadExcelNOutLook()
	{
		String email;
	
		Config config = new Config(OR);
		
		String filePath = config.getLinkedinDataIntoExcel();
		String testSheet = "Sample Leads";
		ExcelHelper objExcel = new ExcelHelper();
	
		int EmailcolNum = -1;
		int FirstNamecolNum = -1;
		int LastNamecolNum = -1;
		int CompanyWebsitecolNum = -1;
		int orgURLcolNum = -1;
		
		EmailcolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Email");
		FirstNamecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "First name");
		LastNamecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Last name");
		CompanyWebsitecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Company website");
		orgURLcolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "orgURL");

		ArrayList<String> arrExcel = objExcel.getExcelColumnValue(filePath, testSheet, orgURLcolNum);
		
		// To get the number of rows present in sheet
		for(int i=0; i<arrExcel.size(); i++)
		{	
			String fName = objExcel.getExcelColumnSingleValue(filePath, testSheet, FirstNamecolNum,i+1);
			String lName = objExcel.getExcelColumnSingleValue(filePath, testSheet, LastNamecolNum,i+1);
			String CompanyWebsite = objExcel.getExcelColumnSingleValue(filePath, testSheet, CompanyWebsitecolNum,i+1);
			String orgURL = objExcel.getExcelColumnSingleValue(filePath, testSheet, orgURLcolNum,i+1);
			
			// function to get the email from outlook.com
			email = funcGetEmail(fName,lName,CompanyWebsite,orgURL);
			System.out.println("Email - ReadExcelNOutLook :"+ email);
			if(!email.equalsIgnoreCase("null"))
				objExcel.UpdateExcelColumnStringValue(filePath, testSheet,email,i+1,EmailcolNum,"Email");
 		} 		
		System.out.println("Script Finished");
	}
		
	public static String funcGetEmail(String fName, String lName,String CompanyName,String orgURL)
	{
		String returnType;
		String newEmail = null;
		String returnEmail = null;
		
		GetEmailFromOutlookPage outlookpage = new GetEmailFromOutlookPage(driver);
		
		
		String [] arrOfStr = CompanyName.split("www.");
		for (String splitval : arrOfStr) 
		    CompanyName = splitval;
		
		arrOfStr = null;
		if (CompanyName.contains("/"))
		{
			arrOfStr = CompanyName.split("/");
			CompanyName = arrOfStr[0];
		}
		
		if (fName == null && lName == null)
		{
			System.out.println("First Name :"+fName + " Last Name :"+ lName);
			return newEmail;
		}
		
		// Name Combinations
		ArrayList<String> arrli = new ArrayList<String>(5);
		
		arrli.add(fName + lName + "@");
		arrli.add(fName + "_" + lName + "@");
		arrli.add(fName + "." + lName + "@") ;
		arrli.add(fName + "@") ;
		arrli.add(fName.substring(0,1) + lName + "@") ;
		arrli.add(fName + lName.substring(0,1) + "@") ;
		
		// Create New URL
		if(!CompanyName.equalsIgnoreCase("null"))
		{
			for(int x = 0; x <arrli.size(); x++) 
			{
				newEmail = arrli.get(x) + CompanyName;
				returnType = outlookpage.returnEmail(newEmail,orgURL,fName,lName);
				 if (returnType == "true")
				 {
					 returnEmail = newEmail;
					 System.out.println("Email -funcGetEmail :"+newEmail);
					 break;
				 }
				 else
				 {
					 System.out.println("not found:"+returnType);
				 }
		     }
		}
		else
		{
			System.out.println("Company websie is null");
			return returnEmail;
		}
		return returnEmail;
	}
		
}
