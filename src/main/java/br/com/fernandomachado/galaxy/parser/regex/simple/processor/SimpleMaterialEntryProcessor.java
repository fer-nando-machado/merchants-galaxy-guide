package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;
import br.com.fernandomachado.galaxy.model.Price;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;

import java.util.List;

/**
 * Process {@link Material} data entry commands.
 */
public class SimpleMaterialEntryProcessor implements Processor {

    /**
     * Persists {@link Material}.
     *
     * {@link VariableContainer} data:
     * The first {@link SimpleToken#TITLE} representing the {@link Material} name.
     * The second {@link SimpleToken#TITLE} representing the {@link Price} {@link Currency} name.
     * A single {@link SimpleToken#NUMBER} representing the {@link Price} unit.
     * One or more {@link SimpleToken#WORD}s representing the {@link Material} quantity in alien words.
     *
     * @param container
     * @return null
     */
    public String run(VariableContainer container) {
        AbstractDAOFactory dao = AbstractDAOFactory.getDAOFactory();

        List<String> quantityInAlienWords = container.getVariables(SimpleToken.WORD);
        List<String> titles = container.getVariables(SimpleToken.TITLE);
        String materialName = titles.get(0);
        String currencyName = titles.get(1);
        Double totalPrice = Double.valueOf(container.getVariables(SimpleToken.NUMBER).get(0));

        Integer materialQuantity = SimpleProcessorCommons.translateToDecimal(quantityInAlienWords);
        Double materialPrice = totalPrice / materialQuantity;

        Material material = new Material(materialName);
        material.addPrice(new Price(new Currency(currencyName), materialPrice));
        dao.getMaterialDAO().save(material);
        return null;
    }

}
