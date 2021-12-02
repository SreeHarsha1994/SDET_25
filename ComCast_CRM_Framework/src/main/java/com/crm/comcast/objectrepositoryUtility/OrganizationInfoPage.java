package com.crm.comcast.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * 
 * @author Nitheesha
 *
 */
public class OrganizationInfoPage {
	
	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement organizationInfo;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industriesInfo;
	
	@FindBy(id = "dtlview_Type")
	private WebElement typeInfo;

	public WebElement getOrganizationInfo() {
		return organizationInfo;
	}

	public WebElement getIndustriesInfo() {
		return industriesInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
	}



	

	


}
