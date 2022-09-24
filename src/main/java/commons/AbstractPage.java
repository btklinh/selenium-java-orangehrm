package commons;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.AbstractPageUI;

public abstract class AbstractPage {
	private WebDriverWait explicitWait;
	private long SHORT_TIMEOUT = 5;
	private Actions action;
	private JavascriptExecutor jsExecutor;

	protected WebElement findElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}

	protected List<WebElement> findElements(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String value) {
		findElement(driver, locator).clear();
		findElement(driver, locator).sendKeys(value);
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String value, String... values) {
		findElement(driver, castToRestParameter(locator, values)).clear();
		findElement(driver, castToRestParameter(locator, values)).sendKeys(value);
	}

	protected String getElementText(WebDriver driver, String locator) {
		return findElement(driver, locator).getText();
	}

	protected List<String> getElementsText(WebDriver driver, String locator) {
		List<WebElement> elements = findElements(driver, locator);
		List<String> elementsText = new ArrayList<String>();
		for (WebElement e : elements) {
			elementsText.add(e.getText());
		}
		return elementsText;
	}

	protected String getElementText(WebDriver driver, String locator, String... values) {
		return findElement(driver, castToRestParameter(locator, values)).getText();
	}

	protected void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	protected void waitToElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(castToRestParameter(locator, values))));
	}

	protected void waitToElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, SHORT_TIMEOUT);

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	protected void waitToElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(castToRestParameter(locator, values))));
	}

	protected void clickToElement(WebDriver driver, String locator) {
		findElement(driver, locator).click();
	}

	protected void clickToElement(WebDriver driver, String locator, String... values) {
		findElement(driver, castToRestParameter(locator, values)).click();
	}

	protected void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElement(driver, locator), key).perform();
	}

	protected void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElement(driver, castToRestParameter(locator, values)), key).perform();
	}

	protected String castToRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	protected String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	protected void selectItemInDropdownByValue(WebDriver driver, String locator, String value) {
		Select select = new Select(findElement(driver, locator));
		select.selectByValue(value);
	}

	protected void selectItemInDropdownByVisibleText(WebDriver driver, String locator, String text) {
		Select select = new Select(findElement(driver, locator));
		select.selectByVisibleText(text);
	}

	protected void selectItemInDropdownByIndex(WebDriver driver, String locator, int index) {
		Select select = new Select(findElement(driver, locator));
		select.selectByIndex(index);
	}

//	public AbstractPage openMenuPageByName(WebDriver driver, String pageName) {
//		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
//		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
//		if (pageName.equals("New Customer")) {
//			return PageGeneratorManager.getNewCustomerPage(driver);
//		} else if (pageName.equals("Edit Customer")) {
//			return PageGeneratorManager.getEditCustomerPage(driver);
//		} else if (pageName.equals("New Account")) {
//			return PageGeneratorManager.getNewAccountPage(driver);
//		} else {
//			throw new RuntimeException();
//		}
//	}

//	public void openPageContainsSearchFunc(WebDriver driver, String pageName) {
//		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_CONTAIN_SEARCH, pageName);
//		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_CONTAIN_SEARCH, pageName);
//	}
//	
//	public void openPageContainsConfigure(WebDriver driver, String pageName) {
//		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_CONTAIN_CONFIGURE, pageName);
//		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_CONTAIN_CONFIGURE, pageName);
//	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElement(driver, locator));
	}

	public void clickToLeftMenuByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LEFT_MENU, pageName);
	}

	public void clickToParentMenuByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_PARENT_MENU_BY_NAME, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PARENT_MENU_BY_NAME, pageName);
	}

	public void clickToDropdownMenuByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_DROPDOWN_MENU_BY_NAME, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_DROPDOWN_MENU_BY_NAME, pageName);
	}

	public String getPageTitleContainSearch(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.PAGE_TITLE_CONTAIN_SEARCH);
		return getElementText(driver, AbstractPageUI.PAGE_TITLE_CONTAIN_SEARCH);
	}

	public String getPageTitleContainConfigure(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.PAGE_TITLE_CONTAIN_CONFIGURE);
		return getElementText(driver, AbstractPageUI.PAGE_TITLE_CONTAIN_CONFIGURE);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElement(driver, locator));
	}
}
