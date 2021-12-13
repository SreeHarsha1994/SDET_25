package com.crm.comcast.conatctTest;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
 * @author SreeHarsha
 *
 */
public class CreateConatctWithOrgTest extends BaseAnnotationClass{

	@Test
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
       //  SoftAssert sa=new SoftAssert();
      //   Assert.fail();
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
		if(actContactinfo.contains(conactName)) {
			System.out.println(conactName + "==> is created==>PASS");
		}else {
			System.out.println(conactName + "==> is not created==>Fail");
		}
	}
}
