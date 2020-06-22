package br.com.fernandomachado.galaxy.dao;

import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

import java.util.List;

/**
 * Manages {@link Translation} entities.
 */
public interface TranslationDAO {

    /**
     * Creates or updates a {@link Translation} entity.
     *
     * @param translation
     */
    void save(Translation translation);

    /**
     * Given a {@link String} word, returns its corresponding {@link SymbolEnum} translation.
     * Returns null if a match is not found.
     *
     * @param word
     * @return {@link SymbolEnum} translation.
     */
    SymbolEnum findSymbol(String word);

    /**
     * Given a {@link List<String>} of words, returns the corresponding {@link SymbolEnum} translation for each word.
     *
     * @param words
     * @return {@link List<SymbolEnum>} containing translations.
     */
    List<SymbolEnum> findSymbols(List<String> words);


}
