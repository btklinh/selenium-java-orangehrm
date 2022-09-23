package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;

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
}