package com.crm.comcast.conatctTest;
import static org.testng.Assert.assertEquals;
import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.comcast.genericutility.BaseAnnotationClass;
import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtlity;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepositoryUtility.Contacts;
import com.crm.comcast.objectrepositoryUtility.ContactsInfoPage;
import com.crm.comcast.objectrepositoryUtility.CreateNewConatctPage;
import com.crm.comcast.objectrepositoryUtility.CreateOrganizationPage;
import com.crm.comcast.objectrepositoryUtility.HomePage;
import com.crm.comcast.objectrepositoryUtility.Login;
import com.crm.comcast.objectrepositoryUtility.OrganizationInfoPage;
import com.crm.comcast.objectrepositoryUtility.OrganizationPage;
/**
 * 
 * @author Sreeharsha
 *
 */
public class CreateContactTest extends BaseAnnotationClass{
	SoftAssert sa=new SoftAssert();
	@Test(groups="smoke")
	public void  createContactTest() throws Throwable {
		/* get ramDomData */
		int randomNum = jav.getRandomNumber();
		/* read test data from Excel File*/
		String lastName = exc.getDataFromExcel("contact", 1, 2) + randomNum;
		/* step 1 : navigate to Contacts Page */ 
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click(); 
		/* step 2 :  navigate to CREATE  Contact Page*/ 
		Contacts cp = new Contacts(driver);
		cp.getCreateNewConatctIMG().click();
		/* step 3 : create a new Contact Page */
		CreateNewConatctPage cncp = new CreateNewConatctPage(driver);
		cncp.createNewConatct(lastName);
		/* step 4 : verify */ 
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String  actContactinfo = cip.getContactSucMsg().getText();
		/* step 4: Assertion*/
		 boolean Lastname = actContactinfo.contains(lastName);
		 Assert.assertTrue(Lastname);
		 sa.assertTrue(Lastname);
		 System.out.println(Lastname);
		// sa.assertAll();
	} 
	@Test(groups="regression")
	public void createContact_Support_DateTest() throws Throwable {
		/* get ramDomData */
		int randomNum = jav.getRandomNumber();
		/* read test data from Excel File*/
		String lastName = exc.getDataFromExcel("contact", 7, 2) + randomNum;
		String mobileNumber = exc.getDataFromExcel("contact", 7, 3);
		/* step 1 : navigate to Contacts Page */ 
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click(); 
		/* step 2 :  navigate to CREATE  Contact Page*/ 
		Contacts cp = new Contacts(driver);
		cp.getCreateNewConatctIMG().click();
		/* step 3 : create a new Contact Page */
		CreateNewConatctPage cncp = new CreateNewConatctPage(driver);
		cncp.createNewConatct(lastName, mobileNumber, jav.getDateFormate());
		/* step 4 : verify */ 
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String  actContactinfo = cip.getContactSucMsg().getText();
		boolean Lastname = actContactinfo.contains(lastName);
		Assert.assertTrue(Lastname);
		sa.assertTrue(Lastname);
		System.out.println(Lastname);
		//sa.assertAll();
	}
	@Test(groups="regression")
	public void createConatctWithOrgTest() throws Throwable {
		/* get ramDomData */
		int randomNum = jav.getRandomNumber();
		/* read test data from Excel File*/
		String orgName = exc.getDataFromExcel("contact", 4, 2) + randomNum;
		String conactName = exc.getDataFromExcel("contact", 4, 3) + randomNum;
		/* step 2 :  navigate to Org Page*/ 
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();             
		/* step 3 : navigate to create Org page */  
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIMG().click();
		/* step 3 : create a new Org */   
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrganization(orgName);
		/* step 4 : verify */ 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		wdu.waitForElemnetToBeClickAble(driver, oip.getOrganizationInfo());
		/* step 5 :  navigate to CREATE  Coantct Page*/ 
		hp.getContactLink().click();
		/* step 6 :  navigate to CREATE  Contact Page*/ 
		Contacts cp = new Contacts(driver);
		cp.getCreateNewConatctIMG().click();
		/* step 7 : create a new Contact Page */
		CreateNewConatctPage cncp = new CreateNewConatctPage(driver);
		cncp.createNewConatct(conactName, orgName);
		/* step 8 : verify */ 
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String  actContactinfo = cip.getContactSucMsg().getText();
		boolean Contact =actContactinfo .contains(conactName);
		Assert.assertTrue(Contact,"Contact is true");
		sa.assertTrue(Contact);
		System.out.println("===End Test===");
		//sa.assertAll();
	}
}
