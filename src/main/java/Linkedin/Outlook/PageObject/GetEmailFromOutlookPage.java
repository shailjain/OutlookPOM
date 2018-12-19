package Linkedin.Outlook.PageObject;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Linkedin.Outlook.helper.Generic.GenericHelper;
import Linkedin.Outlook.helper.Generic.JavaScriptHelper;
import Linkedin.Outlook.helper.Generic.waitHelper;

public class GetEmailFromOutlookPage {

	public WebDriver driver;
	public GenericHelper gHelper;
	public waitHelper wHelper;
	public JavaScriptHelper jHelper;
	
	
	@FindBy(xpath="//div[text()='New message']")
	public WebElement compose;
	
	@FindBy(xpath="//div[text()='To']/following-sibling::span/input")
	public WebElement toemail;
	
	@FindBy(xpath="//div[text()='(No subject)']")
	public WebElement subject;
	
	@FindBy(xpath="//button//div[text()='Show more']")
	public WebElement showmore;
	
	@FindBy(xpath="//button[@title='LinkedIn']")
	public WebElement linkedinbutton;
	
	@FindBy(xpath="//div[@class='_2hgIa']]")
	public WebElement linkedincontent;
	
	@FindBy(xpath="//span[@class='vMI3V']//span[1]")
	public List<WebElement> linkedinpersonname;
	
	@FindBy(xpath="//button[@aria-label='Close expanded profile view']")
	public WebElement closeprofileview;
	
	@FindBy(xpath="//div[text()='(No subject)']/following-sibling::button[1]")
	public WebElement closecomposebutton;
		
	
	public GetEmailFromOutlookPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		gHelper = new GenericHelper(driver);
		wHelper = new waitHelper(driver);
		jHelper = new JavaScriptHelper(driver);
	}
	
	public WebElement GetElement(String id)
	{
		
		 return driver.findElement(By.xpath(id));
	}
	
	public String returnEmail(String newEmail,String orgURL,String fName, String lName) 
	{
		String flagReturn = "false";
		Boolean ble;
		
		 try {			 
			 	compose.click();
			 	Thread.sleep(3000);
			 	
			 	ble = gHelper.isWebElementVisible(toemail);
				if(ble)
				{
					toemail.sendKeys(newEmail);
					Thread.sleep(2000);
					toemail.sendKeys(Keys.TAB);
					Thread.sleep(2000);
				}
 
				subject.click();
				Thread.sleep(3000);
				
				WebElement newEle = GetElement("//span[text()='" + newEmail+ "']");
				
				//ble = gHelper.isByElementPresents(By.xpath("//span[text()='" + newEmail+ "']"));
				ble = gHelper.isWebElementPresent(newEle);
				
				if(ble)
				{
					// WebElement element = driver.findElement(By.xpath("//span[text()='" + newEmail+ "']"));
					 newEle.click();
					 Thread.sleep(1000);
					 showmore.click();
					 Thread.sleep(1000);
					 linkedinbutton.click();
					 Thread.sleep(1000);
				}
				else
				{
					
				}
				
				ble = gHelper.isWebElementVisible(linkedincontent);
				if(ble)
				{
					flagReturn = "false";
				}
				else
				{
					ble = gHelper.isListElementPresent(linkedinpersonname);
				   	if(ble)
				   	{				   	
					   	for (WebElement ele: linkedinpersonname) {
					    	
					    	String name =ele.getText();
					    	System.out.println("name:"+name);
					    	
					    	String[] arrSplit = name.split(" ");
					    	System.out.println("arrSplit.length:"+arrSplit.length);
					    	System.out.println("aarrSplit[0]:"+arrSplit[0]);
					    	System.out.println("arrSplit[1]:"+arrSplit[1]);
					    	System.out.println("fname:"+fName);
					    	System.out.println("lName:"+lName);
					    	
					    	if (arrSplit.length >=2 && arrSplit[0].equalsIgnoreCase(fName) && arrSplit[1].equalsIgnoreCase(lName))
					    	{			
					    		 System.out.println("in the if");
					    		 flagReturn = "true";
					    		 break;
					    	}
					    	else
					    	{
					    		 flagReturn = "false";
					    	}
						    	
					   	}
				   	}
				   	else
				   	{
				   		System.out.println("FirstName and Last Name data doesn't matches");
				   		flagReturn = "false";
				   	}
				   	
				 }
				
				 System.out.println("between new Email :" + newEmail);
				 closeprofileview.click();
				 Thread.sleep(1000);
				 closecomposebutton.click();				 
				 Thread.sleep(1000);
				 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 System.out.println("End new Email :" + flagReturn);
		 return flagReturn;
	}
			
}
