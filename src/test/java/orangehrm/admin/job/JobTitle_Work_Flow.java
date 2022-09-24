package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import pageUIs.JobTitlesPageUI;
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
		
		log.info("Pre-condition - Step 05: Access Job Titles page");
		userPage.clickToParentMenuByName(driver, "Job");
		userPage.clickToDropdownMenuByName(driver, "Job Titles");
		jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);
	}

	@Test
	public void TC_01_Verify_Add_Job_Title() throws AWTException {
		
		jobTitle = data.getJobTitle();
		jobDescription = "This is a test description";
		jobNote = "This is a test note";

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
		
		log.info("TC01 - Step 09: Verify Successful popup is displayed");
		assertEquals(jobTitlesPage.getSuccessMessage(),"Successfully Saved");
	
		log.info("TC01 - Step 09: Verify the Job Title is added into list");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
	}
	
	@Test
	public void TC_02_Verify_Edit_Job_Title() throws InterruptedException {
		log.info("TC02 - Step 01: Click to Edit icon");
		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
		
		log.info("TC02 - Step 02: Edit information");
		jobTitle = data.getJobTitle();		
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);
		jobTitlesPage.inputToJobDescriptionTextbox(data.getAddress());
		jobTitlesPage.inputToJobNoteTextbox(data.getAddress());
		
		log.info("TC02 - Step 03: Click Save button");
		jobTitlesPage.clickToSaveButton();
		
		log.info("TC02 - Step 04: Verify Successful popup is displayed");
		assertEquals(jobTitlesPage.getSuccessMessage(),"Successfully Updated");
	
		log.info("TC02 - Step 05: Verify the Job Title is added into list");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
	}
	
	
	public void afterClass() {
		driver.quit();
	}

}
