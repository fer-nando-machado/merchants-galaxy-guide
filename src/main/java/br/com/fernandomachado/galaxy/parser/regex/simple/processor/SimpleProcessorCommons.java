package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.NumeralFactory;
import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;

import java.util.List;

/**
 * Contains methods that are common to Simple {@link Processor}s.
 */
public class SimpleProcessorCommons {

    /**
     * Given a {@link List<String>} of alien words, find their corresponding {@link Translation} in {@link SymbolEnum}s,
     * build them into a {@link String} numeral and, finally, convert it to a {@link Integer} decimal representation.
     *
     * @param alienWords
     * @return {@link Integer} decimal
     */
    public static Integer translateToDecimal(List<String> alienWords) {
        AbstractDAOFactory dao = AbstractDAOFactory.getDAOFactory();

        List<SymbolEnum> translatedSymbols = dao.getTranslationDAO().findSymbols(alienWords);
        if (alienWords.size() != translatedSymbols.size()) {
            throw new SimpleProcessorException("Unable to find a translation for every alien symbol.");
        }

        StringBuilder translatedNumeral = new StringBuilder();
        for (SymbolEnum translatedSymbol : translatedSymbols) {
            translatedNumeral.append(translatedSymbol.getCharacter());
        }
        return NumeralFactory.getNumeralFactory().toDecimal(translatedNumeral.toString());
    }

}
