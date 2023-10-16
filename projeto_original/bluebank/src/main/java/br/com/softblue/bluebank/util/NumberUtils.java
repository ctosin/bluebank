package br.com.softblue.bluebank.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberUtils {
	
	private static final Locale LOCALE = Locale.forLanguageTag("pt-BR");

	private NumberUtils() {}
	
	public static String getFormattedCurrency(BigDecimal value) {
		return NumberFormat.getCurrencyInstance(LOCALE).format(value.doubleValue());
	}
	
	public static String getFormattedNumber(BigDecimal value) {
		return NumberFormat.getNumberInstance(LOCALE).format(value.doubleValue());
	}
}
