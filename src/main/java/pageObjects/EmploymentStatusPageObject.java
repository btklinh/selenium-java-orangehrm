package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.PimPageUI;

public class EmploymentStatusPageObject extends AbstractPage {
	WebDriver driver;

	public EmploymentStatusPageObject(WebDriver driver) {
		this.driver = driver;
	}


}
