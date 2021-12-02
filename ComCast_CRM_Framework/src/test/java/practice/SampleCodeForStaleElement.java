package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.objectrepositoryUtility.Login;


public class SampleCodeForStaleElement {

	public static void main(String[] args) throws Throwable {

		
		WebDriver driver = new FirefoxDriver();
		
	
			Login lp =  new Login(driver);
		
	        lp.loginToApp("http://localhost:8888", "admin", "manager");


	}

}
