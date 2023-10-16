package bluebank;

import static br.com.softblue.bluebank.infrastructure.util.NumberUtils.formatCurrency;
import static br.com.softblue.bluebank.infrastructure.util.NumberUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NumberUtilsTest {

	@Test
	public void testFormatCurrency() {
		assertEquals("100,00", formatCurrency(new BigDecimal(100)).substring(3));
	}
	
	@RepeatedTest(10)
	public void testRandom() {
		//assertEquals(0, random(1));
		
		//int n = random(2);
		//assertTrue(n == 0 || n == 1);
		
		assertThrows(IllegalArgumentException.class, () -> random(-1));
	}
}
