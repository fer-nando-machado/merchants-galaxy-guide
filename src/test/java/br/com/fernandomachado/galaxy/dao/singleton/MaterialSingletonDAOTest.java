package br.com.fernandomachado.galaxy.dao.singleton;

import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;
import br.com.fernandomachado.galaxy.model.Price;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link MaterialSingletonDAO}.
 */
public class MaterialSingletonDAOTest {

    private MaterialSingletonDAO dao = new MaterialSingletonDAO();

    private Currency reais = new Currency("Reais");
    private Currency dollars  = new Currency("Dollars");
    private Currency credits = new Currency("Credits");

    @Test
    public void findTest() {
        Material input = new Material("Gold");
        input.addPrice(new Price(this.reais, 5.0));
        input.addPrice(new Price(this.dollars, 2.5));
        input.addPrice(new Price(this.credits, 100.0));
        this.dao.save(input);

        Material actual = this.dao.find("Gold");
        assertEquals(input.getName(), actual.getName());
        assertEquals(input.getPrices().size(), actual.getPrices().size());
    }

    @Test
    public void findTest$notFound() {
        assertNull(this.dao.find("Sand"));
    }

    @Test
    public void findPriceTest() {
        Material input = new Material("Silver");
        input.addPrice(new Price(this.reais, 7.5));
        input.addPrice(new Price(this.dollars, 5.0));
        this.dao.save(input);

        input = new Material("Silver");
        input.addPrice(new Price(this.reais, 1.0));
        this.dao.save(input);

        assertEquals(1.0, this.dao.findPrice("Silver", this.reais.getName()), 0);
        assertEquals(5.0, this.dao.findPrice("Silver", this.dollars.getName()), 0);
    }

    @Test
    public void findPriceTest$notFound() {
        Material input = new Material("Bronze");
        input.addPrice(new Price(this.credits, 1.0));
        this.dao.save(input);

        input = new Material("Cooper");
        this.dao.save(input);

        assertNull(this.dao.findPrice("Bronze", this.reais.getName()));
        assertNull(this.dao.findPrice("Cooper", this.credits.getName()));
        assertNull(this.dao.findPrice("Brass", this.dollars.getName()));
    }

}
