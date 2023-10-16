package br.com.softblue.bluebank.test.utils;

import static br.com.softblue.bluebank.util.DateUtils.getFormattedDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

	@Test
	public void testGetFormattedDate() {
		String formatted = getFormattedDate(LocalDate.of(2023, 5, 17));
		assertEquals("17/05/2023", formatted);
		assertNull(getFormattedDate(null));
	}
}
