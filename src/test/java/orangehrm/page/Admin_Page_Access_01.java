package orangehrm.page;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.EmploymentStatusPageObject;
import pageObjects.JobCategoriesPageObject;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import pageObjects.WorkShiftsPageObject;

public class Admin_Page_Access_01 extends AbstractTest {

	String username, password;
	UserPageObject userPage;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;
	PayGradesPageObject payGradesPage;
	EmploymentStatusPageObject employmentStatusPage;
	JobCategoriesPageObject jobCategoriesPage;
	WorkShiftsPageObject workShiftsPage;
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
		assertEquals(jobTitlesPage.getPageTitleContainConfigure(driver), "Job Titles");
	}

	@Test
	public void TC_03_Verify_Job_Pay_Grades_Page() {
		log.info("TC03 - Step 01: Click to Job on the upper menu");
		jobTitlesPage.clickToParentMenuByName(driver, "Job");
		jobTitlesPage.clickToDropdownMenuByName(driver, "Pay Grades");
		payGradesPage = PageGeneratorManager.getPayGradesPage(driver);

		log.info("TC03 - Step 02: Click to Pay Grades on the upper menu");
		assertEquals(payGradesPage.getPageTitleContainConfigure(driver), "Pay Grades");
	}

	@Test
	public void TC_04_Verify_Job_Employee_Status_Page() {
		log.info("TC03 - Step 01: Click to Job on the upper menu");
		payGradesPage.clickToParentMenuByName(driver, "Job");
		payGradesPage.clickToDropdownMenuByName(driver, "Employment Status");
		employmentStatusPage = PageGeneratorManager.getEmploymentStatusPage(driver);

		log.info("TC03 - Step 02: Click to Employment Status on the upper menu");
		assertEquals(employmentStatusPage.getPageTitleContainConfigure(driver), "Employment Status");
	}

	@Test
	public void TC_05_Verify_Job_Job_Categories_Page() {
		log.info("TC03 - Step 01: Click to Job on the upper menu");
		employmentStatusPage.clickToParentMenuByName(driver, "Job");
		employmentStatusPage.clickToDropdownMenuByName(driver, "Job Categories");
		jobCategoriesPage = PageGeneratorManager.getJobCategoriesPage(driver);

		log.info("TC03 - Step 02: Click to Job Categories on the upper menu");
		assertEquals(jobCategoriesPage.getPageTitleContainConfigure(driver), "Job Categories");
	}

	@Test
	public void TC_06_Verify_Job_Work_Shifts_Page() {
		log.info("TC03 - Step 01: Click to Job on the upper menu");
		jobCategoriesPage.clickToParentMenuByName(driver, "Job");
		jobCategoriesPage.clickToDropdownMenuByName(driver, "Work Shifts");
		workShiftsPage = PageGeneratorManager.getWorkShiftsPage(driver);

		log.info("TC03 - Step 02: Click to Work Shifts on the upper menu");
		assertEquals(workShiftsPage.getPageTitleContainConfigure(driver), "Work Shifts");
	}

	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}
}
