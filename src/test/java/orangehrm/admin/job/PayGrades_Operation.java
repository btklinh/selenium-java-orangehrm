package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class PayGrades_Operation extends AbstractTest {

	String username, password;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	PayGradesPageObject payGradesPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String payGradeName, minimumSalary, maximumSalary, currencyValue;
	String url;
	List<String> currencyList;

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void Precondition_Access_Pay_Grades_List(String browserName) {
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
		userPage.clickToDropdownMenuByName(driver, "Pay Grades");
		payGradesPage = PageGeneratorManager.getPayGradesPage(driver);
		url = payGradesPage.getCurrentPageUrl(driver);
	}

	//@Test
	public void TC_01_Verify_Add_Pay_Grade() {

		payGradeName = payGradesPage.generatePayGradeName();
		log.info("TC01 - Step 01: Click Add button");
		payGradesPage.clickToAddButton();

		log.info("TC01 - Step 02: Enter Pay Grade in Name field");
		payGradesPage.inputToPayGradeNameTextbox(payGradeName);

		log.info("TC01 - Step 03: Click to Save button");
		payGradesPage.clickToPayGradeSaveButton();

		log.info("TC01 - Step 04: Verify page is Move to Edit screen");
		assertEquals(payGradesPage.checkPageTitle(), "Edit Pay Grade");

		log.info("TC01 - Step 05: Verify the Pay Grade is added into list");
		payGradesPage.clickToParentMenuByName(driver, "Job");
		payGradesPage.clickToDropdownMenuByName(driver, "Pay Grades");
		assertEquals(payGradesPage.checkPayGradesInTheList(payGradeName), true);
	}

	@Test
	public void TC_02_Verify_Add_Currency() {
		
		//Data Test
		minimumSalary = String.valueOf(data.getMinimumValue());
		maximumSalary = String.valueOf(data.getMaximumValue());


		log.info("TC02 - Step 01: Click Edit button");
		payGradesPage.clickToEditIconOfPayGrade("Grade 2");
		
		log.info("TC02 - Step 02: Click Add Currencies button");
		payGradesPage.clickToAddCurrencyButton();
		
		log.info("TC02 - Step 03: Select Currency");
		payGradesPage.clickToArrowIcon();
		currencyValue = payGradesPage.getRandomCurrencyValue();		
		payGradesPage.selectCurrency(currencyValue);
		
		log.info("TC02 - Step 04: Input Minimum Salary and Maximum Salary");
		payGradesPage.inputToMinimumSalaryTextbox(minimumSalary);
		payGradesPage.inputToMaximumSalaryTextbox(maximumSalary);
		
		log.info("TC02 - Step 05: Click Save button");
		payGradesPage.clickToSaveCurrencyButton();
		
		log.info("TC02 - Step 06: Verify Successful message displayed");
		assertEquals(payGradesPage.getSuccessMessage(), "Successfully Saved");
		
		log.info("TC02 - Step 07: Verify whether currency is added into list");
		assertEquals(payGradesPage.checkCurrencyInTheList(currencyValue.substring(6)), true);
		assertEquals(payGradesPage.checkMiniumumSalaryIntheListminimumSalary(minimumSalary.concat(".00")), true);
		assertEquals(payGradesPage.checkMaximumSalaryInTheList(maximumSalary.concat(".00")), true);
		
	}

//	@Test
//	public void TC_02_Verify_Edit_Job_Title() {
//		log.info("TC02 - Step 01: Click to Edit icon");
//		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
//
//		log.info("TC02 - Step 02: Edit information");
//		jobTitle = data.getJobTitle();
//		jobTitlesPage.inputToJobTitleTexbox(jobTitle);
//		jobTitlesPage.inputToJobDescriptionTextbox(data.getAddress());
//		jobTitlesPage.inputToJobNoteTextbox(data.getAddress());
//
//		log.info("TC02 - Step 03: Click Save button");
//		jobTitlesPage.clickToSaveButton();
//
//		log.info("TC02 - Step 04: Verify Successful popup is displayed");
//		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");
//
//		log.info("TC02 - Step 05: Verify the Job Title is added into list");
//		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
//	}
//
//	@Test
//	public void TC_03_Verify_Edit_Job_Specification_File() {
//		fileName = "Doc3.docx";
//		directory = GlobalConstants.ROOT_FOLDER + "\\upload-files\\" + fileName;
//		log.info("TC03 - Step 01: Click to Edit icon");
//		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
//
//		log.info("TC03 - Step 02: Select Replace Current");
//		jobTitlesPage.selectReplaceCurrentRadio();
//
//		log.info("TC03 - Step 03: Click to Browse icon");
//		jobTitlesPage.clickToBrowseButton();
//
//		log.info("TC03 - Step 04: Select a valid file");
//		jobTitlesPage.selectUploadFile(directory);
//
//		log.info("TC03 - Step 05: Click Save button");
//		jobTitlesPage.clickToSaveButton();
//
//		log.info("TC03 - Step 06: Verify Successful popup is displayed");
//		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");
//
//		log.info("TC03 - Step 07: Assert that file is upload");
//		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
//		assertEquals(jobTitlesPage.getUploadedFileName(), fileName);
//	}
//
//	@Test
//	public void TC_04_Verify_Delete_Job_Specification_File() {
//		jobTitlesPage.clickToCancelButton();
//		
//		log.info("TC04 - Step 01: Click to Edit icon");
//		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
//
//		log.info("TC04 - Step 02: Select Delete Current radio button");
//		jobTitlesPage.selectDeleteCurrentRadio();
//
//		log.info("TC04 - Step 03: Click Save button");
//		jobTitlesPage.clickToSaveButton();
//
//		log.info("TC04 - Step 06: Verify Successful popup is displayed");
//		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Updated");
//
//		log.info("TC04 - Step 07: Verify that file is deleted");
//		jobTitlesPage.clickToEditIconOfJobTitle(jobTitle);
//		assertEquals(jobTitlesPage.isDeleteRadioButtonUndisplayed(), true);
//	}
//
//	@Test
//	public void TC_05_Verify_Delete_Operation() {
//		jobTitlesPage.clickToCancelButton();
//		confirmMessage = "The selected record will be permanently deleted. Are you sure you want to continue?";
//
//		log.info("TC05 - Step 01: Click To Delete button");
//		jobTitlesPage.clickToDeleteButtonOfJobTitle(jobTitle);
//
//		log.info("TC05 - Step 02: Verify confirmation popup is displayed");
//		assertEquals(jobTitlesPage.getConfirmationPopupMessage(), confirmMessage);
//
//		log.info("TC05 - Step 03: Click to No, Cancel");
//		jobTitlesPage.clickToNoButton();
//
//		log.info("TC05 - Step 04: Verify job title is not deleted");
//		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), true);
//
//		log.info("TC05 - Step 05: Click to Delete button");
//		jobTitlesPage.clickToDeleteButtonOfJobTitle(jobTitle);
//
//		log.info("TC05 - Step 06: Click to Yes, Delete button");
//		jobTitlesPage.clickToYesButton();
//
//		log.info("TC05 - Step 07: Verify Successful popup is displayed");
//		assertEquals(jobTitlesPage.getSuccessMessage(), "Successfully Deleted");
//
//		log.info("TC05 - Step 08: Verify job title is deleted");
//		assertEquals(jobTitlesPage.checkJobTitleInTheList(jobTitle), false);
//
//	}


	public void afterClass() {
		driver.quit();
	}

}
