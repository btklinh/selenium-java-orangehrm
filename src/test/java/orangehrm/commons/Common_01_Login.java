package orangehrm.commons;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.AbstractTest;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;

public class Common_01_Login extends AbstractTest {
	
	String username, password;
	LoginPageObject loginPage;
	PimPageObject pimPage;
	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void TC_01_Login_Success(String browserName) {
		log.info("Pre-condition - Step 01: Open orangehrm site");		
		driver = getBrowserDriver(browserName);
		username = "btklinh";
		password = "Klinh1993@!";
		loginPage = new LoginPageObject(driver);
		
		log.info("Pre-condition - Step 02: Input correct Username");
		loginPage.inputToUsernameTextbox(username);
		
		log.info("Pre-condition - Step 03: Input correct Password");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Pre-condition - Step 03: Click Login button");
		pimPage = loginPage.clickToLoginButton();
	}

}
