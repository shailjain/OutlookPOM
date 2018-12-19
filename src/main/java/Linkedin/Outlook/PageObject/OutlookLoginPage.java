package Linkedin.Outlook.PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Linkedin.Outlook.helper.Generic.GenericHelper;
import Linkedin.Outlook.helper.Generic.waitHelper;

public class OutlookLoginPage {

	public WebDriver driver;
	public GenericHelper gHelper;
	public waitHelper wHelper;
	
	
	@FindBy(xpath="//div[@class='placeholderContainer']/input[1]")
	public WebElement username;
	
	@FindBy(xpath="//div[@class='placeholderContainer']/input[1]")
	public WebElement password;
	
	@FindBy(xpath="//a[text()='Sign in '][@class='linkButtonSigninHeader']")
	public WebElement signin;
	
	@FindBy(xpath="//input[@id='idSIButton9']")
	public WebElement loginbutton;
	
	@FindBy(xpath="//div[text()='New message']")
	public WebElement compose;
	
	public OutlookLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		gHelper = new GenericHelper(driver);
		wHelper = new waitHelper(driver);
	}
	
	public void logintoOutlook(String uname, String pass) throws Exception
	{
		Boolean ble;
	
		wHelper.waitForWebElementToAppear(signin, 30);
		ble = gHelper.isWebElementVisible(signin);
		if (!ble)
			return;
	
		signin.click();
		wHelper.waitForWebElementToAppear(username, 30);

		ble = gHelper.isWebElementVisible(username);
		if (!ble)
			return;
		
		username.sendKeys(uname);
		try {
			Thread.sleep(2000);
			loginbutton.click();
			Thread.sleep(2000);
			password.sendKeys(pass);
			loginbutton.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		wHelper.waitForWebElementToAppear(compose, 30);
		
		ble = gHelper.isWebElementVisible(compose);
		if (ble)
			System.out.println("Login to Outlook happened successfully.");
		else
			System.out.println("Login to Outlook doesn't happened successfully.");
	}
			
}
