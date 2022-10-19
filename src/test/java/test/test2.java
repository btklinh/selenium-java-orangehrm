package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageUIs.PayGradesPageUI;
import utilities.DataHelper;

public class test2 {

	private static Locale locale = new Locale("en");
	private static Faker faker = new Faker(locale);
	private static JavascriptExecutor jsExecutor;
	static DataHelper data2 = DataHelper.getData();
	static WebDriver driver;

	public static String getTextOnTextboxByJS(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("return arguments[0].value", driver.findElement(By.name("firstName"))).toString();
		// return null;
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(5000);
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/payGrade/3");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()[contains(.,'Add')]]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[contains(@class,'oxd-select-text--arrow')]")).click();
		Thread.sleep(2000);
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@role='listbox']"));
		List<String> html = new ArrayList<>();
		for (WebElement a : list) {
			html.add(a.getText());
		}
		for (String b : html) {
			System.out.println(b);
		}
		
		
//		List<WebElement> elementList = new ArrayList<WebElement>();
//		elementList = driver.findElements(By.xpath("//div[@role='listbox']"));
//		List<String> list = new ArrayList<String>();
//		for (WebElement a: elementList) {
//			list.add(a.getAttribute("innerHTML"));
//		}
//		for (String b:list) {
//			System.out.println(b);
//		}
		
	}

}
