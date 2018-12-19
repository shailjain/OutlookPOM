package Linkedin.Outlook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import Linkedin.Outlook.PageObject.GetLinkedinDataPage;
import Linkedin.Outlook.helper.Generic.ExcelHelper;
import Linkedin.Outlook.testBase.Config;
import Linkedin.Outlook.testBase.TestBase;

public class GetLinkeDinDataIntoExcel extends TestBase {

	public static File f1;
	public static FileInputStream file;
	public static Config config;
			
	
	@Test(priority= 0)
	public static void setup() {
		
		LoginLinkedin();		
	}


	public static void ReadExcelNNavigate()
	{
		Config config = new Config(OR);
		String filePath = config.getLinkedinDataIntoExcel();
		String testSheet = "Sample Leads";
		
	 	int LinkedinURLcolNum = -1;
		int EmailcolNum = -1;
		int FirstNamecolNum = -1;
		int LastNamecolNum = -1;
		int CompanyWebsitecolNum = -1;
		int CompanycolNum = -1;
		int orgURLcolNum = -1; 
		
		ExcelHelper objExcel = new ExcelHelper();
		
		LinkedinURLcolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Linkedin");
		EmailcolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Email");
		FirstNamecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "First name");
		LastNamecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Last name");
		CompanyWebsitecolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Company website");
		CompanycolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "Company");
		orgURLcolNum = objExcel.getExcelColumnNumber(filePath, testSheet, "orgURL");
			
		// To get the number of rows present in sheet
		ArrayList<String> arrExcel = objExcel.getExcelColumnValue(filePath, testSheet, LinkedinURLcolNum);
			 		
		for(int i=0; i<arrExcel.size(); i++)
		{			
			String value = arrExcel.get(i);
			Map<String,String> data = navigateToURL(value);
			
			if (data.size() != 0)
			{
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,FirstNamecolNum,"First name");
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,LastNamecolNum,"Last name");
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,CompanyWebsitecolNum,"Company website");
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,CompanycolNum,"Company");
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,orgURLcolNum,"orgURL");
				objExcel.UpdateExcelColumnValue(filePath, testSheet,data,i+1,EmailcolNum,"Email");				
			}
			else
			{
				System.out.println("Data doesnt saved in Excel for : "+ value);
			}
		}
		System.out.println("Script finished");
	}
		
	public static Map<String, String> navigateToURL(String orgURL)
	{
	   String fName = null;
	   String lName = null;
	   String email = null;
	   String CompanyName = null;
	   String CompanyWebSite = null;
	   String neworgURL = null;
	   	   
	   Boolean ble;
	   Map<String, String> ExcelData = new HashMap<String, String>();

	   GetLinkedinDataPage linkedinpage = new GetLinkedinDataPage(driver);
	   
	   // navigate to URL
	   ble = linkedinpage.navigateToURL(orgURL);
	   if(ble)
	   {
		   System.out.println("Linkedin Page load sucessfully.");
		   neworgURL = driver.getCurrentUrl();
	   }
	   else
	   {
		   System.out.println("Linkedin Page doesn't load properly.");
		   return ExcelData;
	   }
	   
	   // Contact Info Details page
	   String ContactData = linkedinpage.GetContactInfoDetail();
	   if (ContactData != null)
	   {
		   String [] arr1 = ContactData.split(";");
		   email = arr1[0];
	   	   CompanyWebSite= arr1[1];
	   }
	   
	   // Get the first and last name.
	   String name = linkedinpage.GetFirstLastName();
	 	if (name != null)
		{
			String[] arrSplit = name.split(" ");
			for (int i=0; i < arrSplit.length; i++)
		    {
				System.out.println("Name :"+arrSplit[i]);
		    }
			
			if (arrSplit.length == 2)
			{
				fName = arrSplit[0];
				lName = arrSplit[1];
			}
		}
		else
		{
			System.out.println("First Name and Last Name doesn't Exist.");
		}		
	 	
	 	// Get the Company name and website details
	    String cmpData = linkedinpage.GetCompanyData();
		   if (cmpData != null)
		   {
			   String [] arr1 = cmpData.split(";");
			   CompanyName = arr1[0];
		   	   CompanyWebSite= arr1[1];
		   }
	 		
		ExcelData.put("First name",fName);
		ExcelData.put("Last name",lName);
		ExcelData.put("Email",email);
		ExcelData.put("Company",CompanyName);
		ExcelData.put("Company website",CompanyWebSite);
		ExcelData.put("orgURL",neworgURL);
				
		return ExcelData;
	}		
	
}
