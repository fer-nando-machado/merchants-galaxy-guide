package br.com.fernandomachado.galaxy.model.numeral.roman;

import br.com.fernandomachado.galaxy.model.numeral.Numeral;

import java.util.Objects;

/**
 * A {@link Numeral} in the Roman numeric system.
 */
public class RomanNumeral implements Numeral {

    private String numeral;
    private Integer decimal;

    private RomanNumeral(String numeral, Integer decimal) {
        this.numeral = numeral;
        this.decimal = decimal;
    }

    public RomanNumeral(String numeral) {
        this(numeral, RomanNumeralHelper.toDecimal(numeral));
    }

    public RomanNumeral(Integer decimal) {
        this(RomanNumeralHelper.toNumeral(decimal), decimal);
    }

    @Override
    public String getNumeral() {
        return this.numeral;
    }

    @Override
    public Integer getDecimal() {
        return this.decimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RomanNumeral)) return false;
        RomanNumeral that = (RomanNumeral) o;
        return Objects.equals(decimal, that.decimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decimal);
    }
}
