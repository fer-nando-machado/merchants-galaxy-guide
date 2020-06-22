package br.com.fernandomachado.galaxy.parser.regex.simple.pattern;

import br.com.fernandomachado.galaxy.model.lookup.LookupException;
import br.com.fernandomachado.galaxy.parser.regex.pattern.PatternEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleTokenTest {

    @Test
    public void matchTest() {
        assertEquals(SimpleToken.IS, match("is"));
        assertEquals(SimpleToken.HOW, match("how"));
        assertEquals(SimpleToken.MUCH, match("much"));
        assertEquals(SimpleToken.MANY, match("many"));
        assertEquals(SimpleToken.QUESTION, match("?"));

        assertEquals(SimpleToken.TITLE, match("Or"));
        assertEquals(SimpleToken.TITLE, match("Palladium"));

        assertEquals(SimpleToken.SYMBOL, match("S"));

        assertEquals(SimpleToken.WORD, match("one"));
        assertEquals(SimpleToken.WORD, match("thousand"));

        assertEquals(SimpleToken.NUMBER, match("001"));
        assertEquals(SimpleToken.NUMBER, match("10.0"));
        assertEquals(SimpleToken.NUMBER, match("100.5"));
        assertEquals(SimpleToken.NUMBER, match("1000.75"));
    }

    @Test(expected = LookupException.class)
    public void matchTest$unsupportedDecimalSeparator() {
        match("10,65");
    }

    @Test(expected = LookupException.class)
    public void matchTest$unsupportedDecimalIntegerFormat() {
        match("5.");
    }

    @Test(expected = LookupException.class)
    public void matchTest$unsupportedDecimalFractionalFormat() {
        match(".2");
    }

    @Test(expected = LookupException.class)
    public void matchTest$unsupportedCamelCase() {
        match("CamelCase");
    }

    @Test(expected = LookupException.class)
    public void matchTest$unsupportedAllCaps() {
        match("CAPITAL");
    }

    private static SimpleToken match(String s) {
        return (SimpleToken) PatternEnum.toEnum(s, SimpleToken.values());
    }

}
