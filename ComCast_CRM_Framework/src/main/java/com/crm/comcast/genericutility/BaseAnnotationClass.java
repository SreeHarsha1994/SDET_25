package com.crm.comcast.genericutility;

import java.sql.SQLException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectrepositoryUtility.HomePage;
import com.crm.comcast.objectrepositoryUtility.Login;

public class BaseAnnotationClass {
	public FileUtility fis=new FileUtility();
	public ExcelUtility exc=new ExcelUtility();
	public JavaUtlity jav=new JavaUtlity();
	public WebDriverUtility wdu=new WebDriverUtility();
	public DataBaseUtilities db=new DataBaseUtilities();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	@BeforeSuite(groups= {"smoke","regression"})
	public void configBS() {                                                                              //Configration BeforeSuite
		db.connectToDB();
	}
	//@Parameters("BROWSER")
	@BeforeClass(groups= {"smoke","regression"})
	public void configBc() throws Throwable{
		System.out.println("===lanuch the browser===");
		String BROWSER = fis.getPropertyKeyValue("browser");
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println();
		}
		sdriver=driver;
		wdu.waitForPageToLoad(driver);
		driver.manage().window().maximize();
	}
	@BeforeMethod(groups= {"smoke","regression"})
	public void configBM() throws Throwable  {
		System.out.println("===login to application===");
		Login lp=new Login(driver);
		String URL = fis.getPropertyKeyValue("url");
		String USERNAME = fis.getPropertyKeyValue("username");
		String BROWSER = fis.getPropertyKeyValue("browser");
		String PASSWORD = fis.getPropertyKeyValue("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(groups= {"smoke","regression"})
	public void configAm() {
		System.out.println("===close the browser===");
		HomePage hp=new HomePage(driver);
		hp.signOut();
	}
	@AfterClass(groups= {"smoke","regression"})
	public void configAc() {
		System.out.println("===close the Browser===");
	}
	@AfterSuite(groups= {"smoke","regression"})
	public void configAs() throws SQLException {
		db.closeDB();
	}
}
