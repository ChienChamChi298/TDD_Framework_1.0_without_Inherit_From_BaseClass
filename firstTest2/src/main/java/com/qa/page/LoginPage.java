package com.qa.page;

import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage { 
	  @AndroidFindBy(accessibility="test-Username1") private MobileElement userNameTxtFld;   
	  @AndroidFindBy(accessibility="test-Password") private MobileElement passwordTextFld;  
	  @AndroidFindBy(accessibility="test-LOGIN") private MobileElement buttonLogin;  
	  @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private MobileElement err;  
	  BaseTest base; 
	public LoginPage() { 
		 base = new BaseTest();
		 PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}

public LoginPage enterUserName(String userName) {
	base.sendkeys( userNameTxtFld, userName); 
	return this;
}  

public LoginPage enterPassword(String password) {
	base.sendkeys( passwordTextFld, password); 
	return this;
}   

public ProductPage pressLoginBtn() {
	base.click(buttonLogin); 
	return new ProductPage();
}  

public String errTxt() {
	return base.getAttribute(err, "text");
}

}
