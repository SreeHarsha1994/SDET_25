package com.crm.comcast.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.FileUtility;
/**
 * 
 * @author Deepak
 *
 */
public class Login {
	
	WebDriver driver;
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="user_name")
	private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindAll({@FindBy(id = "submitButton"), @FindBy(xpath = "//input[@type='submit']")})
	private WebElement loginButton;

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 *  login to app with default credentials 
	 * @throws Throwable
	 */
	public void loginToApp() throws Throwable {	
		FileUtility fLib = new FileUtility();
		
		driver.manage().window().maximize();
		driver.get(fLib.getPropertyKeyValue("url"));
		userNameEdt.sendKeys(fLib.getPropertyKeyValue("username"));
		passwordEdt.sendKeys(fLib.getPropertyKeyValue("password"));
		loginButton.click();
	}
	/**
	 * login to app user defined credentials
	 * @param userName
	 * @param password
	 * @throws Throwable
	 */
	public void loginToApp(String url , String userName , String password) throws Throwable {	
		FileUtility fLib = new FileUtility();
		
		driver.manage().window().maximize();
		driver.get(url);
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginButton.click();
	}
}






