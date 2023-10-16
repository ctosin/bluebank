package bluebank;

import static br.com.softblue.bluebank.infrastructure.util.DateUtils.formatDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

	@Test
	public void testFormatDateSuccess() {
		assertEquals("16/10/2023", formatDate(LocalDate.of(2023, 10, 16)));
	}
	
	@Test
	void testFormatDateNull() {
		assertNull(formatDate(null));
	}
}
