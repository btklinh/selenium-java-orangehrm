package pageUIs;

public class WorkShiftsPageUI {

	public static final String ADD_BUTTON = "//button[text()[contains(.,'Add')]]";
	public static final String SHIFT_NAME_TEXTBOX = "//*[text()='Shift Name']/parent::div/following-sibling::div/input";
	public static final String WORKING_HOURS_FROM_TEXTBOX = "//*[text()='From']/parent::div/following-sibling::div//input";
	public static final String WORKING_HOURS_TO_TEXTBOX = "//*[text()='To']/parent::div/following-sibling::div//input";
	public static final String ASSIGNED_EMPLOYEES_TEXTBOX = "//*[text()='Assigned Employees']/parent::div/following-sibling::div//input";
	public static final String SAVE_BUTTON = "//button[text()[contains(.,'Save')]]";
	public static final String DURATION_PER_DAY_TEXT = "//p[contains(@class,'orangehrm-workshift-duration')]";


}
