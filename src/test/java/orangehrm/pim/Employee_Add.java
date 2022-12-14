package orangehrm.pim;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.EmployeeAddPageObject;
import pageObjects.EmployeeInfoPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class Employee_Add extends AbstractTest {

	PimPageObject pimPage;
	LoginPageObject loginPage;
	UserPageObject userPage;
	DataHelper data = DataHelper.getData();
	String fName, mName, lName, employeeId, confirmMessage, url;
	String nickName, otherID, driverLicense, licenseExpireDate, ssnNum, marialStatus, dob, sinNum, gender, militarySer, smoker, bloodType, nationality;
	EmployeeInfoPageObject employeeInfoPage;
	EmployeeAddPageObject employeeAddPage;

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
		// url = pimPage.getCurrentPageUrl(driver);

		fName = data.getFirstName();
		mName = data.getMiddleName();
		lName = data.getLastName();
		nickName = "Lynn";
		otherID = "NV001";
		driverLicense = "009AS91739";
		licenseExpireDate = "2025-10-12";
		nationality = "American";
		ssnNum = "01234546789";
		sinNum = "215232423";
		marialStatus = "Married";
		dob = "1979-08-23";
		gender = "Male";
		militarySer = "Yes";
		smoker = "No";
		bloodType = "B+";

	}

	@Test
	public void TC_01_Verify_Add_Employee() {
		log.info("TC01 - Step 01: Click Add Employee");
		pimPage.clickAddButton();
		employeeAddPage = PageGeneratorManager.getEmployeeAddPage(driver);

		log.info("TC01 - Step 02: Input valid info of employee ");
		employeeId = employeeAddPage.getEmployeeID();
		employeeAddPage.setFName(fName);
		employeeAddPage.setMName(mName);
		employeeAddPage.setLName(lName);

		log.info("TC01 - Step 03: Click Save button");
		employeeAddPage.clickSave();

		log.info("TC01 - Step 04: Verify success message is displayed");
		assertEquals(employeeAddPage.getMessage(), "Successfully Saved");
		employeeInfoPage = PageGeneratorManager.getEmployeeInfoPage(driver);
		employeeInfoPage.checkPersonalPage();

		log.info("TC01 - Step 05: Verify that employee information screen is displayed with correct info");
		assertEquals(employeeInfoPage.getFName(), fName);
		assertEquals(employeeInfoPage.getMName(), mName);
		assertEquals(employeeInfoPage.getLName(), lName);
		assertEquals(employeeInfoPage.getEmployeeID(), employeeId);
	}

	@Test
	public void TC_02_Verify_Edit_Personal_Details() {
//		// employeeInfoPage = PageGeneratorManager.getEmployeeInfoPage(driver);
//		pimPage.openUrl(driver, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/10");
//		employeeInfoPage = PageGeneratorManager.getEmployeeInfoPage(driver);
//
//		employeeInfoPage.sleepInSecond(4);

		log.info("TC02 - Step 01: Set NickName");
		employeeInfoPage.setNickName(nickName);

		log.info("TC02 - Step 02: Set Other ID");
		employeeInfoPage.setOtherID(otherID);

		log.info("TC02 - Step 03: Set Driver's License Number");
		employeeInfoPage.setDriverLicense(driverLicense);

		log.info("TC02 - Step 04: Set License Expiry Date");
		employeeInfoPage.setLicenseExpireDate(licenseExpireDate);

		log.info("TC02 - Step 05: Set SSN Number");
		employeeInfoPage.setSsnNumber(ssnNum);

		log.info("TC02 - Step 06: Set SIN Number");
		employeeInfoPage.setSinNumber(sinNum);

		log.info("TC02 - Step 07: Set Nationality");
		employeeInfoPage.clickToNationalityArrow();
		employeeInfoPage.setDropdownValue(nationality);

		log.info("TC02 - Step 08: Set Marital Status");
		employeeInfoPage.clickToMaritalArrow();
		employeeInfoPage.setDropdownValue(marialStatus);

		log.info("TC02 - Step 09: Set Date of Birth");
		employeeInfoPage.setDateOfBirth(dob);

		log.info("TC02 - Step 10: Set Gender");
		employeeInfoPage.selectGender(gender);

		log.info("TC02 - Step 11: Set Military Service");
		employeeInfoPage.setMilitary(militarySer);

		log.info("TC02 - Step 12: Set Smoker");
		employeeInfoPage.setSmoker(smoker);

		log.info("TC02 - Step 13: Click Save");
		employeeInfoPage.clickSavePD();

		log.info("TC02 - Step 14: Verify message success displayed");
		assertEquals(employeeInfoPage.getMessage(), "Successfully Updated");

		log.info("TC02 - Step 15: Set Blood Type");
		employeeInfoPage.clickToBloodTypeArror();
		employeeInfoPage.setDropdownValue(bloodType);

		log.info("TC02 - Step 16: Click Save");
		employeeInfoPage.clickSaveCustomFields();

		log.info("TC02 - Step 17: Verify message success displayed");
		assertEquals(employeeInfoPage.getMessage(), "Successfully Saved");
	}

	@AfterClass
	public void afterClass() {
		 closeBrowser(driver);
	}

}
