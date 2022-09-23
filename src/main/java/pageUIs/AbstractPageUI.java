package pageUIs;

public class AbstractPageUI {
	
	public static final String DYNAMIC_LEFT_MENU = "//span[text()='%s']/parent::a";
	public static final String DYNAMIC_PARENT_MENU_BY_NAME = "//span[contains(text(),'%s')]/parent::li";
	public static final String DYNAMIC_DROPDOWN_MENU_BY_NAME = "//a[contains(text(),'%s')]";
	
	public static final String PAGE_TITLE_CONTAIN_SEARCH = "//*[contains(@class,'oxd-table-filter-title')]";
	public static final String PAGE_TITLE_CONTAIN_CONFIGURE = "//*[contains(@class, 'orangehrm-main-title')]";

}
