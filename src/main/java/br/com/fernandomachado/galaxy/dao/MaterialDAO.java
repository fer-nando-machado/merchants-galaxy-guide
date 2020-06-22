package br.com.fernandomachado.galaxy.dao;

import br.com.fernandomachado.galaxy.model.Currency;
import br.com.fernandomachado.galaxy.model.Material;

/**
 * Manages {@link Material} entities.
 */
public interface MaterialDAO {

    /**
     * Creates or updates a {@link Material} entity.
     *
     * @param material
     */
    void save(Material material);

    /**
     * Given a {@link String} name, returns its corresponding {@link Material}.
     * Returns null if a match is not found.
     *
     * @param name
     * @return {@link Material}
     */
    Material find(String name);

    /**
     * Given {@link String}s representing a {@link Material} name and a {@link Currency} name,
     * returns its corresponding {@link Double} price or null if a match is not found.
     *
     * @param name
     * @param currency
     * @return {@link Double} price
     */
    Double findPrice(String name, String currency);

}
