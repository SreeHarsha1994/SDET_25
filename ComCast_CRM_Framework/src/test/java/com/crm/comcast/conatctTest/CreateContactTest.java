package com.crm.comcast.conatctTest;

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
import org.testng.annotations.Test;

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
 * @author Deepak
 *
 */
public class CreateContactTest{

	@Test
	public void  createContactTest() throws Throwable {
		
        /* create object to libraries*/
		FileUtility flib = new FileUtility();
		JavaUtlity jLib = new JavaUtlity();
		WebDriverUtility wLib = new WebDriverUtility();
        ExcelUtility eLib = new ExcelUtility();
		/* get ramDomData */
		int randomNum = jLib.getRandomNumber();
		
		/* read common data from Properties File*/
		 String BROWER = flib.getPropertyKeyValue("browser");
		 String URL = flib.getPropertyKeyValue("url");
		 String USERNAME = flib.getPropertyKeyValue("username");
		 String PASSWORD = flib.getPropertyKeyValue("password");
		 
		 /* read test data from Excel File*/
		    String lastName = eLib.getDataFromExcel("contact", 1, 2) + randomNum;
 
         /* launch the Browser */ 
         WebDriver driver = null;
         if(BROWER.equals("chrome")) {
              driver = new ChromeDriver();
         }else if(BROWER.equals("firefox")){
        	  driver = new FirefoxDriver();
         }else if(BROWER.equals("ie")){
       	  driver = new InternetExplorerDriver();
        }else {
            driver = new ChromeDriver();
        }
         wLib.waitForPageToLoad(driver);
         driver.get(URL);
         /* step 1 : login to APP */ 
         Login lp = new Login(driver);
         lp.loginToApp(URL, USERNAME, PASSWORD);
         
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
         if(actContactinfo.contains(lastName)) {
        	 System.out.println(actContactinfo + "==> is created==>PASS");
         }else {
        	 System.out.println(actContactinfo + "==> is not created==>Fail");

         }
         
    /* step 5 : logout */ 
     hp.signOut();
     driver.quit();


	}
	
	

	

}
