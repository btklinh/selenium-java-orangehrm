package orangehrm.page;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;

public class Admin_Page_Access_01 extends AbstractTest {

	String username, password;
	UserPageObject userPage;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
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

	@Test
	public void TC_01_Verify_Access_User_Page() {
		log.info("TC01 - Step 01: Click to Admin menu");
		pimPage.clickToLeftMenuByName(driver, "Admin");
		userPage = PageGeneratorManager.getUserPage(driver);

		log.info("Pre-condition - Step 04: Verify whether sub tile of page is System Users");
		assertEquals(userPage.getPageTitleContainSearch(driver), "System Users");
	}
	
	@Test
	public void TC_02_Verify_Job_Titles_Page() {
		log.info("TC02 - Step 01: Click to Job on the upper menu");
		userPage.clickToParentMenuByName(driver, "Job");
		userPage.clickToDropdownMenuByName(driver, "Job Titles");
		jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);
		
		log.info("TC02 - Step 02: Click to Job Titles sub menu");
		assertEquals(userPage.getPageTitleContainConfigure(driver), "Job Titles");
	}
	
	@Test
	public void TC_03_Verify_Job_Pay_Grades_Page() {
		log.info("TC03 - Step 01: Click to Job on the upper menu");
		log.info("TC03 - Step 02: Click to Job on the upper menu");
	}

}
