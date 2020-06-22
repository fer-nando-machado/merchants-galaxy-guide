package br.com.fernandomachado.galaxy.model.numeral.roman;

import br.com.fernandomachado.galaxy.model.lookup.LookupException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link RomanNumeralFactory}.
 */
public class RomanNumeralFactoryTest {

    private RomanNumeralFactory factory = new RomanNumeralFactory();

    @Test
    public void toSymbolTest$character() {
        assertEquals(RomanSymbol.M, this.factory.toSymbol('M'));
        assertEquals(RomanSymbol.D, this.factory.toSymbol('D'));
        assertEquals(RomanSymbol.C, this.factory.toSymbol('C'));
        assertEquals(RomanSymbol.L, this.factory.toSymbol('L'));
        assertEquals(RomanSymbol.X, this.factory.toSymbol('X'));
        assertEquals(RomanSymbol.V, this.factory.toSymbol('V'));
        assertEquals(RomanSymbol.I, this.factory.toSymbol('I'));
    }

    @Test
    public void toSymbolTest$decimal() {
        assertEquals(RomanSymbol.M, this.factory.toSymbol(1000));
        assertEquals(RomanSymbol.D, this.factory.toSymbol(500));
        assertEquals(RomanSymbol.C, this.factory.toSymbol(100));
        assertEquals(RomanSymbol.L, this.factory.toSymbol(50));
        assertEquals(RomanSymbol.X, this.factory.toSymbol(10));
        assertEquals(RomanSymbol.V, this.factory.toSymbol(5));
        assertEquals(RomanSymbol.I, this.factory.toSymbol(1));
    }

    @Test(expected = LookupException.class)
    public void toSymbolTest$expectedIllegalArgumentException() {
        this.factory.toSymbol(7);
    }

    @Test
    public void toNumeralTest() {
        assertEquals(new RomanNumeral("MMXVIII"), this.factory.toNumeral("MMXVIII"));
    }
    @Test
    public void toDecimalTest() {
        assertEquals(2018, this.factory.toDecimal("MMXVIII").intValue());
    }

}
