package Linkedin.Outlook.helper.Generic;

import java.util.concurrent.TimeUnit;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitHelper {
	
	private WebDriver driver;
		
	public waitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setImplicitWait(int timeout, TimeUnit unit)
	{
		driver.manage().timeouts().implicitlyWait(timeout,unit);		
	}
	
	public void setPageLoadTimeout(long timeout, TimeUnit unit)
	{
		//driver.manage().timeouts().wait(30);	//.pageLoadTimeout(timeout, unit);
		
	}
	
	public void waitForWebElementToAppear(WebElement selector, long timeOutInSeconds) {
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	      wait.until(ExpectedConditions.visibilityOfElementLocated((By) selector));	      
	      
	    } 
	    catch (Exception e) {
	      
	    }
	  }
	
	public void waitForByElementToAppears(By selector, long timeOutInSeconds) {
	    try {
	      WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	      wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	    } 
	    catch (Exception e) {
	      
	    }
	  }
	
}
