package orangehrm.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;

public class LoginPage_Forgot_Password extends AbstractTest {
	
	String username, password;
	LoginPageObject loginPage;
	PimPageObject pimPage;
	WebDriver driver;

	@Parameters("browser")
	@Test
	public void TC_01_Login_Success(String browserName) {

	}
}
//title: //h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-level']

//sub title //div[@class='orangehrm-header-container']/*[1]
//sub title of page have search function: //*[contains(@class,'oxd-table-filter-title')]
//sub title of page have configuration: orangehrm-main-title
//sub title of page have configuration: //*[contains(@class, 'orangehrm-main-title')]