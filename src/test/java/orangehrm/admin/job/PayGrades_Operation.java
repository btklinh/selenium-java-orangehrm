package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

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
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class PayGrades_Operation extends AbstractTest {


	PimPageObject pimPage;
	LoginPageObject loginPage;
	PayGradesPageObject payGradesPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String payGradeName, minimumSalary, maximumSalary, currencyValue;
	String url;

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
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
		userPage.clickToDropdownMenuByName(driver, "Pay Grades");
		payGradesPage = PageGeneratorManager.getPayGradesPage(driver);
		url = payGradesPage.getPayGradesUrl();
	}

	@Test
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
		assertFalse(payGradesPage.checkPayGradesUndisplayedInTheList(payGradeName));
	}

	@Test
	public void TC_02_Verify_Add_Currency() {

		// Data Test
		minimumSalary = String.valueOf(data.getMinimumValue());
		maximumSalary = String.valueOf(data.getMaximumValue());

		log.info("TC02 - Step 01: Click Edit button");
		payGradesPage.clickToEditIconOfPayGrade(payGradeName);

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
		payGradesPage.clickToSaveCurrencyButton("Add Currency");

		log.info("TC02 - Step 06: Verify Successful message displayed");
		assertEquals(payGradesPage.getSuccessMessage(), "Successfully Saved");

		log.info("TC02 - Step 07: Verify whether currency is added into list");
		assertTrue(payGradesPage.checkCurrencyInTheList(currencyValue.substring(6), "Add Currency"));
		assertTrue(payGradesPage.checkMiniumumSalaryIntheList(currencyValue.substring(6), "Add Currency",
				minimumSalary.concat(".00")));
		assertTrue(payGradesPage.checkMaximumSalaryInTheList(currencyValue.substring(6), "Add Currency",
				maximumSalary.concat(".00")));
	}

	@Test
	public void TC_03_Verify_Edit_Currency() {

		// Data Test
		minimumSalary = String.valueOf(data.getMinimumValue());
		maximumSalary = String.valueOf(data.getMaximumValue());

		log.info("TC03 - Step 01: Click Edit button on Currency");
		payGradesPage.clickToEditIconOfCurrency(currencyValue.substring(6));

		log.info("TC03 - Step 03: Input Minimum and Maximum Salary");
		payGradesPage.inputToMinimumSalaryTextbox(minimumSalary);
		payGradesPage.inputToMaximumSalaryTextbox(maximumSalary);

		log.info("TC03 - Step 04: Click Save button");
		payGradesPage.clickToSaveCurrencyButton("Edit Currency");

		log.info("TC03 - Step 05: Verify Successful message displayed");
		assertEquals(payGradesPage.getSuccessMessage(), "Successfully Updated");

		log.info("TC03 - Step 06: Verify whether currency is edited");
		assertTrue(payGradesPage.checkMiniumumSalaryIntheList(currencyValue.substring(6), "Edit Currency",
				minimumSalary.concat(".00")));
		assertTrue(payGradesPage.checkMaximumSalaryInTheList(currencyValue.substring(6), "Edit Currency",
				maximumSalary.concat(".00")));
	}

	@Test
	public void TC_04_Verify_Delete_Currency() {
		
		log.info("TC04 - Step 01: Click To Delete button");
		payGradesPage.clickToDeleteCurrencyButton(currencyValue.substring(6));
				
		log.info("TC04 - Step 02: Verify confirmation popup is displayed");
		assertEquals(payGradesPage.getConfirmationPopupMessage(), GlobalConstants.DELETE_CONFIRM_MESSAGE);
		
		log.info("TC04 - Step 03: Click to No, Cancel");
		payGradesPage.clickToNoButton();
		
		log.info("TC04 - Step 04: Verify currency is not deleted");
		assertTrue(payGradesPage.checkCurrencyInTheList(currencyValue.substring(6), "Add Currency"));
		
		log.info("TC04 - Step 05: Click to Delete button");
		payGradesPage.clickToDeleteCurrencyButton(currencyValue.substring(6));
		
		log.info("TC04 - Step 06: Click to Yes, Delete button");
		payGradesPage.clickToYesButton();
		
		log.info("TC04 - Step 07: Verify Successful popup is displayed");
		assertEquals(payGradesPage.getSuccessMessage(), "Successfully Deleted");
		
		log.info("TC04 - Step 08: Verify currency is deleted");
		assertFalse(payGradesPage.checkCurrencyDeleted(currencyValue.substring(6)));
	}
	
	@Test
	public void TC_05_Verify_Edit_Pay_Grades() {
		
		log.info("TC05 - Step 01: Open Pay Grades page");
		payGradesPage.openPayGradePage(url);
		
		log.info("TC05 - Step 02: Click to Edit icon");
		payGradesPage.clickToEditIconOfPayGrade(payGradeName);
		
		log.info("TC05 - Step 03: Edit Pay Grade Name");
		payGradeName = payGradesPage.generatePayGradeName();
		payGradesPage.inputToPayGradeNameTextbox(payGradeName);
		
		log.info("TC05 - Step 04: Click Save button");
		payGradesPage.clickToPayGradeSaveButton();
		
		log.info("TC05 - Step 05: Verify success message displayed");
		payGradesPage.openPayGradePage(url);
		
		log.info("TC05 - Step 06: Verify pay grade is updated in list");
		assertFalse(payGradesPage.checkPayGradesUndisplayedInTheList(payGradeName));
		
	}
	
	@Test
	public void TC_06_Verify_Delete_Pay_Grade() {
		log.info("TC06 - Step 01: Open Pay Grades page");
		payGradesPage.openPayGradePage(url);
		
		log.info("TC06 - Step 02: Click to Delete icon");
		payGradesPage.clickToDeleteIconOfPayGrade(payGradeName);
		
		log.info("TC06 - Step 03: Verify confirmation popup is displayed");
		assertEquals(payGradesPage.getConfirmationPopupMessage(), GlobalConstants.DELETE_CONFIRM_MESSAGE);
		
		log.info("TC06 - Step 04: Click to No, Cancel");
		payGradesPage.clickToNoButton();
		
		log.info("TC06 - Step 05: Verify job title is not deleted");
		assertFalse(payGradesPage.checkPayGradesUndisplayedInTheList(payGradeName));
		
		log.info("TC06 - Step 06: Click to Delete button");
		payGradesPage.clickToDeleteIconOfPayGrade(payGradeName);
		
		log.info("TC06 - Step 07: Click to Yes, Delete button");
		payGradesPage.clickToYesButton();
		
		log.info("TC06 - Step 08: Verify Successful popup is displayed");
		assertEquals(payGradesPage.getSuccessMessage(), "Successfully Deleted");
		
		log.info("TC06 - Step 09: Verify job title is deleted");
		assertTrue(payGradesPage.checkPayGradesUndisplayedInTheList(payGradeName));
		
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}

}
