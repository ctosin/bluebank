package br.com.softblue.bluebank.infrastructure.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberUtils {
	
	private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("pt-BR");
	private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(DEFAULT_LOCALE);
	
	private NumberUtils() {}

	public static String formatCurrency(BigDecimal value) {
		return FORMATTER.format(value);
	}
}
