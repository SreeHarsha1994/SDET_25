package com.crm.comcast.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;

/**
 * 
 * @author Deepak , Nehal
 *
 */

public class CreateOrganizationPage extends WebDriverUtility{
	WebDriver driver;
	public CreateOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement organizationNameTF;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getOrganizationNameTF() {
		return organizationNameTF;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	/**
	 * Use this method to create organization by entering mandatory field
	 * @param organizationName
	 */
	public void createOrganization(String organizationName) {
		organizationNameTF.sendKeys(organizationName);
		saveBtn.click();		
	}
	
	/**
	 * Create organziation with industry dropdown
	 * @param organizationName
	 * @param industryType
	 */
	public void createOrganization(String organizationName,String industryType) {
		organizationNameTF.sendKeys(organizationName);
		select(industryDropDown, industryType);
		saveBtn.click();
	}
	/**
	 *  create new ORganization with industries & type
	 * @param organizationName
	 * @param industryType
	 */
	public void createOrganization(String organizationName,String industryType , String type) {
		organizationNameTF.sendKeys(organizationName);
		select(industryDropDown, industryType);
		select(typeDropDown, type);
		saveBtn.click();
	}

}
