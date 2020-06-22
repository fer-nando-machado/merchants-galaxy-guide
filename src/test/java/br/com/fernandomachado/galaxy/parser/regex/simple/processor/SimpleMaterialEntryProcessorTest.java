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
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link SimpleMaterialEntryProcessor}.
 */
public class SimpleMaterialEntryProcessorTest extends SingletonDAOTestHelper {

    private SimpleMaterialEntryProcessor processor = new SimpleMaterialEntryProcessor();

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

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.TITLE, "Tin");
        container.addVariable(SimpleToken.TITLE, "Credits");
        container.addVariable(SimpleToken.NUMBER, "40");

        assertNull(this.processor.run(container));
        assertEquals(10, this.materialDAO.findPrice("Tin", "Credits"), 0);
    }

    @Test
    public void runTest$updatingPrices() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));

        Currency bitcoins = new Currency("BitCoins");
        Currency starbits = new Currency("StarBits");
        Material gold = new Material("Gold");
        gold.addPrice(new Price(bitcoins, 1000.0));
        gold.addPrice(new Price(starbits, 500.0));
        Material silver = new Material("Silver");
        silver.addPrice(new Price(bitcoins, 100.0));
        this.materialDAO.save(gold);
        this.materialDAO.save(silver);

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.TITLE, gold.getName());
        container.addVariable(SimpleToken.TITLE, bitcoins.getName());
        container.addVariable(SimpleToken.NUMBER, "750");
        this.processor.run(container);

        container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.TITLE, silver.getName());
        container.addVariable(SimpleToken.TITLE, starbits.getName());
        container.addVariable(SimpleToken.NUMBER, "50");
        this.processor.run(container);

        assertEquals(750, this.materialDAO.findPrice(gold.getName(), bitcoins.getName()), 0);
        assertEquals(50, this.materialDAO.findPrice(silver.getName(), starbits.getName()), 0);

    }

    @Test(expected = SimpleProcessorException.class)
    public void runTest$missingTranslation() {
        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "one");
        container.addVariable(SimpleToken.TITLE, "Dirt");
        container.addVariable(SimpleToken.TITLE, "Reais");
        container.addVariable(SimpleToken.NUMBER, "1");

        assertNull(this.processor.run(container));
    }

    @Test(expected = NumberFormatException.class)
    public void runTest$invalidNumberFormat() {
        this.translationDAO.save(new Translation("one", RomanSymbol.I));
        this.translationDAO.save(new Translation("five", RomanSymbol.V));
        this.translationDAO.save(new Translation("ten", RomanSymbol.X));

        VariableContainer container = new VariableContainer();
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.WORD, "five");
        container.addVariable(SimpleToken.TITLE, "Tin");
        container.addVariable(SimpleToken.TITLE, "Credits");
        container.addVariable(SimpleToken.NUMBER, "40");

        this.processor.run(container);
    }

}
