package Linkedin.Outlook.testBase;


import java.util.Properties;

public class Config extends TestBase{
	
	public Properties OR;
	
	public Config(Properties OR){
		this.OR = OR;
	}
	public String getLinkedinUserName() {
		return OR.getProperty("LinkedinUsername");
	}

	public String getLinkedinPassword() {
		return OR.getProperty("LinkedinPassword");
	}
	public String getOutlookUsername() {
		return OR.getProperty("OutlookUsername");
	}

	public String getOutlookPassword() {
		return OR.getProperty("OutlookPassword");
	}	
	public String getBrowser() {
		return OR.getProperty("Browser");
	}

	public String getLinkedinWebsite() {
		return OR.getProperty("LinkedinWebsite");
	}
	
	public String getOutlookWebsite() {
		return OR.getProperty("OutlookWebsite");
	}
	
	public String getLinkedinDataIntoExcel() {
		return OR.getProperty("GetLinkedinDataIntoExcel");
	}
	
	public String getInputExcelFile() {
		return OR.getProperty("InputExcelFile");
	}
	
	public int getPageLoadTimeOut() {
		return Integer.parseInt(OR.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("ExplicitWait"));
	}



}
