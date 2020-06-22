package br.com.fernandomachado.galaxy.parser.regex.simple.processor;

import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;

import java.util.List;

import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.IS;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.SPACE;

/**
 * Process {@link Translation} query commands.
 */
public class SimpleTranslationQueryProcessor implements Processor {

    /**
     * Returns the {@link Integer} decimal representation of a {@link List<String>} of alien words.
     *
     * {@link VariableContainer} data:
     * One or more {@link SimpleToken#WORD}s representing alien words.
     *
     * @param container
     * @return {@link String} informing the {@link Integer} decimal value of the alien words.
     */
    public String run(VariableContainer container) {
        List<String> alienWords = container.getVariables(SimpleToken.WORD);

        Integer decimal = SimpleProcessorCommons.translateToDecimal(alienWords);

        StringBuilder output = new StringBuilder();
        output.append(String.join(SPACE, alienWords)).append(SPACE);
        output.append(IS).append(SPACE);
        output.append(decimal);
        return output.toString();
    }


}
