package pageObjects;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.JobTitlesPageUI;
import pageUIs.WorkShiftsPageUI;

public class WorkShiftsPageObject extends AbstractPage {
	WebDriver driver;
	boolean result;

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

	public int convertToHour(String time) {
		return Integer.parseInt(time.substring(0, 2));
	}

	public int convertToMinute(String time) {
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

	public void typeToAssignedEmployees(String employee) {
		waitToElementVisible(driver, WorkShiftsPageUI.ASSIGNED_EMPLOYEES_TEXTBOX);
		sendKeysToElement(driver, WorkShiftsPageUI.ASSIGNED_EMPLOYEES_TEXTBOX, employee);
		sleepInSecond(2);
	}

	public void selectAssignedEmployees() {
		waitToElementVisible(driver, WorkShiftsPageUI.EMPLOYEES_SEARCH_RESULT);
		clickToElement(driver, WorkShiftsPageUI.EMPLOYEES_SEARCH_RESULT);

	}

	public void getHtmlSourceOfElement() {
		waitToElementVisible(driver, WorkShiftsPageUI.EMPLOYEES_SEARCH_RESULT);
		List<WebElement> html = findElements(driver, WorkShiftsPageUI.EMPLOYEES_SEARCH_RESULT);
		List<String> htmlText = new ArrayList<>();
		for (WebElement s : html) {
			System.out.println(s.getAttribute("innerHTML"));
			htmlText.add(s.getAttribute("innerHTML"));
		}
	}

	public boolean checkWorkShiftsInTheList(String workShift) {
		waitToElementVisible(driver, WorkShiftsPageUI.WORK_SHIFTS_LIST);
		List<String> workShiftList = new ArrayList<>();
		workShiftList = getElementsText(driver, WorkShiftsPageUI.WORK_SHIFTS_LIST);
		for (String shift : workShiftList) {
			if (shift.equals(workShift)) {
				result = true;
				break;
			} else {
				result = false;
				continue;
			}
		}
		return result;
	}

	public String getWorkingTimeFrom(String shiftName) {
		waitToElementVisible(driver, WorkShiftsPageUI.FROM_ROW, shiftName);
		return getElementText(driver, WorkShiftsPageUI.FROM_ROW, shiftName);
	}

	public String getWorkingTimeTo(String shiftName) {
		waitToElementVisible(driver, WorkShiftsPageUI.TO_ROW, shiftName);
		return getElementText(driver, WorkShiftsPageUI.TO_ROW, shiftName);
	}

	public String getHoursPerDay(String shiftName) {
		waitToElementVisible(driver, WorkShiftsPageUI.HOURS_PER_DAY_ROW, shiftName);
		return getElementText(driver, WorkShiftsPageUI.HOURS_PER_DAY_ROW, shiftName);
	}

	public String formatTime(String time) {
		String convertTime;
		if (convertToSuffix(time).equals("AM")) {
			convertTime = time.substring(0, 5);
		} else {
			int hour = convertToHour(time) + 12;
			int minute = convertToMinute(time);
			convertTime = String.valueOf(hour) + ":" + String.valueOf(minute);
		}
		return convertTime;
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, WorkShiftsPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, WorkShiftsPageUI.SUCCESS_MESSAGE_POPUP);
	}
}
