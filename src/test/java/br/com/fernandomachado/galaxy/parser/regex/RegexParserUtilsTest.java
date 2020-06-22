package br.com.fernandomachado.galaxy.parser.regex;

import org.junit.Test;

import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.DECIMAL_SEPARATOR;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.formatPrice;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link RegexParserUtils}.
 */
public class RegexParserUtilsTest {

    @Test
    public void formatPriceTest() {
        assertEquals("1", formatPrice(1.));
        assertEquals("10", formatPrice(10.0));
        assertEquals("100" + DECIMAL_SEPARATOR + "25", formatPrice(100.25));
        assertEquals("1000" + DECIMAL_SEPARATOR + "5", formatPrice(1000.5));
        assertEquals("10000" + DECIMAL_SEPARATOR + "75", formatPrice(10000.75));
        assertEquals("0" + DECIMAL_SEPARATOR + "28", formatPrice(0.2813203946));
        assertEquals("1" + DECIMAL_SEPARATOR + "99", formatPrice(1.9895244945));
        assertEquals("2" + DECIMAL_SEPARATOR + "76", formatPrice(2.7626171527));
        assertEquals("3" + DECIMAL_SEPARATOR + "33", formatPrice(3.3293418826));
        assertEquals("4" + DECIMAL_SEPARATOR + "15", formatPrice(4.1450975899));
        assertEquals("5" + DECIMAL_SEPARATOR + "48", formatPrice(5.4813207834));
        assertEquals("6" + DECIMAL_SEPARATOR + "03", formatPrice(6.0342305523));
        assertEquals("7" + DECIMAL_SEPARATOR + "52", formatPrice(7.5247624626));
        assertEquals("8" + DECIMAL_SEPARATOR + "64", formatPrice(8.6350585511));
        assertEquals("9" + DECIMAL_SEPARATOR + "84", formatPrice(9.8419025909));
    }

}
