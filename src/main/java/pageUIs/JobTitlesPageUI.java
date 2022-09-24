package pageUIs;

public class JobTitlesPageUI {

	public static final String ADD_BUTTON = "//button[contains(@class,'oxd-button')]";
	public static final String JOB_TITLE_TEXTBOX = "//*[text()='Job Title']/parent::div/following-sibling::div/input";
	//public static final String BROWSE_BUTTON = "//input[@class='oxd-file-input']";
	public static final String BROWSE_BUTTON = "//div[text()='Browse']";
	public static final String JOB_DESCRIPTION_TEXTBOX = "//textarea[contains(@placeholder,'description')]";
	public static final String JOB_NOTE_TEXTBOX = "//textarea[contains(@placeholder,'note')]";
	public static final String SAVE_BUTTON = "//button[@type='submit']";
	public static final String JOB_TITLES_LIST= "//div[@class='oxd-table-card']/descendant::div[6]";


}
