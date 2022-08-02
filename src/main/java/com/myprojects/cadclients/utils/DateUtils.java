package com.myprojects.cadclients.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

	public static LocalDate convertStringToLocalDate(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, new Locale("pt", "br"));
		return LocalDate.parse(date, formatter);
	}

	public static LocalDateTime convertStringToLocalDateTime(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, new Locale("pt", "br"));
		return LocalDateTime.parse(date, formatter);
	}

	public static LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date convertLocalDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String convertLocalDateToString(LocalDate localDate, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, new Locale("pt", "br"));
		return localDate.format(formatter);
	}

	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, new Locale("pt", "br"));
		return localDateTime.format(formatter);
	}

	public static String formatDateToString(Date dt, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "br"));
		return dt != null ? df.format(dt) : "";
	}

	public static Date formatStringToData(String sData, String pattern) {
		if (sData == null || sData.isEmpty()) {
			return null;
		}
		Date dt = null;
		try {
			DateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "br"));
			dt = (java.util.Date) df.parse(sData);
		} catch (ParseException e) {
			return null;
		}
		return dt;
	}

}
