package br.com.fernandomachado.galaxy.model.numeral.roman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link RomanNumeralHelper}.
 */
public class RomanNumeralHelperTest {

    @Test
    public void toDecimalTest() {
        assertEquals(1, toDecimal("I"));
        assertEquals(4, toDecimal("IV"));
        assertEquals(5, toDecimal("V"));
        assertEquals(9, toDecimal("IX"));
        assertEquals(10, toDecimal("X"));
        assertEquals(40, toDecimal("XL"));
        assertEquals(50, toDecimal("L"));
        assertEquals(90, toDecimal("XC"));
        assertEquals(100, toDecimal("C"));
        assertEquals(400, toDecimal("CD"));
        assertEquals(500, toDecimal("D"));
        assertEquals(900, toDecimal("CM"));
        assertEquals(999, toDecimal("CMXCIX"));
        assertEquals(1000, toDecimal("M"));
        assertEquals(1888, toDecimal("MDCCCLXXXVIII"));
        assertEquals(1944, toDecimal("MCMXLIV"));
        assertEquals(2018, toDecimal("MMXVIII"));
    }

    @Test(expected = NumberFormatException.class)
    public void toDecimalTest$unexpectedFormat() {
        toDecimal("MMMM");
    }

    @Test
    public void toNumeralTest() {
        assertEquals("I", toNumeral(1));
        assertEquals("II", toNumeral(2));
        assertEquals("III", toNumeral(3));
        assertEquals("IV", toNumeral(4));
        assertEquals("V", toNumeral(5));
        assertEquals("VI", toNumeral(6));
        assertEquals("VII", toNumeral(7));
        assertEquals("VIII", toNumeral(8));
        assertEquals("IX", toNumeral(9));
        assertEquals("X", toNumeral(10));
        assertEquals("CMXCIX", toNumeral(999));
        assertEquals("MDCCCLXXXVIII", toNumeral(1888));
        assertEquals("MCMIII", toNumeral(1903));
        assertEquals("MMXVIII", toNumeral(2018));
    }

    @Test(expected = NumberFormatException.class)
    public void toNumeralTest$belowMinimalLimit() {
        toNumeral(0);
    }

    @Test(expected = NumberFormatException.class)
    public void toNumeralTest$overMaximumLimit() {
        toNumeral(4000);
    }

    private static int toDecimal(String numeral) {
        return RomanNumeralHelper.toDecimal(numeral);
    }

    private static String toNumeral(int decimal) {
        return RomanNumeralHelper.toNumeral(decimal);
    }
    
}
