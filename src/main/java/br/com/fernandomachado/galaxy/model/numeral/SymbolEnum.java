package br.com.fernandomachado.galaxy.model.numeral;

import br.com.fernandomachado.galaxy.model.lookup.LookupEnum;
import br.com.fernandomachado.galaxy.model.lookup.Looker;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface for {@link Enum}s representing symbols used in {@link Numeral}s.
 */
public interface SymbolEnum extends LookupEnum {

    /**
     * Given a {@link Character} and an array of {@link SymbolEnum}s, returns the matching {@link SymbolEnum}.
     *
     * @param character
     * @param symbols
     * @return {@link SymbolEnum}
     */
    static SymbolEnum toEnum(Character character, SymbolEnum[] symbols) {
        return LookupEnum.lookup(character, symbols,
                (Looker<Character, SymbolEnum>) (needle, candidate) -> needle.equals(candidate.getCharacter()));
    }

    /**
     * Given a {@link Integer} and an array of {@link SymbolEnum}s, returns the matching {@link SymbolEnum}.
     *
     * @param decimal
     * @param symbols
     * @return {@link SymbolEnum}
     */
    static SymbolEnum toEnum(Integer decimal, SymbolEnum[] symbols) {
        return LookupEnum.lookup(decimal, symbols,
                (Looker<Integer, SymbolEnum>) (needle, candidate) -> needle.equals(candidate.getDecimal()));
    }

    /**
     * Given an array of {@link SymbolEnum}s, returns a map associating its {@link Character}s and {@link Integer}s.
     *
     * @param symbols
     * @return {@link Map}
     */
    static Map<Character, Integer> buildCharacterDecimalMap(SymbolEnum[] symbols) {
        Map<Character, Integer> map = new HashMap<>();
        for (SymbolEnum symbol : symbols) {
            map.put(symbol.getCharacter(), symbol.getDecimal());
        }
        return map;
    }

    /**
     * Given an array of {@link SymbolEnum}s, returns a map associating its {@link Integer}s and {@link Character}s.
     *
     * @param symbols
     * @return {@link Map}
     */
    static Map<Integer, Character> buildDecimalCharacterMap(SymbolEnum[] symbols) {
        Map<Integer, Character> map = new HashMap<>();
        for (SymbolEnum symbol : symbols) {
            map.put(symbol.getDecimal(), symbol.getCharacter());
        }
        return map;
    }

    Character getCharacter();

    Integer getDecimal();

}
