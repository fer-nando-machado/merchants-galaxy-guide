package br.com.fernandomachado.galaxy.parser.regex.pattern;

/**
 * Interface for {@link Enum}s that represent parser tokens.
 */
public interface TokenEnum extends PatternEnum {

    String getName();

    boolean isVariable();

}
