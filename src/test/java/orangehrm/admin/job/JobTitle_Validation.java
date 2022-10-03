package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class JobTitle_Validation extends AbstractTest {
	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String jobTitle, url, directory;
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
		userPage.clickToDropdownMenuByName(driver, "Job Titles");
		jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);
		url = jobTitlesPage.getCurrentUrl();
	}

	@Test
	public void TC_01_Job_Title_Cannot_Be_Blank() {
		log.info("TC01 - Step 01: Click Add button");
		jobTitlesPage.clickToAddButton();

		log.info("TC01 - Step 02: Do not enter a value in NAME field");
		jobTitlesPage.inputToJobTitleTexbox("");

		log.info("TC01 - Step 03: Click Save button");
		jobTitlesPage.clickToSaveButton();

		log.info("TC01 - Step 04: Verify Error text message");
		assertEquals(jobTitlesPage.getErrorMessageOfJobTitle(), "Required");
	}
	
	@Test
	public void TC_02_Job_Title_Cannot_More_Than_100_Characters() {
		jobTitle = "Account Assistant asdf efa wef asdfas dfqaw efwefasdfawefwaef dfasdfasdfawefas asdfasfdawefwaefweafd1";
		jobTitlesPage.openJobTitlesPage(url);
		
		log.info("TC01 - Step 01: Click Add button");
		jobTitlesPage.clickToAddButton();

		log.info("TC01 - Step 02: Do not enter a value in NAME field");
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);

		log.info("TC01 - Step 03: Click Save button");
		jobTitlesPage.clickToSaveButton();

		log.info("TC01 - Step 04: Verify Error text message");
		assertEquals(jobTitlesPage.getErrorMessageOfJobTitle(), "Should not exceed 100 characters");
	}

	@Test
	public void TC_03_File_Size_Cannot_More_Than_1MB() {
		jobTitle = data.getJobTitle();
		directory = GlobalConstants.ROOT_FOLDER + "\\upload-files\\invalid_file_size.docx";
		jobTitlesPage.openJobTitlesPage(url);
		
		log.info("TC03 - Step 01: Click Add button");
		jobTitlesPage.clickToAddButton();
		
		log.info("TC03 - Step 02: Input valid Job Title");
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);

		log.info("TC03 - Step 03: Click Browser on Job Specification ");
		jobTitlesPage.clickToBrowseButton();
		
		log.info("TC03 - Step 04: Select file that file size more than 1MB");
		jobTitlesPage.selectUploadFile(directory);

		log.info("TC03 - Step 05: Verify Error text message");
		assertEquals(jobTitlesPage.getErrorMessageOfJobSpecification(), "Attachment Size Exceeded");
	}

	@Test
	public void TC_04_File_Extension_Wrong_Format() {
		jobTitle = data.getJobTitle();
		directory = GlobalConstants.ROOT_FOLDER + "\\upload-files\\cypress.json";
		jobTitlesPage.openJobTitlesPage(url);
		
		log.info("TC03 - Step 01: Click Add button");
		jobTitlesPage.clickToAddButton();
		
		log.info("TC03 - Step 02: Input valid Job Title");
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);

		log.info("TC03 - Step 03: Click Browser on Job Specification ");
		jobTitlesPage.clickToBrowseButton();
		
		log.info("TC03 - Step 04: Select file that is wrong format");
		jobTitlesPage.selectUploadFile(directory);

		log.info("TC03 - Step 05: Verify Error text message");
		assertEquals(jobTitlesPage.getErrorMessageOfJobSpecification(), "File type not allowed");
	}
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}
}
