package br.com.fernandomachado.galaxy.parser.regex.simple;

import br.com.fernandomachado.galaxy.dao.singleton.SingletonDAOTestHelper;
import br.com.fernandomachado.galaxy.parser.Parser;
import org.junit.Test;

import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.ERROR_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link SimpleRegexParser}.
 */
public class SimpleRegexParserTest extends SingletonDAOTestHelper {

    private Parser parser = new SimpleRegexParser();

    @Test
    public void parseTest$exampleScenario() {
        assertNull(this.parser.parse("glob is I"));
        assertNull(this.parser.parse("glob is I"));
        assertNull(this.parser.parse("prok is V"));
        assertNull(this.parser.parse("pish is X"));
        assertNull(this.parser.parse("tegj is L"));

        assertNull(this.parser.parse("glob glob Silver is 34 Credits"));
        assertNull(this.parser.parse("glob prok Gold is 57800 Credits"));
        assertNull(this.parser.parse("pish pish Iron is 3910 Credits"));

        assertEquals("pish tegj glob glob is 42", this.parser.parse("how much is pish tegj glob glob ?"));

        assertEquals("glob prok Silver is 68 Credits", this.parser.parse("how many Credits is glob prok Silver ?"));
        assertEquals("glob prok Gold is 57800 Credits", this.parser.parse("how many Credits is glob prok Gold ?"));
        assertEquals("glob prok Iron is 782 Credits", this.parser.parse("how many Credits is glob prok Iron ?"));

        assertEquals(ERROR_MESSAGE, this.parser.parse("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }

    @Test
    public void parseTest$multiCurrencyScenario() {
        assertNull(this.parser.parse("one is I"));
        assertNull(this.parser.parse("um is I"));
        assertNull(this.parser.parse("five is V"));
        assertNull(this.parser.parse("cinco is V"));
        assertNull(this.parser.parse("ten is X"));
        assertNull(this.parser.parse("dez is X"));
        assertNull(this.parser.parse("fifty is L"));
        assertNull(this.parser.parse("cinquenta is L"));
        assertNull(this.parser.parse("hundred is C"));
        assertNull(this.parser.parse("cem is C"));

        assertNull(this.parser.parse("um Gold is 1000 Reais")); //1000
        assertNull(this.parser.parse("five Gold is 500 Dollars")); //100
        assertNull(this.parser.parse("one one one Gold is 60 Credits")); //20
        assertNull(this.parser.parse("cinco um Silver is 900 Reais")); //150
        assertNull(this.parser.parse("ten ten Silver is 1000 Dollars")); //50
        assertNull(this.parser.parse("um one Stardust is 5 Credits")); //2.5
        assertEquals(ERROR_MESSAGE, this.parser.parse("trinta Gold is 10 Reais"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("one plata is 300 Dollars"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("ten Gold is 10 candies"));

        assertEquals("five Gold is 5000 Reais", this.parser.parse("how many Reais is five Gold ?"));
        assertEquals("cinco Silver is 250 Dollars", this.parser.parse("how many Dollars is cinco Silver ?"));
        assertEquals("five um one Gold is 140 Credits", this.parser.parse("how many Credits is five um one Gold ?"));
        assertEquals("hundred Silver is 15000 Reais", this.parser.parse("how many Reais is hundred Silver ?"));
        assertEquals("um one um Stardust is 7.5 Credits", this.parser.parse("how many Credits is um one um Stardust ?"));
        assertEquals("cem Stardust is 250 Credits", this.parser.parse("how many Credits is cem Stardust ?"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("how many Reais is trezentos Silver ?"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("how many Credits is dez Silver ?"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("how many Dollars is cinquenta Stardust ?"));
        assertEquals(ERROR_MESSAGE, this.parser.parse("how many Tacos is fifty Gold ?"));
    }

}
