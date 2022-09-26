package pageObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.AbstractPageUI;
import pageUIs.JobTitlesPageUI;
import pageUIs.PayGradesPageUI;
import utilities.DataHelper;

public class PayGradesPageObject extends AbstractPage {
	WebDriver driver;
	boolean result;
	Date date;
	List<String> currencyList;
	DataHelper data = DataHelper.getData();

	public PayGradesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddButton() {
		waitToElementClickable(driver, PayGradesPageUI.GRADE_ADD_BUTTON);
		clickToElement(driver, PayGradesPageUI.GRADE_ADD_BUTTON);
		sleepInSecond(1);
	}

	public void inputToPayGradeNameTextbox(String value) {
		waitToElementVisible(driver, PayGradesPageUI.GRADE_NAME_TEXTBOX);
		sendKeysToElement(driver, PayGradesPageUI.GRADE_NAME_TEXTBOX, value);
	}

	public void clickToPayGradeSaveButton() {
		waitToElementClickable(driver, PayGradesPageUI.GRADE_SAVE_BUTTON);
		clickToElement(driver, PayGradesPageUI.GRADE_SAVE_BUTTON);
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, PayGradesPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, PayGradesPageUI.SUCCESS_MESSAGE_POPUP);
	}

	public String checkPageTitle() {
		waitToElementVisible(driver, PayGradesPageUI.CURRENCIES_TITLE);
		return getElementText(driver, AbstractPageUI.PAGE_TITLE_CONTAIN_CONFIGURE);
	}

	public boolean checkPayGradesInTheList(String payGrade) {
		waitToElementVisible(driver, PayGradesPageUI.PAY_GRADES_LIST);
		List<String> payGradesList = new ArrayList<>();
		payGradesList = getElementsText(driver, PayGradesPageUI.PAY_GRADES_LIST);
		for (String grade : payGradesList) {
			if (grade.equals(payGrade)) {
				result = true;
				break;
			} else {
				result = false;
				continue;
			}
		}
		return result;
	}

	public String generatePayGradeName() {
		long ID = new Date().getTime();
		String prefix = "Grade ";
		String payGradeName = prefix + String.valueOf(ID);
		return payGradeName;
	}

	public void clickToEditIconOfPayGrade(String payGradeName) {
		waitToElementClickable(driver, PayGradesPageUI.DYNAMIC_EDIT_BUTTON_BY_GRADE, payGradeName);
		clickToElement(driver, PayGradesPageUI.DYNAMIC_EDIT_BUTTON_BY_GRADE, payGradeName);
		sleepInSecond(2);
	}

	public void clickToAddCurrencyButton() {
		waitToElementClickable(driver, PayGradesPageUI.CURRENCY_ADD_BUTTON);
		clickToElement(driver, PayGradesPageUI.CURRENCY_ADD_BUTTON);
//		sleepInSecond(2);
	}

	public void clickToArrowIcon() {
		waitToElementClickable(driver, PayGradesPageUI.ARROW_ICON);
		clickToElement(driver, PayGradesPageUI.ARROW_ICON);
		sleepInSecond(1);
	}

	public void selectCurrency(String string) {
		waitToElementVisible(driver, PayGradesPageUI.DYNAMIC_SELECT_DROPDOWN_VALUE, string);
		clickToElement(driver, PayGradesPageUI.DYNAMIC_SELECT_DROPDOWN_VALUE, string);
	}

	public List<String> getAllDropdownValue() {
		waitToElementVisible(driver, PayGradesPageUI.HIDDEN_DROPDOWN_OPTION);
		List<WebElement> list = findElements(driver, PayGradesPageUI.HIDDEN_DROPDOWN_OPTION);
		List<String> html = new ArrayList<>();
		for (WebElement a : list) {
			html.add(a.getText());
		}
		return html;
	}

	public void inputToMinimumSalaryTextbox(String minimumSalary) {
		waitToElementVisible(driver, PayGradesPageUI.MINIMUM_SALARY_TEXTBOX);
		sendKeysToElement(driver, PayGradesPageUI.MINIMUM_SALARY_TEXTBOX, minimumSalary);
	}

	public void inputToMaximumSalaryTextbox(String maximumSalary) {
		waitToElementVisible(driver, PayGradesPageUI.MAXIMUM_SALARY_TEXTBOX);
		sendKeysToElement(driver, PayGradesPageUI.MAXIMUM_SALARY_TEXTBOX, maximumSalary);
	}

	public void clickToSaveCurrencyButton(String title) {
		waitToElementVisible(driver, PayGradesPageUI.CURRENCY_SAVE_BUTTON, title);
		clickToElement(driver, PayGradesPageUI.CURRENCY_SAVE_BUTTON, title);
		// sleepInSecond(4);
	}

	public boolean checkCurrencyInTheList(String currencyValue, String labelName) {
		waitToElementInvisible(driver, PayGradesPageUI.DYNAMIC_CURRENCY_LABEL, labelName);
		List<String> currencyList = new ArrayList<>();
		currencyList = getElementsText(driver, PayGradesPageUI.CURRENCY_ROW_LABEL);
		for (String currency : currencyList) {
			if (currency.equals(currencyValue)) {
				result = true;
				break;
			} else {
				result = false;
				continue;
			}
		}
		return result;
	}

	public String getCurrencyValue() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, PayGradesPageUI.SELECTED_CURRENCY_VALUE);
		return getElementText(driver, PayGradesPageUI.SELECTED_CURRENCY_VALUE);
	}

	public boolean checkMiniumumSalaryIntheList(String currencyName, String labelName, String minimumSalary) {
		waitToElementInvisible(driver, PayGradesPageUI.DYNAMIC_CURRENCY_LABEL, labelName);
		String salary = getElementText(driver, PayGradesPageUI.MINIMUM_SALARY_ROW_LABEL, currencyName);
		if (salary.equals(minimumSalary))
			result = true;
		else
			result = false;
		return result;
	}

	public boolean checkMaximumSalaryInTheList(String currencyName, String labelName, String maximumSalary) {
		waitToElementInvisible(driver, PayGradesPageUI.DYNAMIC_CURRENCY_LABEL, labelName);
		String salary = getElementText(driver, PayGradesPageUI.MAXIMUM_SALARY_ROW_LABEL, currencyName);
		if (salary.equals(maximumSalary))
			result = true;
		else
			result = false;
		return result;
	}

	public String getRandomCurrencyValue() {
		currencyList = getAllDropdownValue();
		return currencyList.get(data.getRandomIndex(currencyList.size() - 1));
	}
	

	public void clickToEditIconOfCurrency(String currencyValue) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, PayGradesPageUI.CURRENCY_EDIT_BUTTON, currencyValue);
		clickToElement(driver, PayGradesPageUI.CURRENCY_EDIT_BUTTON, currencyValue);
		sleepInSecond(1);
	}

	public void clickToSaveEditCurrencyButton() {
		waitToElementVisible(driver, PayGradesPageUI.CURRENCY_SAVE_EDIT_BUTTON);
		clickToElement(driver, PayGradesPageUI.CURRENCY_SAVE_EDIT_BUTTON);
	}
}
