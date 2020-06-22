package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.dao.MaterialDAO;
import br.com.fernandomachado.galaxy.dao.TranslationDAO;
import br.com.fernandomachado.galaxy.dao.singleton.SingletonDAOTestHelper;
import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;
import br.com.fernandomachado.galaxy.model.Price;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanSymbol;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link SimpleMaterialQueryProcessor}.
 */
public class SimpleMaterialQueryProcessorTest extends SingletonDAOTestHelper {

    private SimpleMaterialQueryProcessor processor = new SimpleMaterialQueryProcessor();

    private MaterialDAO materialDAO;
    private TranslationDAO translationDAO;

    @Before
    public void before() {
        this.materialDAO = AbstractDAOFactory.getDAOFactory().getMaterialDAO();
        this.translationDAO = AbstractDAOFactory.getDAOFactory().getTranslationDAO();
    }

    @Test
    public void runTest() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        Currency credits = new Currency("Credits");
        Material material = new Material("Gold");
        material.addPrice(new Price(credits, 50.0));
        this.materialDAO.save(material);

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.TITLE, credits.getName());
        container.addVariable(SimpleToken.TITLE, material.getName());

        assertEquals("one ten Gold is 450 Credits", this.processor.run(container));
    }

    @Test(expected = SimpleProcessorException.class)
    public void runTest$missingPrice() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.TITLE, "Credits");
        container.addVariable(SimpleToken.TITLE, "Gold");

        this.processor.run(container);
    }

    @Test(expected = SimpleProcessorException.class)
    public void runTest$missingTranslation() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));

        Currency credits = new Currency("Credits");
        Material material = new Material("Gold");
        material.addPrice(new Price(credits, 50.0));
        this.materialDAO.save(material);

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.TITLE, credits.getName());
        container.addVariable(SimpleToken.TITLE, material.getName());

        this.processor.run(container);
    }

    @Test(expected = NumberFormatException.class)
    public void runTest$invalidNumberFormat() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        Currency credits = new Currency("Credits");
        Material material = new Material("Gold");
        material.addPrice(new Price(credits, 50.0));
        this.materialDAO.save(material);

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "ten");
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.TITLE, credits.getName());
        container.addVariable(SimpleToken.TITLE, material.getName());

        this.processor.run(container);
    }

}
