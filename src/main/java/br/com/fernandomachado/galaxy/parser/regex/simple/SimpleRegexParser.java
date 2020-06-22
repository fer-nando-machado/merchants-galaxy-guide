package br.com.fernandomachado.galaxy.parser.regex.simple;

import br.com.fernandomachado.galaxy.parser.regex.RegexParser;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleCommand;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleToken;

/**
 * Simple implementation of {@link RegexParser}.
 */
public class SimpleRegexParser extends RegexParser {

    public SimpleRegexParser() {
        super(SimpleCommand.values(), SimpleToken.values());
    }

}
