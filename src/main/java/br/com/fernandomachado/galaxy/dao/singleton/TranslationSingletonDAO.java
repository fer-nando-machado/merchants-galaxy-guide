package br.com.fernandomachado.galaxy.dao.singleton;

import br.com.fernandomachado.galaxy.dao.TranslationDAO;
import br.com.fernandomachado.galaxy.model.Translation;
import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link TranslationDAO}.
 * Singleton class storing {@link Translation} data on a {@link Map}.
 */
public class TranslationSingletonDAO implements TranslationDAO {

    private static TranslationSingletonDAO instance;

    private Map<String, SymbolEnum> map;

    TranslationSingletonDAO() {
        this.map = new HashMap<>();
    }

    static TranslationSingletonDAO getInstance() {
        if (instance == null) {
            instance = new TranslationSingletonDAO();
        }
        return instance;
    }

    @Override
    public void save(Translation translation) {
        this.map.put(translation.getWord(), translation.getSymbol());
    }

    @Override
    public SymbolEnum findSymbol(String word) {
        return this.map.get(word);
    }

    @Override
    public List<SymbolEnum> findSymbols(List<String> words) {
        List<SymbolEnum> symbols = new ArrayList<>();
        for (String word : words) {
            SymbolEnum symbol = this.findSymbol(word);
            if (symbol != null) {
                symbols.add(symbol);
            }
        }
        return symbols;
    }


}
