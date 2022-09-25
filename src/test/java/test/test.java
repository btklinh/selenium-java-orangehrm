package test;

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
	
	public static boolean checkSearchByNameResult(String keyword) {
		boolean result = true;
	
		List<String> locationsNameList = new ArrayList<>();
		locationsNameList.add("Hong Kong 1");
		locationsNameList.add("Hong Kong 2");
		//locationsNameList.add("asdc1");
		
		for (String location : locationsNameList) {
			System.out.println(location);
			String formatLocation = formatText(location);
			System.out.println(formatLocation);
			String formatKeyword = formatText(keyword);
			System.out.println(formatKeyword);
			if (formatLocation.contains(formatKeyword)) {
				result = true;
				continue;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public static int getRecordsNumber() {
		
		String label = "(212) Records Found";
		String recordNumber = label.replace("Records Found", "").replace("(", "").replace(")", "").trim();
		return Integer.parseInt(recordNumber);
	}
	

	
	public static String generatePayGradeName() {
		long ID = new Date().getTime();
		String prefix = "Grade ";
		String payGradeName = prefix + String.valueOf(ID);
		return payGradeName;
	}

	public static void main(String args[]) {
		System.out.println(generatePayGradeName());
	}
}
