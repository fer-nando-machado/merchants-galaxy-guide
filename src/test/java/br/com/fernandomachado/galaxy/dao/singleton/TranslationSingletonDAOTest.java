package br.com.fernandomachado.galaxy.dao.singleton;

import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanSymbol;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link TranslationSingletonDAO}.
 */
public class TranslationSingletonDAOTest {

    private TranslationSingletonDAO dao = new TranslationSingletonDAO();

    @Test
    public void findSymbolTest() {
        this.dao.save(new Translation("one", RomanSymbol.I));
        this.dao.save(new Translation("five", RomanSymbol.V));
        this.dao.save(new Translation("ten", RomanSymbol.X));

        assertEquals(RomanSymbol.I, this.dao.findSymbol("one"));
        assertEquals(RomanSymbol.V, this.dao.findSymbol("five"));
        assertEquals(RomanSymbol.X, this.dao.findSymbol("ten"));
    }

    @Test
    public void findSymbolTest$notFound() {
        assertNull(this.dao.findSymbol("ten"));
    }

    @Test
    public void findSymbolsTest() {
        this.dao.save(new Translation("one", RomanSymbol.I));
        this.dao.save(new Translation("five", RomanSymbol.V));
        this.dao.save(new Translation("ten", RomanSymbol.X));

        assertEquals(Arrays.asList(RomanSymbol.X, RomanSymbol.V, RomanSymbol.I),
                this.dao.findSymbols(Arrays.asList("ten", "five", "one")));

        assertEquals(Arrays.asList(RomanSymbol.X, RomanSymbol.I, RomanSymbol.X),
                this.dao.findSymbols(Arrays.asList("ten", "two", "one", "fifty", "two", "ten")));

        assertEquals(Collections.emptyList(), this.dao.findSymbols(Arrays.asList("nine, two")));
    }

}
