package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.NumeralFactory;
import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;


/**
 * Process {@link Translation} data entry commands.
 */
public class SimpleTranslationEntryProcessor implements Processor {

    /**
     * Persists {@link Translation}.
     *
     * {@link VariableContainer} data:
     * A single {@link SimpleToken#WORD} representing an alien word.
     * A single {@link SimpleToken#SYMBOL} character representing its corresponding {@link SymbolEnum} translation.
     *
     * @param container
     * @return null
     */
    public String run(VariableContainer container) {
        AbstractDAOFactory dao = AbstractDAOFactory.getDAOFactory();

        String alienWord = container.getVariables(SimpleToken.WORD).get(0);
        char symbolCharacter = container.getVariables(SimpleToken.SYMBOL).get(0).charAt(0);

        SymbolEnum symbol = NumeralFactory.getNumeralFactory().toSymbol(symbolCharacter);
        Translation translation = new Translation(alienWord, symbol);
        dao.getTranslationDAO().save(translation);
        return null;
    }


}
