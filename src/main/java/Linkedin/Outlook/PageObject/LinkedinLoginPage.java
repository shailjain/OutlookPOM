package Linkedin.Outlook.PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Linkedin.Outlook.helper.Generic.GenericHelper;
import Linkedin.Outlook.helper.Generic.waitHelper;

public class LinkedinLoginPage {

	public WebDriver driver;
	public GenericHelper gHelper;
	public waitHelper wHelper;
	
	
	@FindBy(xpath="//input[@id='login-email']")
	public WebElement username;
	
	@FindBy(xpath="//input[@id='login-password']")
	public WebElement password;
	
	@FindBy(xpath="//input[@id='login-submit']")
	public WebElement signin;
	
	@FindBy(xpath="//span[text()='Home']")
	public WebElement homepage;
	
	@FindBy(xpath="//button[@id='nav-settings__dropdown-trigger']")
	public WebElement me;
	
	@FindBy(xpath="//span[text()='View profile']")
	public WebElement viewprofile;
	
	@FindBy(xpath="//span[@class='visually-hidden'][text()='See contact info']")
	public WebElement seecontactinfo;
	
	
	public LinkedinLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		gHelper = new GenericHelper(driver);
		wHelper = new waitHelper(driver);
	}
	
	public void logintoLinkedin(String uname, String pass)
	{
		Boolean ble;
		
		ble = gHelper.isWebElementVisible(username);
		if (!ble)
			return;
	
		username.click();
		username.sendKeys(uname);
		 
		ble = gHelper.isWebElementVisible(password);
		if (!ble)
			return;
		
		password.sendKeys(pass);
		
		ble = gHelper.isWebElementEnabled(signin);
		if (!ble)
			return;
		
		signin.click();
		wHelper.waitForWebElementToAppear(homepage, 30);
		
		ble = gHelper.isWebElementVisible(homepage);
		if (ble)
			System.out.println("Login to Linkedin happened successfully.");
		else
			System.out.println("Login to Linkedin doesn't happened successfully.");
	}
	
	public void navigateToProfile()
	{
		Boolean ble;
		
		ble = gHelper.isWebElementVisible(me);
		if (!ble)
			return;
		
		me.click();
		viewprofile.click();
		wHelper.waitForWebElementToAppear(seecontactinfo, 30);
		ble = gHelper.isWebElementVisible(seecontactinfo);
		if (ble)
			System.out.println("Linkedin Profile Page opened.");
		else
			System.out.println("Linkedin Profile Page doesn't opened.");
	}
		
}
