package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.dao.TranslationDAO;
import br.com.fernandomachado.galaxy.dao.singleton.SingletonDAOTestHelper;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.lookup.LookupException;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanSymbol;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link SimpleTranslationEntryProcessor}.
 */
public class SimpleTranslationEntryProcessorTest extends SingletonDAOTestHelper {

    private SimpleTranslationEntryProcessor processor = new SimpleTranslationEntryProcessor();

    private TranslationDAO translationDAO;

    @Before
    public void before() {
        this.translationDAO = AbstractDAOFactory.getDAOFactory().getTranslationDAO();
    }

    @Test
    public void runTest() {
        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.SYMBOL, "I");

        assertNull(this.processor.run(container));
        assertEquals(RomanSymbol.I, this.translationDAO.findSymbol("one"));
    }

    @Test
    public void runTest$updatingTranslations() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "um");
        container.addVariable(SimpleToken.SYMBOL, "I");
        this.processor.run(container);

        container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "dez");
        container.addVariable(SimpleToken.SYMBOL, "X");
        this.processor.run(container);

        assertEquals(RomanSymbol.I, this.translationDAO.findSymbol("um"));
        assertEquals(RomanSymbol.V, this.translationDAO.findSymbol("five"));
        assertEquals(RomanSymbol.X, this.translationDAO.findSymbol("dez"));
    }

    @Test(expected = LookupException.class)
    public void runTest$unableToFindSymbol() {
        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.SYMBOL, "T");
        this.processor.run(container);
    }

}
