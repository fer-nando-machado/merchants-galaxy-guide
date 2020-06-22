package br.com.fernandomachado.galaxy.parser.regex.processor;

/**
 * Interface for classes that are able to process {@link VariableContainer} data and return an output.
 */
public interface Processor {

    /**
     * Run the implemented processing strategy and returns its output.
     *
     * @param container
     * @return {@link String} containing the processing output
     */
    String run(VariableContainer container);

}
