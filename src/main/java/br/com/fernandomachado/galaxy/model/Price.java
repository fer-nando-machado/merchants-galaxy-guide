package br.com.fernandomachado.galaxy.model;

import java.util.Objects;

/**
 * Quantity given by one party to another in exchange for goods.
 */
public class Price {

    private Currency currency;
    private Double units;

    public Price(Currency currency, Double units) {
        this.currency = currency;
        this.units = units;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return Objects.equals(currency, price.currency) &&
                Objects.equals(units, price.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, units);
    }

}
