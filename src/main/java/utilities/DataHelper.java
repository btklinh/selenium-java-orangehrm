package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {

	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);

	public static DataHelper getData() {
		return new DataHelper();
	}

	public String getJobTitle() {
		return faker.job().title();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getFullName() {
		return faker.name().fullName();
	}

	public String getAddress() {
		return faker.address().streetAddress();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getCity() {
		return faker.address().city();
	}
		
	public String getCityName() {
		return faker.address().cityName();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getPin() {
		return String.valueOf(faker.number().numberBetween(100000, 999999));
	}
	
	
	public String getPhone() {
		return faker.number().digits(10);
	}
	
	public String getPassword() {
		return faker.internet().password(6, 32);
	}
	
	public String getDateOfBirth() {
		Date date = faker.date().birthday();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateAfterFormat = dateFormat.format(date);
		return dateAfterFormat;
	}
	
	public String getDeposit() {
		return faker.number().digits(7);
	}
	
}
