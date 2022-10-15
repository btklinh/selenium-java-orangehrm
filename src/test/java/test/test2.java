package test;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
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
		driver.get("https://letskodeit.teachable.com/p/practice");
		Thread.sleep(5000);
		driver.findElement(By.name("username")).sendKeys("btklinh");
		driver.findElement(By.name("password")).sendKeys("Klinh1993@!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(10000);
		driver.get("http://localhost/orangehrm-5.1/web/index.php/pim/viewPersonalDetails/empNumber/1");
		Thread.sleep(10000);
		System.out.print(getTextOnTextboxByJS(driver));
		
	}

}
