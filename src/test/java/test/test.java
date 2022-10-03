package test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;
import pageUIs.PayGradesPageUI;

public class test extends AbstractTest {
	String username, password;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	PayGradesPageObject payGradesPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
WebDriver driver;
	
	public static String formatText(String s) {
		return s.toLowerCase();
		
	}
	public static double convertToHour(String time) {
		return Integer.parseInt(time.substring(0, 2));
	}
	
	public static double convertToMinute(String time) {
		return Integer.parseInt(time.substring(3, 5));
	}
	
	public static String convertToSuffix(String time) {
		return time.substring(6,8);
	}

	public static String durationPerDayCal(String workingHourFrom, String workingHourTo) {
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
		duration = (toHh + (toMm/60)) - (fromHh + (fromMm/60));
		System.out.println(df.format(duration));
		return String.valueOf(df.format(duration));
	}

	public static void main(String args[]) {
		System.out.println(convertToHour("03:12 PM"));
		System.out.println(convertToMinute("10:12 AM"));
		System.out.println(convertToSuffix("10:12 AM"));
		durationPerDayCal("03:12 PM", "11:42 PM");
	}
}
