package test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.github.javafaker.Faker;

import utilities.DataHelper;

public class test2 {

	private static Locale locale = new Locale("en");
	private static Faker faker = new Faker(locale);

	static DataHelper data2 = DataHelper.getData();

	public static String fromTime() {
		int hour = faker.number().numberBetween(0, 24);
		int minute;
		String time;
		if (hour == 24)
			minute = 0;
		else
			minute = faker.number().numberBetween(0, 59);
		time = formatTime(hour, minute);
		return time;
	}

	public static String toTime(String time) {
		// int hour;
		int fromHour, toHour, toMinute;
		String toTime;
		fromHour = Integer.parseInt(time.substring(0, 2));
		if (fromHour < 24) {
			toHour = faker.number().numberBetween(fromHour + 1, 24);
		} else {
			toHour = 24;
		}
		if (toHour == 24) {
			toMinute = 0;
		} else {
			toMinute = faker.number().numberBetween(0, 59);
		}
		toTime = formatTime(toHour, toMinute);
		return toTime;
	}

	public static String formatTime(int hour, int minute) {
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

	public static int convertToHour(String time) {
		return Integer.parseInt(time.substring(0, 2));
	}

	public static int convertToMinute(String time) {
		return Integer.parseInt(time.substring(3, 5));
	}

	public static String convertToSuffix(String time) {
		return time.substring(6, 8);
	}

	public static String convert12HTo24H(String time) {
		// String convertTime;
		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		Date _12HourDt;
		try {
			_12HourDt = _12HourSDF.parse(time);
			return String.valueOf(_24HourSDF.format(_12HourDt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


//	public static String calDurationPerDay(String workingHourFrom, String workingHourTo) throws ParseException {
//		int fromHh, toHh, fromMm, toMm;
//		double duration;
//		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
//		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
//		Date _12HourDt, _12HourDt2;
//		_12HourDt = _12HourSDF.parse(workingHourFrom);
//		_12HourDt2 = _12HourSDF.parse(workingHourTo);
//		fromHh = _12HourDt.getHours();
//	}
	
	public String calDurationPerDay(String workingHourFrom, String workingHourTo) {
		double fromHh, toHh, fromMm, toMm, duration;
	
		
		fromHh = convertToHour(convert12HTo24H(workingHourFrom));
		toHh = convertToHour(convert12HTo24H(workingHourTo));
		fromMm = convertToMinute(convert12HTo24H(workingHourFrom));
		toMm = convertToMinute(convert12HTo24H(workingHourTo));
		DecimalFormat df = new DecimalFormat("0.00");
		duration = (toHh + (toMm / 60)) - (fromHh + (fromMm / 60));
		return String.valueOf(df.format(duration));
	}

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
//		  // Instantiate a Date object
//	      Date date = new Date();

	      // display time and date using toString()
		date = _24HourSDF.parse("12:21");
	      System.out.println(date.toString());
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY)); // gets hour in 24h format
		System.out.println(calendar.get(Calendar.MINUTE));        // gets hour in 12h format
	}

}
