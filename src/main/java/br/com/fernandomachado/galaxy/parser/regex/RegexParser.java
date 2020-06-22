package br.com.fernandomachado.galaxy.parser.regex;

import br.com.fernandomachado.galaxy.parser.Parser;
import br.com.fernandomachado.galaxy.parser.regex.pattern.CommandEnum;
import br.com.fernandomachado.galaxy.parser.regex.pattern.PatternEnum;
import br.com.fernandomachado.galaxy.parser.regex.pattern.TokenEnum;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainer;

import java.util.Arrays;
import java.util.List;

import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.ERROR_MESSAGE;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.MULTI_SPACE_REGEX;
import static br.com.fernandomachado.galaxy.parser.regex.RegexParserUtils.SPACE;

/**
 * Implementation of regex based {@link Parser}.
 * Concrete subclasses will need to inform which implementation of {@link CommandEnum}s and {@link TokenEnum}s to use.
 */
public abstract class RegexParser implements Parser {

    private CommandEnum[] commands;
    private TokenEnum[] tokens;

    protected RegexParser(CommandEnum[] commands, TokenEnum[] tokens) {
        this.commands = commands;
        this.tokens = tokens;
    }

    @Override
    public String parse(String input) {
        try {
            List<String> words = Arrays.asList(input.split(MULTI_SPACE_REGEX));
            VariableContainer container = new VariableContainer();
            StringBuilder inputToken = new StringBuilder();
            for (String word : words) {
                TokenEnum token = (TokenEnum) PatternEnum.toEnum(word, this.tokens);
                container.addVariable(token, word);
                inputToken.append(token.getName()).append(SPACE);
            }
            CommandEnum command = (CommandEnum) PatternEnum.toEnum(inputToken.toString().trim(), this.commands);
            return command.getProcessor().run(container);
        } catch (Exception e) {
            // TODO in the future, parse Exception messages in order to return a better description of the errors.
            return ERROR_MESSAGE;
        }
    }

}
