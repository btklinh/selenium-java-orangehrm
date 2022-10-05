package orangehrm.admin.job;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import pageObjects.WorkShiftsPageObject;
import utilities.DataHelper;

public class WorkShift_Operation extends AbstractTest {
	PimPageObject pimPage;
	LoginPageObject loginPage;
	WorkShiftsPageObject workShiftsPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String url, shiftName, workingHourFrom, workingHourTo;
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
		userPage.clickToDropdownMenuByName(driver, "Work Shifts");
		workShiftsPage = PageGeneratorManager.getWorkShiftsPage(driver);
		url = workShiftsPage.getCurrentUrl();
	}

	@Test
	public void TC_01_Verify_Add_Work_Shift() {
		shiftName = workShiftsPage.generateShiftName();
		workingHourFrom = workShiftsPage.convert24HTo12H(data.fromTime());
		workingHourTo = workShiftsPage.convert24HTo12H(data.toTime(workingHourFrom));

		log.info("TC01 - Step 01: Click Add button");
		workShiftsPage.clickToAddButton();

		log.info("TC01 - Step 02: Input valid Shift Name");
		workShiftsPage.inputToShiftNameTextbox(shiftName);

		log.info("TC01 - Step 03: Select valid working hours");
		workShiftsPage.selectWorkingHoursFrom(workingHourFrom);
		workShiftsPage.selectWorkingHoursTo(workingHourTo);

		log.info("TC01 - Step 04: Select Assigned Employees");
		workShiftsPage.typeToAssignedEmployees("linh");
		workShiftsPage.selectAssignedEmployees();

		log.info("TC01 - Step 05: Verify whether Duration Per Day display correctly");
		String actual = workShiftsPage.getDurationPerDayText();
		String expected = workShiftsPage.calDurationPerDay(workingHourFrom, workingHourTo);
		assertEquals(actual, expected);

		log.info("TC01 - Step 06: Click to Save button");
		workShiftsPage.clickToSaveButton();

		log.info("TC01 - Step 07: Verify success message displayed");
		assertEquals(workShiftsPage.getSuccessMessage(), "Successfully Saved");

		log.info("TC01 - Step 08: Verify the Work Shift in the list");
		assertTrue(workShiftsPage.checkWorkShiftsInTheList(shiftName));
		assertEquals(workShiftsPage.getWorkingTimeFrom(shiftName), workShiftsPage.convert12HTo24H(workingHourFrom));
		assertEquals(workShiftsPage.getWorkingTimeTo(shiftName), workShiftsPage.convert12HTo24H(workingHourTo));

		assertEquals(workShiftsPage.getHoursPerDay(shiftName), expected);
	}

}
