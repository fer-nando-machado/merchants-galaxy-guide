package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;
import br.com.fernandomachado.galaxy.model.Price;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;

import java.util.List;

import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.IS;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.SPACE;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.formatPrice;

/**
 * Process {@link Material} query commands.
 */
public class SimpleMaterialQueryProcessor implements Processor {

    /**
     * Returns the {@link Price} of a {@link Material}.
     *
     * {@link VariableContainer} data:
     * The first {@link SimpleToken#TITLE} representing the {@link Price} {@link Currency} name.
     * The second {@link SimpleToken#TITLE} representing the {@link Material} name.
     * One or more {@link SimpleToken#WORD}s representing the {@link Material} quantity in alien words.
     *
     * @param container
     * @return {@link String} informing the {@link Price} of the queried {@link Material}
     */
    public String run(VariableContainer container) {
        AbstractDAOFactory dao = AbstractDAOFactory.getDAOFactory();

        List<String> alienWords = container.getVariables(SimpleToken.WORD);
        List<String> names = container.getVariables(SimpleToken.TITLE);
        String currencyName = names.get(0);
        String materialName = names.get(1);

        Double materialPrice = dao.getMaterialDAO().findPrice(materialName, currencyName);
        if (materialPrice == null) {
            throw new SimpleProcessorException("Unable to find a price for " + materialName + " in " + currencyName + ".");
        }
        Integer materialQuantity = SimpleProcessorCommons.translateToDecimal(alienWords);
        Double totalPrice = materialPrice * materialQuantity;

        StringBuilder output = new StringBuilder();
        output.append(String.join(SPACE, alienWords)).append(SPACE);
        output.append(materialName).append(SPACE);
        output.append(IS).append(SPACE);
        output.append(formatPrice(totalPrice)).append(SPACE);
        output.append(currencyName);
        return output.toString();
    }

}
