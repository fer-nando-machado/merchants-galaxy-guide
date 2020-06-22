package br.com.fernandomachado.galaxy.parser.regex.simple.pattern;

import br.com.fernandomachado.galaxy.parser.regex.pattern.PatternEnum;
import br.com.fernandomachado.galaxy.parser.regex.pattern.TokenEnum;

import java.util.regex.Pattern;

/**
 * Simple {@link TokenEnum}s identified by the parser.
 */
public enum SimpleToken implements TokenEnum {
    // reserved keywords
    IS("is", false),
    HOW("how", false),
    MUCH("much", false),
    MANY("many", false),
    QUESTION("\\?", false),
    // variable content
    TITLE("[A-Z][a-z]+", true), // titles must start with uppercase and be followed by at least one lowercase character
    SYMBOL("[A-Z]", true), // symbols are composed by a single uppercase character
    WORD("[a-z]+", true), // words are lowercase strings
    NUMBER("\\d+(\\.\\d+)?", true); // numbers are numeric-only strings (and may contain a single '.' separator)

    private Pattern pattern;
    private boolean isVariable;

    SimpleToken(String regex, boolean isVariable) {
        this.pattern = Pattern.compile(regex);
        this.isVariable = isVariable;
    }

    @Override
    public Pattern getPattern() {
        return this.pattern;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public boolean isVariable() {
        return this.isVariable;
    }

}
