package br.com.fernandomachado.galaxy.model.numeral.roman;

import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

/**
 * Symbols used in the {@link RomanNumeral}s.
 */
public enum RomanSymbol implements SymbolEnum {
    M('M', 1000),
    D('D', 500),
    C('C', 100),
    L('L', 50),
    X('X', 10),
    V('V', 5),
    I('I', 1);

    private char character;
    private int decimal;

    RomanSymbol(char c, int i) {
        this.character = c;
        this.decimal = i;
    }

    @Override
    public Character getCharacter() {
        return this.character;
    }

    @Override
    public Integer getDecimal() {
        return this.decimal;
    }
}