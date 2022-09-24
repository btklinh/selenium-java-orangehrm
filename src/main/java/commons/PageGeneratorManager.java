package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.EmploymentStatusPageObject;
import pageObjects.JobCategoriesPageObject;
import pageObjects.JobTitlesPageObject;
import pageObjects.LocationsPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import pageObjects.WorkShiftsPageObject;

public class PageGeneratorManager {

	// return new Login Page
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static UserPageObject getUserPage(WebDriver driver) {
		return new UserPageObject(driver);
	}

	public static PimPageObject getPimPage(WebDriver driver) {
		return new PimPageObject(driver);
	}
	
	public static JobTitlesPageObject getJobTitlesPage(WebDriver driver) {
		return new JobTitlesPageObject(driver);
	}
	
	public static PayGradesPageObject getPayGradesPage(WebDriver driver) {
		return new PayGradesPageObject(driver);
	}
	
	public static EmploymentStatusPageObject getEmploymentStatusPage(WebDriver driver) {
		return new EmploymentStatusPageObject(driver);
	}
	
	public static JobCategoriesPageObject getJobCategoriesPage(WebDriver driver) {
		return new JobCategoriesPageObject(driver);
	}
	
	public static WorkShiftsPageObject getWorkShiftsPage(WebDriver driver) {
		return new WorkShiftsPageObject(driver);
	}
	public static LocationsPageObject getLocationsPage(WebDriver driver) {
		return new LocationsPageObject(driver);
	}
}