package orangehrm.admin.job;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

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
import utilities.DataHelper;

public class JobTitle_Work_Flow extends AbstractTest {

	String username, password;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String jobTitle, jobDescription, jobNote, directory;

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void Precondition_Login(String browserName) {
		jobTitle = data.getJobTitle();
		jobDescription = "This is a test description";
		jobNote = "This is a test note";
		directory="D:\\Doc2.docx";

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

		log.info("Pre-condition - Step 04: Click To Admin on the left menu");
		pimPage.clickToLeftMenuByName(driver, "Admin");
		userPage = PageGeneratorManager.getUserPage(driver);
	}

	@Test
	public void TC_01_Verify_Add_Job_Title() throws AWTException {

		log.info("TC01 - Step 01: Access Job Titles page");
		userPage.clickToParentMenuByName(driver, "Job");
		userPage.clickToDropdownMenuByName(driver, "Job Titles");
		jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);

		log.info("TC01 - Step 02: Click Add button");
		jobTitlesPage.clickToAddButton();

		log.info("TC01 - Step 03: Enter Job title");
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);
		
		log.info("TC01 - Step 04: Click Browse on Job Specification");
		jobTitlesPage.clickToBrowseButton();

		log.info("TC01 - Step 05: Select file that is correct format");
		jobTitlesPage.selectUploadFile(directory);

		log.info("TC01 - Step 06: Enter Job Description");
		jobTitlesPage.inputToJobDescriptionTextbox(jobDescription);

		log.info("TC01 - Step 07: Enter Job Note");
		jobTitlesPage.inputToJobNoteTextbox(jobNote);

		log.info("TC01 - Step 08: Click Save button");
		jobTitlesPage.clickToSaveButton();
	}

}
