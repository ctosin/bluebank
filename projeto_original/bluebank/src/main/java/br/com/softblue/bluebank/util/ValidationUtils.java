package br.com.softblue.bluebank.util;

public final class ValidationUtils {

	private ValidationUtils() {}
	
	public static void require(boolean condition) {
		require(condition, null);
	}
	
	public static void require(boolean condition, String errorMessage) {
		if (!condition) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
}
