package com.crm.comcast.conatctTest;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseAnnotationClass;
import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtlity;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepositoryUtility.Contacts;
import com.crm.comcast.objectrepositoryUtility.ContactsInfoPage;
import com.crm.comcast.objectrepositoryUtility.CreateNewConatctPage;
import com.crm.comcast.objectrepositoryUtility.HomePage;
import com.crm.comcast.objectrepositoryUtility.Login;

/**
 * 
 * @author SreeHarsha
 *
 */
public class CreateContact_Support_DateTest extends BaseAnnotationClass {
	@Test
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
		if(actContactinfo.contains(lastName)) {
			System.out.println(actContactinfo + "==> is created==>PASS");
		}else {
			System.out.println(actContactinfo + "==> is not created==>Fail");
		}
	}
}
