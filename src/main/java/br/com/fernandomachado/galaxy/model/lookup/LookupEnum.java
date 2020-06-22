package br.com.fernandomachado.galaxy.model.lookup;

/**
 * Interface for {@link Enum}s that can be looked up by their attributes using {@link Looker}.
 */
public interface LookupEnum {

    /**
     * Compares the needle element with each element on the haystack array by using the {@link Looker} implementation.
     * Once it finds a match, returns the matched element.
     * Throws an {@link IllegalArgumentException} in case a match can't be found.
     *
     * @param needle
     * @param haystack
     * @param looker
     * @return element
     */
    static <T, E> E lookup(T needle, E[] haystack, Looker<T, ? super E> looker) {
        for (E candidate : haystack) {
            if (looker.matches(needle, candidate)) {
                return candidate;
            }
        }
        throw new LookupException("Unable to find a match for " + needle.toString() + ".");
    }

}
