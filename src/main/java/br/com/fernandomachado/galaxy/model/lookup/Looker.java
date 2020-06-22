package br.com.fernandomachado.galaxy.model.lookup;

/**
 * Defines how to match an object and a {@link LookupEnum}.
 */
public interface Looker<T, E extends LookupEnum> {

    /**
     * Returns true in case the needle object matches the {@link LookupEnum} candidate.
     *
     * @param needle
     * @param candidate
     * @return boolean
     */
    boolean matches(T needle, E candidate);
}
