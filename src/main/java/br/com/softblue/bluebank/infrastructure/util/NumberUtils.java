package br.com.softblue.bluebank.infrastructure.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public final class NumberUtils {
	
	private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("pt-BR");
	private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(DEFAULT_LOCALE);
	private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance(DEFAULT_LOCALE);
	
	private static final Random random = new Random();
	
	private NumberUtils() {}

	public static String formatCurrency(BigDecimal value) {
		return CURRENCY_FORMATTER.format(value);
	}
	
	public static String formatNumber(BigDecimal value) {
		return NUMBER_FORMATTER.format(value);
	}
	
	public static int random(int max) {
		return random.nextInt(0, max + 1);
	}
}
