package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.joda.time.Hours;

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
//	public int fromTime() {
//		return faker.number().numberBetween(0, 24);
//	}
//	public int minutes() {
//		return faker.number().numberBetween(0, 59);
//	}
//	public String getTime() {
//		String time = String.valueOf(hours()) + ":" +String.valueOf(minutes());
//		return time;
//	}
	
	public String getDeposit() {
		return faker.number().digits(7);
	}
	public int getMinimumValue() {
		return faker.number().numberBetween(10000, 80000);
	}
	public int getMaximumValue() {
		return faker.number().numberBetween(90000, 200000);
	}
	public int getRandomIndex(int maximum) {
		return faker.number().numberBetween(0, maximum);
	}
	
	public String fromTime() {
		int hour;
		String time;
		int minute = faker.number().numberBetween(0, 59);
		if (minute == 59) {
			hour = faker.number().numberBetween(0, 23);

		} else {
			hour = faker.number().numberBetween(0, 24);
		}
		time = formatTime(hour, minute);
		return time;
	}

	public String formatTime(int hour, int minute) {
		String fHour, fMinute, time;
		if (hour < 10) {
			fHour = "0" + String.valueOf(hour);
		} else
			fHour = String.valueOf(hour);
		if (minute < 10)
			fMinute = "0" + String.valueOf(minute);
		else
			fMinute = String.valueOf(minute);
		time = fHour + ":" + fMinute;
		return time;
	}

	public String toTime(String time) {
		// int hour;
		int fromHour, toHour, toMinute;
		String toTime;
		fromHour = Integer.parseInt(time.substring(0, 2));

		if (fromHour < 24) {
			toHour = faker.number().numberBetween(fromHour + 1, 24);
		} else {
			toHour = 24;
		}
		if (toHour != 24) {
			toMinute = faker.number().numberBetween(0, 59);
		} else {
			toMinute = 0;
		}
		toTime = formatTime(toHour, toMinute);
		return toTime;
	}
}
