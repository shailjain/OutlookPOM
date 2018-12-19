package Linkedin.Outlook.PageObject;


import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Linkedin.Outlook.helper.Generic.GenericHelper;
import Linkedin.Outlook.helper.Generic.JavaScriptHelper;
import Linkedin.Outlook.helper.Generic.waitHelper;

public class GetLinkedinDataPage {

	public WebDriver driver;
	public GenericHelper gHelper;
	public waitHelper wHelper;
	public JavaScriptHelper jHelper;
	
	
	@FindBy(xpath="//div[@class='pv-top-card-v2-section__links']/a/span[2]")
	public WebElement linkedinhome;
	
	@FindBy(xpath="//h2[text()='Contact Info']")
	public WebElement contactinfo;
	
	@FindBy(xpath="//h2[text()='Contact Info']/following-sibling::div[1]//a")
	public List <WebElement> contactinfolink;
	
	@FindBy(xpath="//h2[text()='Contact Info']/following-sibling::div[1]//section/header[text()='Website']/following-sibling::*//a")
	public WebElement contactinfowebsitelink;
	
	@FindBy(xpath="//button[@class='artdeco-dismiss']")
	public WebElement contactinfoclosebutton;
	
	@FindBy(xpath="//span[text()='See contact info']")
	public WebElement seecontactinfo;
	
	@FindBy(xpath="//h1")
	public WebElement header;
	
	@FindBy(xpath="//section[@id='experience-section']")
	public WebElement experiencesection;
	
	@FindBy(xpath="//span[text()='Company Name']/following-sibling::span")
	public List<WebElement> companylogo;
	
	@FindBy(xpath="//button[@id='org-about-company-module__show-details-btn']") 
	public WebElement showdetailbutton;
	
	@FindBy(xpath="//div[@class='org-about-company-module__about-us-extra']/div[1]/div/a")
	public WebElement companywebsite;	
	
	@FindBy(xpath="//h3/div/span[1]")
	public List<WebElement> multiplecompany;
	
	@FindBy(xpath="//h3/div")
	public List<WebElement> allcompany;
	
	
	public GetLinkedinDataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		gHelper = new GenericHelper(driver);
		wHelper = new waitHelper(driver);
		jHelper = new JavaScriptHelper(driver);
	}
	
	public Boolean navigateToURL(String url)
	{
		// Append https:// if its not exist.
		if (!url.startsWith("https"))
			url = "https://"+ url;
		
		System.out.println("URL enetrred : "+ url);
		
		driver.get(url);
		wHelper.waitForWebElementToAppear(linkedinhome, 30);
		
		return gHelper.isWebElementVisible(linkedinhome);
	}
	
	public String GetContactInfoDetail()
	{
		Boolean ble;
		String email = null;
		String CompanyWebSite = null;
		String data;
		
		Actions action = new Actions(driver);
		
		ble = gHelper.isWebElementVisible(linkedinhome);
		if(ble)
		{
			linkedinhome.click();
			wHelper.waitForWebElementToAppear(contactinfo, 30);
			
			for (WebElement ele: contactinfolink) {
			    if(ele.getText().contains("@"))
			    {
			    	email = ele.getText();
					break;
			    }	    
			}
			ble = gHelper.isWebElementVisible(contactinfowebsitelink);
			if(ble)
				CompanyWebSite = contactinfowebsitelink.getText();
				
			action.moveToElement(contactinfoclosebutton).click().perform();
			wHelper.waitForWebElementToAppear(seecontactinfo, 30);
			
			data = email + ";" + CompanyWebSite;
			
			return data;
		}
		else
		{
			System.out.println("Linkedin Page doesn't load properly.");
			return null;
		}
	}

	public String GetFirstLastName()
	{
		Boolean ble;
		String str;
		
		// Get the First Name, Last Name
		ble = gHelper.isWebElementVisible(header);
		if(ble)
		{
			str =header.getText();
			return str;
		}
		else
		{
			return null;
		}
	}

	public String GetCompanyData()
	{
		String CompanyName = null;
		String CompanyWebSite = null;
		Boolean ble;
		String data = null;		
		
		jHelper.scrolltoElements(experiencesection);
		for (WebElement ele: companylogo) {
			CompanyName = ele.getText();
			ele.click();
			if(CompanyName != null)
				break;					
		}
		
		try {
			Thread.sleep(3000);
			ble = gHelper.isWebElementVisible(showdetailbutton);
			if (ble)
			{
				showdetailbutton.click();
				Thread.sleep(1000);
				CompanyWebSite  = companywebsite.getText();
			}		 
			else
			{
				jHelper.scrolltoListElements(allcompany);
				ble = gHelper.isListElementPresent(allcompany);
				if (ble)
				{
					for (WebElement cmp: allcompany) {
						cmp.click();
					    Thread.sleep(3000);
					    jHelper.scrolldownbypixel(250);
					    ble = gHelper.isWebElementVisible(showdetailbutton);
					    if(ble) {	
						    showdetailbutton.click();
							Thread.sleep(1000);
							CompanyWebSite  = companywebsite.getText();					    	 
							break;
						}
				 }
			  }
			}
		} catch (Exception e) 
		{
			System.out.println("Element doesn't exist: CompanyWebSite ");
		}
		data = CompanyName +";"+ CompanyWebSite;
		
		return data;
		
	}
			
}
