package br.com.fernandomachado.galaxy.parser.regex.processor;

import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Tests for {@link VariableContainer}
 */
public class VariableContainerTest {

    @Test
    public void addVariableTest() {
        VariableContainer container = new VariableContainer();

        container.addVariable(SimpleToken.HOW, "how");
        container.addVariable(SimpleToken.MUCH, "much");

        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "two");

        container.addVariable(SimpleToken.NUMBER, "7");
        container.addVariable(SimpleToken.NUMBER, "7");
        container.addVariable(SimpleToken.NUMBER, "7");

        assertNull(container.getVariables(SimpleToken.HOW));
        assertNull(container.getVariables(SimpleToken.MUCH));
        assertNull(container.getVariables(SimpleToken.TITLE));
        assertEquals(2, container.getVariables(SimpleToken.WORD).size());
        assertEquals(3, container.getVariables(SimpleToken.NUMBER).size());
    }


}
