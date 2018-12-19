package Linkedin.Outlook.testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Linkedin.Outlook.PageObject.LinkedinLoginPage;
import Linkedin.Outlook.PageObject.OutlookLoginPage;
import Linkedin.Outlook.helper.Generic.waitHelper;

public class TestBase {

	public static WebDriver driver;
	public static Properties OR= new Properties();
	public static File f1;
	public static FileInputStream file;
	public static waitHelper waithelper;
		
	@BeforeTest
	public static void launchBrowser()
	{		
		Config config = new Config(OR);
		try {
				loadPropertiesFile();
		}catch (IOException e) 
		{
			e.printStackTrace();			
		}
		getBrowser(config.getBrowser());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	// Open the browser.
	private static void getBrowser(String browser) 
	{
		System.out.println(System.getProperty("user.dir"));
		String pathToExtension = "C://Users//Shailesh//AppData//Local//Google//Chrome//User Data//Default//Extensions//hihakjfhbmlmjdnnhegiciffjplmdhin//2.0.3_0";
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("load-extension=" + pathToExtension);
		
		//options.addExtensions(pathToExtension);
		
		options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
	}

	// load the property file	
	public static void loadPropertiesFile() throws IOException
	{
		try {
			f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Linkedin\\Outlook\\config\\config.properties");
			file = new FileInputStream(f1);
			OR.load(file);
			System.out.println(OR.getProperty("LinkedinWebsite"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void LoginLinkedin()
	{		
		Config config = new Config(OR);
		LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
		driver.get(config.getLinkedinWebsite());
		loginPage.logintoLinkedin(config.getLinkedinUserName(), config.getLinkedinPassword());
		loginPage.navigateToProfile();
	}
	
	public static void LoginOutlook()
	{
		Config config = new Config(OR);
		driver.get(config.getOutlookWebsite());

		OutlookLoginPage loginpage = new OutlookLoginPage(driver);

		try {
			loginpage.logintoOutlook(config.getOutlookUsername(),config.getOutlookPassword());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}	
	
	@AfterTest
	public static void closeBrowser()
	{
		driver.close();
		System.out.println("Browser Close");
	}
	
}
