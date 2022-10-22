package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
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
	private List<WebElement> elements;
	private Log log;

	public void openUrl(WebDriver driver, String url) {
//		this.driver = driver;
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElement(driver, locator));
	}

	protected WebElement findElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}

	protected List<WebElement> findElements(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String value) {
		clearTextInTextbox(driver, locator);
		findElement(driver, locator).sendKeys(value);
	}

	protected void sendKeysToElement(WebDriver driver, String locator, String value, String... values) {
		clearTextInTextbox(driver, castToRestParameter(locator, values));
		findElement(driver, castToRestParameter(locator, values)).sendKeys(value);
	}

	protected String getElementText(WebDriver driver, String locator) {
		return findElement(driver, locator).getText();
	}

	protected List<String> getElementsText(WebDriver driver, String locator) {
		List<WebElement> elements = findElements(driver, locator);
		List<String> elementsText = new ArrayList<String>();
		for (WebElement e : elements) {
			elementsText.add(e.getText().trim());
		}
		return elementsText;
	}

	protected List<String> getElementsText(WebDriver driver, String locator, String... values) {
		List<WebElement> elements = findElements(driver, castToRestParameter(locator, values));
		List<String> elementsText = new ArrayList<String>();
		for (WebElement e : elements) {
			elementsText.add(e.getText().trim());
		}
		return elementsText;
	}

	protected String getElementText(WebDriver driver, String locator, String... values) {
		return findElement(driver, castToRestParameter(locator, values)).getText();
	}

	protected void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		// explicitWait.until(ExpectedConditions.)
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

	protected void clearTextInTextbox(WebDriver driver, String locator) {
		findElement(driver, locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
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

	public void sleepInSecond(long timeout) {

		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElement(driver, locator).getAttribute(attributeName);
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		return findElement(driver, locator).isDisplayed();
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}
	

	public boolean isControlUndisplayed(WebDriver driver, String locator, String... value) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElements(driver, castToRestParameter(locator, value));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		try {
			// co gia tri khi co trong dom
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.info("Waiting for element invisible with message: " + e.getMessage());
		}
	}

	public void waitToElementInvisible(WebDriver driver, String locator, String... values) {
		try {
			// co gia tri khi co trong dom
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToRestParameter(locator, values))));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.info("Waiting for element invisible with message: " + e.getMessage());
		}
	}

	public String getTextOnTextboxByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("return arguments[0].value;", findElement(driver, locator)).toString();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		if (!findElement(driver, locator).isSelected()) {
			findElement(driver, locator).click();
		}
	}

	public void checkToCheckbox(WebDriver driver, String locator, String... value) {
		if (!findElement(driver, castToRestParameter(locator, value)).isSelected()) {
			findElement(driver, castToRestParameter(locator, value)).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (findElement(driver, locator).isSelected()) {
			findElement(driver, locator).click();
		}
	}
	
//	public boolean isControlUndisplayed(WebDriver driver, String locator) {
//		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
//		elements = finds(driver, locator);
//		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
//		if (elements.size() == 0) {
//			// System.out.println("Element not in DOM");
//			log.info("Element not in DOM");
//			return true;
//		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
//			// System.out.println("Element in DOM but not visible / displayed");
//			log.info("Element in DOM but not visible / displayed");
//			return true;
//		} else {
//			// System.out.println("Element in DOM and visible");
//			log.info("Element in DOM and visible");
//			return false;
//		}
}
