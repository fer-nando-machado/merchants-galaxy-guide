package br.com.fernandomachado.galaxy.parser.regex.simple.pattern;

import br.com.fernandomachado.galaxy.model.lookup.LookupException;
import br.com.fernandomachado.galaxy.parser.regex.pattern.PatternEnum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleCommandTest {

    @Test
    public void matchTest() {
        assertEquals(SimpleCommand.TRANSLATION_ENTRY, match("WORD IS SYMBOL"));

        assertEquals(SimpleCommand.TRANSLATION_QUERY, match("HOW MUCH IS WORD QUESTION"));
        assertEquals(SimpleCommand.TRANSLATION_QUERY, match("HOW MUCH IS WORD WORD WORD QUESTION"));

        assertEquals(SimpleCommand.MATERIAL_ENTRY, match("WORD TITLE IS NUMBER TITLE"));
        assertEquals(SimpleCommand.MATERIAL_ENTRY, match("WORD WORD WORD WORD TITLE IS NUMBER TITLE"));

        assertEquals(SimpleCommand.MATERIAL_QUERY, match("HOW MANY TITLE IS WORD TITLE QUESTION"));
        assertEquals(SimpleCommand.MATERIAL_QUERY, match("HOW MANY TITLE IS WORD WORD TITLE QUESTION"));
    }

    @Test(expected = LookupException.class)
    public void matchTest$incompleteQuery() {
        assertEquals(SimpleCommand.TRANSLATION_ENTRY, match("HOW MUCH IS QUESTION"));
    }

    private static SimpleCommand match(String s) {
        return (SimpleCommand) PatternEnum.toEnum(s, SimpleCommand.values());
    }

}
