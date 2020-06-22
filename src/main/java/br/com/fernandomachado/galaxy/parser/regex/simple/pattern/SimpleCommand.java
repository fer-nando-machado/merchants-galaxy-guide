package br.com.fernandomachado.galaxy.parser.regex.simple.pattern;

import br.com.fernandomachado.galaxy.parser.regex.pattern.CommandEnum;
import br.com.fernandomachado.galaxy.parser.regex.pattern.PatternEnum;
import br.com.fernandomachado.galaxy.parser.regex.processor.Processor;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleMaterialEntryProcessor;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleMaterialQueryProcessor;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleTranslationEntryProcessor;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleTranslationQueryProcessor;

import java.util.regex.Pattern;

/**
 * Simple {@link CommandEnum}s understood by the parser.
 */
public enum SimpleCommand implements CommandEnum {
    TRANSLATION_ENTRY("^WORD IS SYMBOL$", new SimpleTranslationEntryProcessor()),
    TRANSLATION_QUERY("^HOW MUCH IS [WORD ]+QUESTION$", new SimpleTranslationQueryProcessor()),
    MATERIAL_ENTRY("^[WORD ]+TITLE IS NUMBER TITLE$", new SimpleMaterialEntryProcessor()),
    MATERIAL_QUERY("^HOW MANY TITLE IS [WORD ]+TITLE QUESTION$", new SimpleMaterialQueryProcessor());

    private Pattern pattern;
    private Processor processor;

    SimpleCommand(String regex, Processor processor) {
        this.pattern = Pattern.compile(regex);
        this.processor = processor;
    }

    @Override
    public Pattern getPattern() {
        return this.pattern;
    }

    @Override
    public Processor getProcessor() {
        return this.processor;
    }

}
