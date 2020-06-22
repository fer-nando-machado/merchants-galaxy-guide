package br.com.fernandomachado.galaxy.model.numeral;

import br.com.fernandomachado.galaxy.model.numeral.roman.RomanNumeralFactory;

/**
 * Abstract Numeral factory.
 */
public abstract class NumeralFactory {

    /**
     * Returns a canonical concrete implementation of {@link NumeralFactory}.
     *
     * @return {@link NumeralFactory}
     */
    public static NumeralFactory getNumeralFactory() {
        return new RomanNumeralFactory();
    }

    public abstract SymbolEnum toSymbol(Character character);
    public abstract SymbolEnum toSymbol(Integer decimal);
    public abstract Numeral toNumeral(String numeral);
    public abstract Integer toDecimal(String numeral);

}
