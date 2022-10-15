package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.PimPageUI;
import pageUIs.WorkShiftsPageUI;

public class PimPageObject extends AbstractPage {
	WebDriver driver;

	public PimPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		waitToElementVisible(driver, PimPageUI.PAGE_TITLE_LABEL);
		return getElementText(driver, PimPageUI.PAGE_TITLE_LABEL);
	}

	public void clickAddButton() {
		waitToElementClickable(driver, PimPageUI.ADD_BUTTON);
		clickToElement(driver, PimPageUI.ADD_BUTTON);
	}


}
