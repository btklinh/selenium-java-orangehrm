package pageObjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.JobTitlesPageUI;

public class JobTitlesPageObject extends AbstractPage {
	WebDriver driver;
	boolean result;

	public JobTitlesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddButton() {
		waitToElementClickable(driver, JobTitlesPageUI.ADD_BUTTON);
		clickToElement(driver, JobTitlesPageUI.ADD_BUTTON);
	}

	public void inputToJobTitleTexbox(String jobTitle){		
		waitToElementVisible(driver, JobTitlesPageUI.JOB_TITLE_TEXTBOX);
		sendKeysToElement(driver, JobTitlesPageUI.JOB_TITLE_TEXTBOX, jobTitle);
	}

	public void inputToJobDescriptionTextbox(String jobDescription) {
		waitToElementVisible(driver, JobTitlesPageUI.JOB_DESCRIPTION_TEXTBOX);
		sendKeysToElement(driver, JobTitlesPageUI.JOB_DESCRIPTION_TEXTBOX, jobDescription);
	}

	public void inputToJobNoteTextbox(String jobNote) {
		waitToElementVisible(driver, JobTitlesPageUI.JOB_NOTE_TEXTBOX);
		sendKeysToElement(driver, JobTitlesPageUI.JOB_NOTE_TEXTBOX, jobNote);
	}

	public void clickToSaveButton() {
		waitToElementClickable(driver, JobTitlesPageUI.SAVE_BUTTON);
		clickToElement(driver, JobTitlesPageUI.SAVE_BUTTON);
	}

	public void clickToBrowseButton() {
		waitToElementClickable(driver, JobTitlesPageUI.BROWSE_BUTTON);
		clickToElement(driver, JobTitlesPageUI.BROWSE_BUTTON);
	}

	public void selectUploadFile(String directory) {
		try {
			Robot rb = new Robot();
			// Copy file path to Clipboard
			StringSelection str = new StringSelection(directory);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

			// press Contol+V for pasting
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);

			// release Contol+V for pasting
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);

			// for pressing and releasing Enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public List<String> getJobTitlesList() {
		waitToElementVisible(driver, JobTitlesPageUI.JOB_TITLES_LIST);
		return getElementsText(driver, JobTitlesPageUI.JOB_TITLES_LIST);
	}

	public boolean checkJobTitleInTheList(String jobTitle) {
		waitToElementVisible(driver, JobTitlesPageUI.JOB_TITLES_LIST);
		List<String> jobTitlesList = new ArrayList<>();
		jobTitlesList = getElementsText(driver, JobTitlesPageUI.JOB_TITLES_LIST);
		for (String job : jobTitlesList) {
			if (job.equals(jobTitle)) {
				result = true;
				break;
			} else {
				result = false;
				continue;
			}
		}
		return result;
	}

	public String getSuccessMessage() {
		waitToElementVisible(driver, JobTitlesPageUI.SUCCESS_MESSAGE_POPUP);
		return getElementText(driver, JobTitlesPageUI.SUCCESS_MESSAGE_POPUP);
	}

	public void clickToEditIconOfJobTitle(String jobTitle) {
		waitToElementClickable(driver, JobTitlesPageUI.DYNAMIC_EDIT_BUTTON_BY_TITLE, jobTitle);
		clickToElement(driver, JobTitlesPageUI.DYNAMIC_EDIT_BUTTON_BY_TITLE, jobTitle);
		sleepInSecond(3);
	}

}
