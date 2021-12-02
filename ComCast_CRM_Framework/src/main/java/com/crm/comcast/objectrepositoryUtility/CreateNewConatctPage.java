package com.crm.comcast.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.genericutility.WebDriverUtility;

/**
 * 
 * @author Nithesh
 *
 */
public class CreateNewConatctPage extends WebDriverUtility{
	WebDriver driver;
	public CreateNewConatctPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}

	
	@FindBy(name = "lastname")
	private WebElement lastNAmeTxt;

	@FindBy(name = "mobile")
	private WebElement mobileTxt;
	
	@FindBy(name = "support_end_date")
	private WebElement supportDate;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	private WebElement orgName_LookUpImg;
	
	
	
	/**
	 * @author Nehal
	 *  create new Contact with lastName
	 * @param lastName
	 */
	public void createNewConatct(String lastName) {
		lastNAmeTxt.sendKeys(lastName);
		saveBtn.click();
	}
	
	/**
	 * @author deepak
	 *  create new Contact with lastName & date , mobileNuber
	 * @param lastName , mobileNum
	 */
	public void createNewConatct(String lastName , String mobileNum, String date) {
		lastNAmeTxt.sendKeys(lastName);
		mobileTxt.sendKeys(mobileNum);
		supportDate.clear();
		supportDate.sendKeys(date);
		saveBtn.click();
	}
	
	/**
	 * @author deepak
	 *  create new Contact with lastName & date , mobileNuber
	 * @param lastName , mobileNum
	 * @throws InterruptedException 
	 */
	public void createNewConatct(String lastName , String orgName) throws InterruptedException {
		lastNAmeTxt.sendKeys(lastName);
		orgName_LookUpImg.click();
		swithToWindow(driver, "Accounts");
		OrganizationPage op = new OrganizationPage(driver);
		op.getSearchTF().sendKeys(orgName);
		op.getSearchBtn().click();
		WebElement wb = driver.findElement(By.xpath("//a[text()='"+orgName+"']"));
		int count = 0;
		while(count<20) {
			try {
			    wb.click();
			    break;
			}catch (Exception e) {
				 count++;
				 Thread.sleep(100);
			}
		}
		
		swithToWindow(driver, "Contacts");
		saveBtn.click();
	}
	
}








