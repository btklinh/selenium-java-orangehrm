package test;

import java.util.ArrayList;
import java.util.List;

import pageUIs.LocationsPageUI;

public class test {
	
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

	public static void main(String[] args) {
		String myStr = "Hong Kong 1";
		System.out.println(getRecordsNumber());
		//System.out.println(myStr.contains("Hong")); 
	}

}
