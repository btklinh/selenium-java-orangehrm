package pageObjects;

import java.text.DecimalFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.WorkShiftsPageUI;

public class WorkShiftsPageObject extends AbstractPage {
	WebDriver driver;

	public WorkShiftsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String generateShiftName() {
		long ID = new Date().getTime();
		String prefix = "Shift ";
		String shiftName = prefix + String.valueOf(ID);
		return shiftName;
	}

	public String getCurrentUrl() {
		return getCurrentPageUrl(driver);
	}

	public void clickToAddButton() {
		waitToElementClickable(driver, WorkShiftsPageUI.ADD_BUTTON);
		clickToElement(driver, WorkShiftsPageUI.ADD_BUTTON);
	}

	public void inputToShiftNameTextbox(String string) {
		waitToElementVisible(driver, WorkShiftsPageUI.SHIFT_NAME_TEXTBOX);
		sendKeysToElement(driver, WorkShiftsPageUI.SHIFT_NAME_TEXTBOX, string);
	}

	public void selectWorkingHoursFrom(String time) {
		waitToElementVisible(driver, WorkShiftsPageUI.WORKING_HOURS_FROM_TEXTBOX);
		sendKeysToElement(driver, WorkShiftsPageUI.WORKING_HOURS_FROM_TEXTBOX, time);
	}

	public void selectWorkingHoursTo(String time) {
		waitToElementVisible(driver, WorkShiftsPageUI.WORKING_HOURS_TO_TEXTBOX);
		sendKeysToElement(driver, WorkShiftsPageUI.WORKING_HOURS_TO_TEXTBOX, time);
	}

	public void clickToSaveButton() {
		waitToElementClickable(driver, WorkShiftsPageUI.SAVE_BUTTON);
		clickToElement(driver, WorkShiftsPageUI.SAVE_BUTTON);
	}

	public String getDurationPerDayText() {
		waitToElementVisible(driver, WorkShiftsPageUI.WORKING_HOURS_TO_TEXTBOX);
		return getElementText(driver, WorkShiftsPageUI.DURATION_PER_DAY_TEXT);
	}

	public double convertToHour(String time) {
		return Integer.parseInt(time.substring(0, 2));
	}

	public double convertToMinute(String time) {
		return Integer.parseInt(time.substring(3, 5));
	}

	public String convertToSuffix(String time) {
		return time.substring(6, 8);
	}

	public String calDurationPerDay(String workingHourFrom, String workingHourTo) {
		double fromHh, toHh, fromMm, toMm, duration;
		fromHh = convertToHour(workingHourFrom);
		toHh = convertToHour(workingHourTo);
		fromMm = convertToMinute(workingHourFrom);
		toMm = convertToMinute(workingHourTo);
		if (convertToSuffix(workingHourFrom).equals("PM")) {
			fromHh += 12;
		}
		if (convertToSuffix(workingHourTo).equals("PM")) {
			toHh += 12;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		duration = (toHh + (toMm / 60)) - (fromHh + (fromMm / 60));
		return String.valueOf(df.format(duration));
	}

	public void clickToAssignedEmployees() {
		waitToElementClickable(driver, WorkShiftsPageUI.ASSIGNED_EMPLOYEES_TEXTBOX);
		clickToElement(driver, WorkShiftsPageUI.ASSIGNED_EMPLOYEES_TEXTBOX);
	}

}
