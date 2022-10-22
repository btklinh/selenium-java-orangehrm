package orangehrm.login;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;


public class LoginPage_Login extends AbstractTest {

	String username, password;
	LoginPageObject loginPage;
	PimPageObject pimPage;
	WebDriver driver;

	@Parameters("browser")
	@Test
	public void TC_01_Login_Success(String browserName) {
		log.info("TC 01 - Step 01: Open orangehrm site");
		driver = getBrowserDriver(browserName);
		username = "btklinh";
		password = "Klinh1993@!";
//		loginPage = new LoginPageObject(driver);

		log.info("TC 01- Step 02: Input correct Username");
		loginPage.inputToUsernameTextbox(username);

		log.info("TC 01 - Step 03: Input correct Password");
		loginPage.inputToPasswordTextbox(password);

		log.info("TC 01 - Step 03: Click Login button");
		pimPage = loginPage.clickToLoginButton();

		log.info("TC 01 - Step 04: Verify PIM page displayed");
		assertEquals(pimPage.getPageTitle(), "PIM");
		driver.quit();

		log.info("TC 01 - Step 04: Verify PIM page displayed");
	
	}
}
