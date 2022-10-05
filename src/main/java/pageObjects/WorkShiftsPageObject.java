package pageObjects;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
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

	public String calDurationPerDay(String workingHourFrom, String workingHourTo) {
		double fromHh, toHh, fromMm, toMm, duration;
		fromHh = convertToHour(convert12HTo24H(workingHourFrom));
		toHh = convertToHour(convert12HTo24H(workingHourTo));
		fromMm = convertToMinute(convert12HTo24H(workingHourFrom));
		toMm = convertToMinute(convert12HTo24H(workingHourTo));
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


	public String convert12HTo24H(String time) {
		// String convertTime;
		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		Date _12HourDt;
		try {
			_12HourDt = _12HourSDF.parse(time);
			return String.valueOf(_24HourSDF.format(_12HourDt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public String convert24HTo12H(String time) {
		// String convertTime;
		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		Date _24HourDt;
		try {
			_24HourDt = _24HourSDF.parse(time);
			return String.valueOf(_12HourSDF.format(_24HourDt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, WorkShiftsPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, WorkShiftsPageUI.SUCCESS_MESSAGE_POPUP);
	}
}
