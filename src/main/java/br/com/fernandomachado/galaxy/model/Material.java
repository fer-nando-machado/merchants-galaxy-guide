package br.com.fernandomachado.galaxy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An element than can be commercialized over the galaxy.
 */
public class Material {

    private String name;
    private List<Price> prices;

    public Material(String name) {
        this.name = name;
        this.prices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void addPrice(Price price) {
        this.prices.add(price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return Objects.equals(name, material.name) &&
                Objects.equals(prices, material.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices);
    }

}
