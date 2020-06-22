package br.com.fernandomachado.galaxy.model.numeral.roman;

import br.com.fernandomachado.galaxy.model.numeral.Numeral;
import br.com.fernandomachado.galaxy.model.numeral.NumeralFactory;
import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

/**
 * Implementation of {@link NumeralFactory} for {@link RomanNumeral}s.
 */
public class RomanNumeralFactory extends NumeralFactory {

    @Override
    public SymbolEnum toSymbol(Character character) {
        return SymbolEnum.toEnum(character, RomanSymbol.values());
    }

    @Override
    public SymbolEnum toSymbol(Integer decimal) {
        return SymbolEnum.toEnum(decimal, RomanSymbol.values());
    }

    @Override
    public Numeral toNumeral(String numeral) {
        return new RomanNumeral(numeral);
    }

    @Override
    public Integer toDecimal(String numeral) {
        return this.toNumeral(numeral).getDecimal();
    }

}
