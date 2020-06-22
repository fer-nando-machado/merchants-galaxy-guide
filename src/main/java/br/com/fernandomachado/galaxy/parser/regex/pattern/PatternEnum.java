package br.com.fernandomachado.galaxy.parser.regex.pattern;

import br.com.fernandomachado.galaxy.model.lookup.LookupEnum;
import br.com.fernandomachado.galaxy.model.lookup.Looker;

import java.util.regex.Pattern;

/**
 * Interface for {@link Enum}s that are associated with a regex {@link Pattern}.
 */
public interface PatternEnum extends LookupEnum {

    /**
     * Given a {@link String} and an array of {@link PatternEnum}s, returns the matching {@link PatternEnum}.
     *
     * @param string
     * @param patterns
     * @return {@link PatternEnum}
     */
    static PatternEnum toEnum(String string, PatternEnum[] patterns) {
        return LookupEnum.lookup(string, patterns,
                (Looker<String, PatternEnum>) (needle, candidate) -> candidate.getPattern().matcher(needle).matches());
    }

    Pattern getPattern();


}
