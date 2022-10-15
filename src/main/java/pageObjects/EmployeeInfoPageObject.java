package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.EmployeeInfoPageUI;
import pageUIs.PayGradesPageUI;

public class EmployeeInfoPageObject extends AbstractPage {
	WebDriver driver;

	public EmployeeInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getMessage() {
		waitToElementVisible(driver, EmployeeInfoPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, EmployeeInfoPageUI.SUCCESS_MESSAGE_POPUP);
	}

	public String getFName() {
		// waitToElementVisible(driver, EmployeeInfoPageUI.FNAME_TEXTBOX);
		sleepInSecond(3);
		return getTextOnTextboxByJS(driver, EmployeeInfoPageUI.FNAME_TEXTBOX);
	}

	public String getMName() {
		// waitToElementVisible(driver, EmployeeInfoPageUI.MNAME_TEXTBOX);
		return getTextOnTextboxByJS(driver, EmployeeInfoPageUI.MNAME_TEXTBOX);
	}

	public String getLName() {
		// waitToElementVisible(driver, EmployeeInfoPageUI.LNAME_TEXTBOX);
		return getTextOnTextboxByJS(driver, EmployeeInfoPageUI.LNAME_TEXTBOX);
	}

	public String getEmployeeID() {
		waitToElementVisible(driver, EmployeeInfoPageUI.EMPLOYEE_ID_TEXTBOX);
		return getTextOnTextboxByJS(driver, EmployeeInfoPageUI.EMPLOYEE_ID_TEXTBOX);
	}

	public void checkPersonalPage() {
		waitToElementVisible(driver, EmployeeInfoPageUI.PERSONAL_DETAILS_TITLE);
	}

	public void setNickName(String nickName) {
		waitToElementVisible(driver, EmployeeInfoPageUI.NICKNAME_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.NICKNAME_TEXTBOX, nickName);
	}

	public void setOtherID(String otherID) {
		waitToElementVisible(driver, EmployeeInfoPageUI.OTHER_ID_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.OTHER_ID_TEXTBOX, otherID);
	}

	public void setDriverLicense(String driverLicense) {
		waitToElementVisible(driver, EmployeeInfoPageUI.DRIVER_LICENSE_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.DRIVER_LICENSE_TEXTBOX, driverLicense);
	}

	public void setLicenseExpireDate(String expireDate) {
		waitToElementVisible(driver, EmployeeInfoPageUI.LICENSE_EXPIRE_DATE_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.LICENSE_EXPIRE_DATE_TEXTBOX, expireDate);
	}

	public void setSsnNumber(String ssnNumber) {
		waitToElementVisible(driver, EmployeeInfoPageUI.SSN_NUMBER_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.SSN_NUMBER_TEXTBOX, ssnNumber);
	}

	public void setSinNumber(String sinNumber) {
		waitToElementVisible(driver, EmployeeInfoPageUI.SIN_NUMBER_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.SIN_NUMBER_TEXTBOX, sinNumber);
	}

	public void clickToNationalityArrow() {
		waitToElementClickable(driver, EmployeeInfoPageUI.NATIONALITY_ARROW_ICON);
		clickToElement(driver, EmployeeInfoPageUI.NATIONALITY_ARROW_ICON);
		sleepInSecond(1);
	}

	public void setDropdownValue(String string) {
		waitToElementVisible(driver, EmployeeInfoPageUI.DYNAMIC_SELECT_DROPDOWN_VALUE, string);
		clickToElement(driver, EmployeeInfoPageUI.DYNAMIC_SELECT_DROPDOWN_VALUE, string);
	}

	public void clickToMaritalArrow() {
		waitToElementClickable(driver, EmployeeInfoPageUI.MARITAL_ARROW_ICON);
		clickToElement(driver, EmployeeInfoPageUI.MARITAL_ARROW_ICON);
		sleepInSecond(1);
	}

	public void setDateOfBirth(String date) {
		waitToElementVisible(driver, EmployeeInfoPageUI.DATE_OF_BIRTH_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.DATE_OF_BIRTH_TEXTBOX, date);
	}

	public void selectGender(String gender) {
		waitToElementClickable(driver, EmployeeInfoPageUI.GENDER_RADIO_BUTTON, gender);
		checkToCheckbox(driver, EmployeeInfoPageUI.SMOKER_CHECKBOX,gender);
	}
	
	public void setMilitary(String value) {
		waitToElementVisible(driver, EmployeeInfoPageUI.MILITARY_SERVICE_TEXTBOX);
		sendKeysToElement(driver, EmployeeInfoPageUI.MILITARY_SERVICE_TEXTBOX, value);
	}

	public void setSmoker(String value) {
		waitToElementClickable(driver, EmployeeInfoPageUI.SMOKER_CHECKBOX);
	    

		if (value.contentEquals("Yes")) 
			checkToCheckbox(driver, EmployeeInfoPageUI.SMOKER_CHECKBOX);
		else
			uncheckToCheckbox(driver, EmployeeInfoPageUI.SMOKER_CHECKBOX);
	}

	public void clickSavePD() {
		waitToElementClickable(driver, EmployeeInfoPageUI.SAVEPD_BUTTON);
		clickToElement(driver, EmployeeInfoPageUI.SAVEPD_BUTTON);
	}


}
