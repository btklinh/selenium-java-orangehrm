package pageUIs;

public class PayGradesPageUI {
	public static final String GRADE_ADD_BUTTON = "//button[contains(@class,'oxd-button')]";
	public static final String GRADE_NAME_TEXTBOX = "//*[text()='Name']/parent::div/following-sibling::div/input";
	public static final String GRADE_SAVE_BUTTON = "//button[@type='submit']";
	public static final String CURRENCY_ADD_BUTTON = "//*[text()='Currencies']/following-sibling::button";
	public static final String CURRENCY_EDIT_BUTTON = "//div[text()='%s']/parent::div/following-sibling::div[3]//i[contains(@class,'bi-pencil')]";
	public static final String SUCCESS_MESSAGE_POPUP = "//p[contains(@class,'oxd-text--toast-message')]";
	public static final String PAY_GRADES_LIST = "//div[@role='row']/child::div[2]/div";
	public static final String DYNAMIC_DELETE_BUTTON_BY_GRADE = "//div[text()='Grade 2']/parent::div/following-sibling::div[2]//i[contains(@class,'bi-trash')]";
	public static final String DYNAMIC_EDIT_BUTTON_BY_GRADE = "//div[text()='%s']/parent::div/following-sibling::div[2]//i[contains(@class,'bi-pencil')]";
	public static final String CURRENCIES_TITLE = "//*[text()='Currencies']";
	public static final String ARROW_ICON = "//i[contains(@class,'oxd-select-text--arrow')]";
	public static final String DYNAMIC_SELECT_DROPDOWN_VALUE = "//div[@role='option']/span[contains(text(),'%s')]";
	public static final String SELECT = "//div[@class='oxd-select-text oxd-select-text--active']";
	public static final String SELECT2 = "//div[@class='oxd-select-text oxd-select-text--focus']";
	public static final String HIDDEN_DROPDOWN_OPTION = "//div[@role='listbox']/descendant::*";
	public static final String MINIMUM_SALARY_TEXTBOX = "//*[text()='Minimum Salary']/parent::div/following-sibling::div/input";
	public static final String MAXIMUM_SALARY_TEXTBOX = "//*[text()='Maximum Salary']/parent::div/following-sibling::div/input";
	public static final String CURRENCY_SAVE_BUTTON = "//h6[text()='%s']/parent::div//button[@type='submit']";
	public static final String CURRENCY_SAVE_EDIT_BUTTON = "//h6[text()='Edit Currency']/parent::div//button[@type='submit']";
	public static final String SELECTED_CURRENCY_VALUE = "//div[@class='oxd-select-text-input']";
	public static final String CURRENCY_ROW_LABEL = "//div[@role='cell'][2]/div";
	public static final String MINIMUM_SALARY_ROW_LABEL = "//div[text()='%s']/parent::div/following-sibling::div[1]/div";
	public static final String MAXIMUM_SALARY_ROW_LABEL = "//div[text()='%s']/parent::div/following-sibling::div[2]/div";
	public static final String DYNAMIC_CURRENCY_LABEL = "//h6[text()='%s']";
	
}
