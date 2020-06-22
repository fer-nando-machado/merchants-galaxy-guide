package br.com.fernandomachado.galaxy.parser.regex.pattern;

import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;

/**
 * Interface for {@link Enum}s that represent commands associated with a {@link Processor}.
 */
public interface CommandEnum extends PatternEnum {

    Processor getProcessor();

}
