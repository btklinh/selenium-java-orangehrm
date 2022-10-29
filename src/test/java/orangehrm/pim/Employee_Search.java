package orangehrm.pim;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.EmployeeAddPageObject;
import pageObjects.EmployeeInfoPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import utilities.DataHelper;

public class Employee_Search extends AbstractTest{
	
	/** Cách làm search theo job title
	 * 1. Lấy dữ liệu trong database điều kiện job title = 'Design Supervisior" và sắp xếp theo điều kiện id tăng dần (ASC)
	 * 2. Tạo 7 list tương ứng với các cột id, first name, last name, job title, employement status, sub unit, supervisor và gán dữ liệu từ database lấy đc ở step 1
	 * 3. Vào web ui thực hiện search job title. cũng tạo 7 list như step 2
	 * 4. Gán dữ liệu lấy đc từ web ui vào các list
	 * 5. So sánh các list tương ứng vs nhau -> nếu giống pass. ngược lại là fail
	 * 	 */
	
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

	}

}
