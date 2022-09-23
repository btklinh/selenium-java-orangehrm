package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.PimPageUI;

public class JobCategoriesPageObject extends AbstractPage {
	WebDriver driver;

	public JobCategoriesPageObject(WebDriver driver) {
		this.driver = driver;
	}


}
