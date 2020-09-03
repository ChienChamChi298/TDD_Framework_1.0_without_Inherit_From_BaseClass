package com.qa;

import org.testng.annotations.Test;

import com.qa.util.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BaseTest {
 protected static AppiumDriver driver;
 protected static Properties props; 
 InputStream inputStream; 
 
 
 public BaseTest() {
	 
 } 
 
 public void setDriver(AppiumDriver driver) {
	 this.driver = driver;
 } 
 
 public AppiumDriver getDriver() {
	 return driver;
 }
 

  public void initDriver(String platformName, String platformVersion , String deviceName)  {  
	    try {  

	    	props = new Properties(); 
	    	String propFileName="config.properties"; 
	    	
	    	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	    	props.load(inputStream);
	    	
	    	
	    	DesiredCapabilities  capabilities = new DesiredCapabilities(); 
	    	 
	    	//Cach 0
	    	//Not Working : URL return Null;=
			//URL urlApp = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation")); 
			//capabilities.setCapability("app",  urlApp); -> NUll
			//System.out.println("URL folder: " + urlApp); 
			
			//Cach 1
			//capabilities.setCapability("app",  "C:\\Users\\CSM\\Downloads\\Android.SauceLabs.Mobile.Sample.app.2.3.0.apk");  
			
			//Cach 2 
			//String urlApp = getClass().getResource(props.getProperty("androidAppLocation")).getFile(); 
			//capabilities.setCapability("app",  urlApp);   
			//Explain why ? 
			
			capabilities.setCapability("platformName", platformName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("deviceName", deviceName); 
			capabilities.setCapability("automationName", props.getProperty("androidAutomationName")); 
			
   
			
			capabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
			capabilities.setCapability("appPackage",  props.getProperty("androidAppPackage")); 
		
			URL url = new URL("http://127.0.0.1:4723/wd/hub");  
			driver = new AndroidDriver(url, capabilities);
			String sessionId = driver.getSessionId().toString();  
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			
	    } catch (Exception e) {
	    	e.printStackTrace(); 
	    	
	    }
} 
 
 public void waitForVisibility(MobileElement e) {
	 WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
	 wait.until(ExpectedConditions.visibilityOf(e));
	 
 } 
 
 public void click(MobileElement e) { 
	 	waitForVisibility(e); 
	 	e.click();
 } 
 
 public void sendkeys(MobileElement e, String txt) {
	 	waitForVisibility(e); 
	 	e.sendKeys(txt);
}
 
public String getAttribute(MobileElement e, String attribute) {
 	    waitForVisibility(e); 
 		return e.getAttribute(attribute);
}
 
 
 
public void quitDriver() {
	driver.quit();
}

}
