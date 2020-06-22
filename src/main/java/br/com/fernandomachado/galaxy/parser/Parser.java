package br.com.fernandomachado.galaxy.parser;

/**
 * Interface for input parser.
 */
public interface Parser {

    /**
     * Given a input {@link String}, parses its contents and returns its output.
     *
     * @param input
     * @return {@link String} output
     */
    String parse(String input);
}
