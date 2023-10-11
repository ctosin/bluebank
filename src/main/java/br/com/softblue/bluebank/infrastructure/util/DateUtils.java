package br.com.softblue.bluebank.infrastructure.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
	
	private static final String PATTERN = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

	private DateUtils() {}

	public static String formatDate(LocalDate date) {
		return date.format(FORMATTER);
	}
}
