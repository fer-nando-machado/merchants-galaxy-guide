package br.com.fernandomachado.galaxy.model;

import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

import java.util.Objects;

/**
 * Association between an alien word and an understandable {@link SymbolEnum}.
 */
public class Translation {

    private String word;
    private SymbolEnum symbol;

    public Translation(String word, SymbolEnum symbol) {
        this.word = word;
        this.symbol = symbol;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SymbolEnum getSymbol() {
        return symbol;
    }

    public void setSymbol(SymbolEnum symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Translation)) return false;
        Translation that = (Translation) o;
        return Objects.equals(word, that.word) &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, symbol);
    }

}
