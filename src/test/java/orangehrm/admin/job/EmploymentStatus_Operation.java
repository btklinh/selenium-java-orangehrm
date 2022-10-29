package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.EmploymentStatusPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class EmploymentStatus_Operation extends AbstractTest {
	PimPageObject pimPage;
	LoginPageObject loginPage;
	EmploymentStatusPageObject employmentStatusPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String url, employmentStatusName;
	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void Precondition_Login(String browserName) {
		log.info("Pre-condition - Step 01: Open orangehrm site");
		driver = getBrowserDriver(browserName);
		loginPage = new LoginPageObject(driver);

		log.info("Pre-condition - Step 02: Input correct Username");
		loginPage.inputToUsernameTextbox(GlobalConstants.LOGIN_USERNAME);

		log.info("Pre-condition - Step 03: Input correct Password");
		loginPage.inputToPasswordTextbox(GlobalConstants.LOGIN_PASSWORD);

		log.info("Pre-condition - Step 03: Click Login button");
		pimPage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 04: Click To Admin on the left menu");
		pimPage.clickToLeftMenuByName(driver, "Admin");
		userPage = PageGeneratorManager.getUserPage(driver);

		log.info("Pre-condition - Step 05: Access Job Titles page");
		userPage.clickToParentMenuByName(driver, "Job");
		userPage.clickToDropdownMenuByName(driver, "Employment Status");
		employmentStatusPage = PageGeneratorManager.getEmploymentStatusPage(driver);
		url = employmentStatusPage.getCurrentPageUrl(driver);
		employmentStatusName = data.getEmploymentStatus();
	}

	@Test
	public void TC_01_Verify_Add_Employment_Status() {
		log.info("TC_01 - Step 01: Click Add button");
		employmentStatusPage.clickAdd();
		
		log.info("TC_01 - Step 02: Input valid Name");
		employmentStatusPage.inputName(employmentStatusName);
		
		log.info("TC_01 - Step 03: Click Save button");
		employmentStatusPage.clickSave();
		
		log.info("TC_01 - Step 04: Verify success message displayed");
		assertEquals(employmentStatusPage.getSuccessMessage(), "Successfully Saved");
		
		log.info("TC_01 - Step 05: Verify Employment status is added in list");
		assertTrue(employmentStatusPage.statusIsAddedToList(employmentStatusName));
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}

}
