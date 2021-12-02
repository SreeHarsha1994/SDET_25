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
public class OrganizationPage {
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrganizationIMG;

	@FindBy(id="search_txt")
	private WebElement searchTF;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	public WebElement getCreateOrganizationIMG() {
		return createOrganizationIMG;
	}
	
	
	public WebElement getSearchTF() {
		return searchTF;
	}


	public void setSearchTF(WebElement searchTF) {
		this.searchTF = searchTF;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	


	
	

}
