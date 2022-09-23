package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.PimPageUI;

public class PimPageObject extends AbstractPage {
	WebDriver driver;

	public PimPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		waitToElementVisible(driver, PimPageUI.PAGE_TITLE_LABEL);
		return getElementText(driver, PimPageUI.PAGE_TITLE_LABEL);
	}

}
