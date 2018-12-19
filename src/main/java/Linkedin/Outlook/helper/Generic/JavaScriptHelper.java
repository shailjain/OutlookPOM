package Linkedin.Outlook.helper.Generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private JavascriptExecutor jse;
	
	public JavaScriptHelper(WebDriver driver)
	{
		this.driver = driver;
		jse  = (JavascriptExecutor)driver;
	}
	
	public void scrollupbypixel()
	{
		jse.executeScript("window.scrollBy(0,-500)", "");	
	}
	
	public void scrolldownbypixel(int num)
	{
		jse.executeScript("window.scrollBy(0,"+num+")", "");	
	}
	
	public void scrolltoElement(By by)
	{
		for (int i=0; i < 6; i++)
	    {
			jse.executeScript("window.scrollBy(0,200)", "");
			try {
				Thread.sleep(2000);
				if (driver.findElement(by).isDisplayed());
				{
					break;
				}	
			} catch (Exception e) {
				
			}					
	    }	
	}
	
	public void scrolltoElements(WebElement ele)
	{
		for (int i=0; i < 6; i++)
	    {
			jse.executeScript("window.scrollBy(0,200)", "");
			try {
				Thread.sleep(2000);
				if (ele.isDisplayed());
				{
					break;
				}	
			} catch (Exception e) {
			}					
	    }	
	}
	
	public void scrolltoListElements(List<WebElement> element)
	{
		System.out.println("scrolltoListElements");
	 for (int i=0; i < 6; i++)
	    {
			jse.executeScript("window.scrollBy(0,200)", "");
			try {
				Thread.sleep(2000);
				if (element.size() != 0)
				{
					break;			 
				 
				}				
			} catch (Exception e) {
				System.out.println("scrolltoListElements : catch");
			}					
	    }	
	}
	
}
