package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.JobTitlesPageUI;
import pageUIs.LocationsPageUI;
import pageUIs.PimPageUI;

public class LocationsPageObject extends AbstractPage {
	WebDriver driver;
	boolean result;

	public LocationsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToSearchNameTextbox(String value) {
		waitToElementVisible(driver, LocationsPageUI.SEARCH_NAME_TEXTBOX);
		sendKeysToElement(driver, LocationsPageUI.SEARCH_NAME_TEXTBOX, value);
	}

	public void clickSearchButton() {
		waitToElementClickable(driver, LocationsPageUI.SEARCH_BUTTON);
		clickToElement(driver, LocationsPageUI.SEARCH_BUTTON);
		sleepInSecond(1);
	}
	
	public boolean checkSearchByNameResult(String keyword) {
		waitToElementVisible(driver, LocationsPageUI.LOCATIONS_NAME_LIST);
		List<String> locationsNameList = new ArrayList<>();
		locationsNameList = getElementsText(driver, LocationsPageUI.LOCATIONS_NAME_LIST);
		for (String location : locationsNameList) {
			
			//Change location and keyword to lower case
			String formatLocation = changeStringToLowerCase(location);
			String formatKeyword = changeStringToLowerCase(keyword);
			
			//If location contains keyword, return true and continute for loop
			if (formatLocation.contains(formatKeyword)==true) {
				result = true;
				continue;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public int getLocationsListSize() {
		waitToElementVisible(driver, LocationsPageUI.LOCATIONS_NAME_LIST);
		List<String> locationsNameList = getElementsText(driver, LocationsPageUI.LOCATIONS_NAME_LIST);
		return locationsNameList.size();
	}
	
	public int getRecordsNumber() {
		waitToElementVisible(driver, LocationsPageUI.RECORDS_COUNT_LABEL);
		String label = getElementText(driver, LocationsPageUI.RECORDS_COUNT_LABEL);
		
		//Remove all String characters
		String recordNumber = label.replace("Records Found", "").replace("(", "").replace(")", "").trim();
		return Integer.parseInt(recordNumber);
	}
	
	public String changeStringToLowerCase(String s) {
		System.out.println(s.toLowerCase());
		return s.toLowerCase();
	}
	


}
