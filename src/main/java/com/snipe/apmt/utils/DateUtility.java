package com.snipe.apmt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtility {

	protected static Logger logger = LoggerFactory.getLogger(DateUtility.class);
	public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyy";
	public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DD_MMM_YYYY_HHMMSS = "dd-MMM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	private DateUtility() {

	}

	public static boolean isThisDateValid(String dateToValidate, String dateFromat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateToValidate);
		} catch (Exception e) {
			logger.error("isThisDateValid:Error:", e);
			return false;
		}
		return true;
	}
	
	public static boolean dobValidation(String dob) throws ParseException {
		String dateString = dob;
		Date dobDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY).parse(dateString);
		Calendar now = Calendar.getInstance();
		Calendar dobCal = Calendar.getInstance();
		dobCal.setTime(dobDate);
		if (dobCal.after(now))
			return false;
		int age = now.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
		if (now.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		if (age < 18)
			return false;
		else
			return true;
	}

}
