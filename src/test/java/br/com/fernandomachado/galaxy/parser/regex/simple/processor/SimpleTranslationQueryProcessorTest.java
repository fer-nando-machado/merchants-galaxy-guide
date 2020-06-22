package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.dao.TranslationDAO;
import br.com.fernandomachado.galaxy.dao.singleton.SingletonDAOTestHelper;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanSymbol;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link SimpleTranslationQueryProcessor}.
 */
public class SimpleTranslationQueryProcessorTest extends SingletonDAOTestHelper {

    private SimpleTranslationQueryProcessor processor = new SimpleTranslationQueryProcessor();

    private TranslationDAO translationDAO;

    @Before
    public void before() {
        this.translationDAO = AbstractDAOFactory.getDAOFactory().getTranslationDAO();
    }

    @Test
    public void runTest() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "one");

        assertEquals("ten ten ten five one one is 37", this.processor.run(container));
    }

    @Test(expected = SimpleProcessorException.class)
    public void runTest$missingTranslation() {
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.WORD, "one");

        this.processor.run(container);
    }

    @Test(expected = NumberFormatException.class)
    public void runTest$invalidNumberFormat() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "one");

        this.processor.run(container);
    }

}
