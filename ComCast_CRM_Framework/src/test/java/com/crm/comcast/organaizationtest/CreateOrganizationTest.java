package com.crm.comcast.organaizationtest;
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
public class CreateOrganizationTest extends BaseAnnotationClass{
	SoftAssert sa=new SoftAssert();
	@Test(groups="smoke")
	public void createOrganizationTest() throws Throwable {
		/* get ramDomData */
		int randomNum = jav.getRandomNumber();
		/* read test data from Excel File*/
		String orgName = exc.getDataFromExcel("org", 1, 2) + randomNum;
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
		String orgNameInfoMsg =  oip.getOrganizationInfo().getText();
		boolean Organization = orgNameInfoMsg.contains(orgName);
		Assert.assertTrue(Organization);
		sa.assertTrue(Organization);
		System.out.println(Organization);
		//sa.assertAll();

	}
	@Test(groups="regression")
	public void  createOrganization_With_Industries_And_Type_Test() throws Throwable {
		/* get ramDomData */
		int randomNum = jav.getRandomNumber();
		/* read test data from Excel File*/
		String orgName = exc.getDataFromExcel("org", 4, 2) + randomNum;
		String industries = exc.getDataFromExcel("org", 4, 3);
		String type = exc.getDataFromExcel("org", 4, 4);
		/* step 2 :  navigate to Org Page*/ 
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();             
		/* step 3 : navigate to create Org page */  
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIMG().click();
		/* step 3 : create a new Org  with indutries & type*/   
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrganization(orgName, industries, type);
		/* step 4 : verify */ 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgNameInfoMsg =  oip.getOrganizationInfo().getText();
		boolean Org = orgNameInfoMsg.contains(orgName);
		Assert.assertTrue(Org);
		String actIndustriesinfo =  oip.getIndustriesInfo().getText();
		boolean Indu = actIndustriesinfo.contains(industries);
		Assert.assertTrue(Indu);
		sa.assertTrue(Indu);
		System.out.println(Indu);
		String actTypeinfo =  oip.getTypeInfo().getText();
		boolean Type = actTypeinfo.contains(type);
		Assert.assertTrue(Type);
		sa.assertTrue(Type);
		System.out.println(Type);
		//sa.assertAll();
	}
}

