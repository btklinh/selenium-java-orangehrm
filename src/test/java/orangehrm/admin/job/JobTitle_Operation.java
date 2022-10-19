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

public class JobTitle_Operation extends AbstractTest {

	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String jobTitle, jobDescription, jobNote, directory, fileName, confirmMessage, url;

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
	public void TC_01_Verify_Add_Job_Title() {
		fileName = "Doc2.docx";
		directory = GlobalConstants.ROOT_FOLDER + "\\upload-files\\" + fileName;
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
		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Saved");

		log.info("TC01 - Step 09: Verify the Job Title is added into list");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
	}

	@Test
	public void TC_02_Verify_Edit_Job_Title() {
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
		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");

		log.info("TC02 - Step 05: Verify the Job Title is added into list");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
	}

	@Test
	public void TC_03_Verify_Edit_Job_Specification_File() {
		fileName = "Doc3.docx";
		directory = GlobalConstants.ROOT_FOLDER + "\\upload-files\\" + fileName;
		log.info("TC03 - Step 01: Click to Edit icon");
		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);

		log.info("TC03 - Step 02: Select Replace Current");
		jobTitlesPage.selectReplaceCurrentRadio();

		log.info("TC03 - Step 03: Click to Browse icon");
		jobTitlesPage.clickToBrowseButton();

		log.info("TC03 - Step 04: Select a valid file");
		jobTitlesPage.selectUploadFile(directory);

		log.info("TC03 - Step 05: Click Save button");
		jobTitlesPage.clickToSaveButton();

		log.info("TC03 - Step 06: Verify Successful popup is displayed");
		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");

		log.info("TC03 - Step 07: Assert that file is upload");
		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
		assertEquals(jobTitlesPage.getUploadedFileName(), fileName);
	}

	@Test
	public void TC_04_Verify_Delete_Job_Specification_File() {
		jobTitlesPage.clickToCancelButton();
		
		log.info("TC04 - Step 01: Click to Edit icon");
		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);

		log.info("TC04 - Step 02: Select Delete Current radio button");
		jobTitlesPage.selectDeleteCurrentRadio();

		log.info("TC04 - Step 03: Click Save button");
		jobTitlesPage.clickToSaveButton();

		log.info("TC04 - Step 06: Verify Successful popup is displayed");
		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");

		log.info("TC04 - Step 07: Verify that file is deleted");
		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
		assertEquals(jobTitlesPage.isDeleteRadioButtonUndisplayed(), true);
	}

	@Test
	public void TC_05_Job_Title_Cannot_Be_Duplicated() {
		log.info("TC05 - Step 01: Go to Job Titles Page");
		jobTitlesPage.openJobTitlesPage(url);
		
		log.info("TC05 - Step 02: Click to Add button");
		jobTitlesPage.clickToAddButton();
		
		log.info("TC05 - Step 03: Input duplicated Job Title");
		jobTitlesPage.inputToJobTitleTexbox(jobTitle);
		
		log.info("TC05 - Step 04:  Click Save button");
		jobTitlesPage.clickToSaveButton();
		
		log.info("TC05 - Step 05: Verify Error text message");
		assertEquals(jobTitlesPage.getErrorMessageOfJobTitle(), "Already exists");
	}

	@Test
	public void TC_06_Verify_Delete_Operation() {
		jobTitlesPage.openJobTitlesPage(url);

		log.info("TC05 - Step 01: Click To Delete button");
		jobTitlesPage.clickToDeleteButtonOfJobTitle(jobTitle);

		log.info("TC05 - Step 02: Verify confirmation popup is displayed");
		assertEquals(jobTitlesPage.getConfirmationPopupMessage(), GlobalConstants.DELETE_CONFIRM_MESSAGE);

		log.info("TC05 - Step 03: Click to No, Cancel");
		jobTitlesPage.clickToNoButton();

		log.info("TC05 - Step 04: Verify job title is not deleted");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);

		log.info("TC05 - Step 05: Click to Delete button");
		jobTitlesPage.clickToDeleteButtonOfJobTitle(jobTitle);

		log.info("TC05 - Step 06: Click to Yes, Delete button");
		jobTitlesPage.clickToYesButton();

		log.info("TC05 - Step 07: Verify Successful popup is displayed");
		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Deleted");

		log.info("TC05 - Step 08: Verify job title is deleted");
		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), false);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}

}
