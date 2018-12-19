package Linkedin.Outlook.helper.Generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericHelper {
	
	private WebDriver driver;
	
	public GenericHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean isWebElementPresent(WebElement ele){
        try{
            if (ele != null){
            return true;
            }
            else {
            	return false;
            }
        }
        catch(Exception e){
        	System.out.println("Element not found : "+ele);
            return false;
        }
    }	
	
	public boolean isListElementPresent(List<WebElement> ele){
        try{
        	System.out.println("isMultipleElementPresent:"+ ele.size());
            if (ele.size() != 0 ) {
            	System.out.println("isMultipleElementPresent : true");
             	return true;
            }
             else {
            	 System.out.println("isMultipleElementPresent : false");
            	 return false;
             }
        }
        catch(Exception e){
        	System.out.println("Element not found in List: "+ele);
            return false;
        }
    }

	public boolean isWebElementVisible(WebElement ele){
        try{
            if (ele.isDisplayed()){
            return true;
            }
            else {
            	return false;
            }
        }
        catch(Exception e){
        	System.out.println("Element not displayed : "+ele);
            return false;
        }
    }	
	
	public boolean isWebElementEnabled(WebElement ele){
        try{
            if (ele.isEnabled()){
            return true;
            }
            else {
            	return false;
            }
        }
        catch(Exception e){
        	System.out.println("Element not enabled : "+ele);
            return false;
        }
    }
	
	public boolean isByElementPresents(By ele){
        try{
            if (driver.findElement(ele) != null){
            return true;
            }
            else {
            	return false;
            }
        }
        catch(Exception e){
        	System.out.println("Element not found : "+ele);
            return false;
        }
    }	
	
	public boolean isByMultipleElementPresents(By ele){
        try{
            if (driver.findElements(ele)  != null) {
             	return true;
            }
             else {
            	 return false;
             }
        }
        catch(Exception e){
        	System.out.println("Element not found : "+ele);
            return false;
        }
    }
	
}
