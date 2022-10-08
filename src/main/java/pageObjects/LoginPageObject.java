package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUsernameTextbox(String username) {
		waitToElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}


	public PimPageObject clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		PimPageObject pimPage = new PimPageObject(driver);
		return pimPage;
	}

}
