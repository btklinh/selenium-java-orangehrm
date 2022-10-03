package pageUIs;

public class JobTitlesPageUI {

	public static final String ADD_BUTTON = "//button[contains(@class,'oxd-button')]";
	public static final String JOB_TITLE_TEXTBOX = "//*[text()='Job Title']/parent::div/following-sibling::div/input";
	public static final String JOB_TITLE_ERROR_TEXT = "//*[text()='Job Title']/parent::div/following-sibling::span";
	public static final String JOB_SPECIFICATION_ERROR_TEXT = "//*[text()='Job Specification']/parent::div/following-sibling::span";
	
	
	//public static final String BROWSE_BUTTON = "//input[@class='oxd-file-input']";
	public static final String BROWSE_BUTTON = "//div[text()='Browse']";
	public static final String JOB_DESCRIPTION_TEXTBOX = "//textarea[contains(@placeholder,'description')]";
	public static final String JOB_NOTE_TEXTBOX = "//textarea[contains(@placeholder,'note')]";
	public static final String SAVE_BUTTON = "//button[@type='submit']";
	public static final String JOB_TITLES_LIST= "//div[@class='oxd-table-card']/descendant::div[6]";
	public static final String SUCCESS_MESSAGE_POPUP= "//p[contains(@class,'oxd-text--toast-message')]";
	public static final String DYNAMIC_EDIT_BUTTON_BY_TITLE = "//div[text()='%s']/parent::div/following-sibling::div[2]//i[contains(@class,'bi-pencil')]";
	public static final String DYNAMIC_DELETE_BUTTON_BY_TITLE = "//div[text()='%s']/parent::div/following-sibling::div[2]//i[contains(@class,'bi-trash')]";
	public static final String REPLACE_SPECIFICATION_JOB_RADIO = "//input[@value='replaceCurrent']";
	public static final String UPLOADED_FILE_NAME_LABEL = "//p[contains(@class,'orangehrm-file-name')]";
	public static final String DELETE_SPECIFICATION_JOB_RADIO = "//input[@value='deleteCurrent']";
	public static final String DELETE_CONFIRM_MESSAGE = "//p[contains(@class,'oxd-text--card-body')]";
	public static final String NO_BUTTON = "//button[contains(@class,'oxd-button--text')]";
	public static final String YES_BUTTON = "//button[contains(@class,'oxd-button--label-danger')]";
	public static final String CANCEL_BUTTON = "//button[@type='submit']/preceding-sibling::button";


}
