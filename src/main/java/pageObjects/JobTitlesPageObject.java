package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.PimPageUI;

public class JobTitlesPageObject extends AbstractPage {
	WebDriver driver;

	public JobTitlesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		waitToElementVisible(driver, PimPageUI.PAGE_TITLE_LABEL);
		return getElementText(driver, PimPageUI.PAGE_TITLE_LABEL);
	}

}
