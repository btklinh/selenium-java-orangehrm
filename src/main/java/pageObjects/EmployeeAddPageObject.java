package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.EmployeeAddPageUI;

public class EmployeeAddPageObject extends AbstractPage {
	WebDriver driver;

	public EmployeeAddPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void setFName(String fname) {
		waitToElementVisible(driver, EmployeeAddPageUI.FNAME_TEXTBOX);
		sendKeysToElement(driver, EmployeeAddPageUI.FNAME_TEXTBOX, fname);
	}

	public void setMName(String mname) {
		waitToElementVisible(driver, EmployeeAddPageUI.MNAME_TEXTBOX);
		sendKeysToElement(driver, EmployeeAddPageUI.MNAME_TEXTBOX, mname);
	}

	public void setLName(String lname) {
		waitToElementVisible(driver, EmployeeAddPageUI.LNAME_TEXTBOX);
		sendKeysToElement(driver, EmployeeAddPageUI.LNAME_TEXTBOX, lname);
	}

	public void clickSave() {
		sleepInSecond(5);
		waitToElementClickable(driver, EmployeeAddPageUI.SAVE_BUTTON);
		clickToElement(driver, EmployeeAddPageUI.SAVE_BUTTON);
	}

	public String getMessage() {
		waitToElementVisible(driver, EmployeeAddPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, EmployeeAddPageUI.SUCCESS_MESSAGE_POPUP);
	}

	public String getEmployeeID() {
		waitToElementVisible(driver, EmployeeAddPageUI.EMPLOYEE_ID_TEXTBOX);
		return getTextOnTextboxByJS(driver, EmployeeAddPageUI.EMPLOYEE_ID_TEXTBOX);
	}

}
