package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.EmploymentStatusPageUI;

public class EmploymentStatusPageObject extends AbstractPage {
	WebDriver driver;

	public EmploymentStatusPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAdd() {
		waitToElementClickable(driver, EmploymentStatusPageUI.ADD_BUTTON);
		clickToElement(driver, EmploymentStatusPageUI.ADD_BUTTON);
	}

	public void inputName(String name) {
		waitToElementVisible(driver, EmploymentStatusPageUI.NAME_TEXTBOX);
		sendKeysToElement(driver, EmploymentStatusPageUI.NAME_TEXTBOX, name);
	}

	public void clickSave() {
		waitToElementClickable(driver, EmploymentStatusPageUI.SAVE_BUTTON);
		clickToElement(driver, EmploymentStatusPageUI.SAVE_BUTTON);
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, EmploymentStatusPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, EmploymentStatusPageUI.SUCCESS_MESSAGE);
	}

	public boolean statusIsAddedToList(String employmentStatusName) {
		return !isControlUndisplayed(driver, EmploymentStatusPageUI.EMPLOYMENT_STATUS_ROW, employmentStatusName);
	}

}
