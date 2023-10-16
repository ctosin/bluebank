package br.com.softblue.bluebank.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class DateUtils {

	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private DateUtils() {}
	
	public static String getFormattedDate(LocalDate date) {
		return Optional
			.ofNullable(date)
			.map(d -> date.format(FORMATTER))
			.orElse(null);
	}
}
