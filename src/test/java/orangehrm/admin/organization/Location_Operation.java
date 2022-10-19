package orangehrm.admin.organization;

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
import pageObjects.LocationsPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;

public class Location_Operation extends AbstractTest {
	String username, password;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	LocationsPageObject locationPage;

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
		loginPage.inputToPasswordTextbox(GlobalConstants.LOGIN_USERNAME);

		log.info("Pre-condition - Step 03: Click Login button");
		pimPage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 04: Click To Admin on the left menu");
		pimPage.clickToLeftMenuByName(driver, "Admin");
		userPage = PageGeneratorManager.getUserPage(driver);

		log.info("Pre-condition - Step 05: Access Locations page");
		userPage.clickToParentMenuByName(driver, "Organization");
		userPage.clickToDropdownMenuByName(driver, "Locations");
		locationPage = PageGeneratorManager.getLocationsPage(driver);
	}

	@Test
	public void TC_04_Verify_Search_Existed_Location_By_Name() {
		log.info("TC04 - Step 1: Input location name that existed in Name field");
		locationPage.inputToSearchNameTextbox("Hong");

		log.info("TC04 - Step 2: Click Search button");
		locationPage.clickSearchButton();

		log.info("TC04 - Step 3: Verify the Location list displays");
		assertEquals(locationPage.checkSearchByNameResult("Hong"), true);

		log.info("TC04 - Step 4: Verify the number of Records");
		assertEquals(locationPage.getRecordsNumber(), locationPage.getLocationsListSize());
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}
}
