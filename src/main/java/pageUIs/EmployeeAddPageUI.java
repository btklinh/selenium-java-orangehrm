package pageUIs;

public class EmployeeAddPageUI {

	public static final String EMPLOYEE_ID_TEXTBOX = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
	public static final String FNAME_TEXTBOX = "//input[@name='firstName']";
	public static final String MNAME_TEXTBOX = "//input[@name='middleName']";
	public static final String LNAME_TEXTBOX = "//input[@name='lastName']";
	public static final String SAVE_BUTTON = "//button[@type='submit']";
	public static final String SUCCESS_MESSAGE_POPUP = "//p[contains(@class,'oxd-text--toast-message')]";

}
