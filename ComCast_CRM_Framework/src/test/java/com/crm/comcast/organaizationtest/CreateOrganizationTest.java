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
import org.testng.annotations.Test;

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
 * @author Deepak
 *
 */
public class CreateOrganizationTest{

	
	
	@Test
	public void createOrganizationTest() throws Throwable {
		
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
		    String orgName = eLib.getDataFromExcel("org", 1, 2) + randomNum;
		    
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
                 
         /* step 2 :  login */ 
              Login lp = new Login(driver);
              lp.loginToApp(URL, USERNAME, PASSWORD);
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
              if(orgNameInfoMsg.contains(orgName)) {
            	  System.out.println(orgName + "==>is created==>PASS");
              }else {
            	  System.out.println(orgName + "==>is not created==>fAIL");

              }
              
         /* step 5 : logout */ 
          hp.signOut();
          driver.quit();

	}
	
	

	

}
