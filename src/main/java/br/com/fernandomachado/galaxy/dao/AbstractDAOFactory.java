package br.com.fernandomachado.galaxy.dao;

import br.com.fernandomachado.galaxy.dao.singleton.SingletonDAOFactory;

/**
 * Abstract DAO Factory.
 */
public abstract class AbstractDAOFactory {

    /**
     * Returns a canonical concrete implementation of {@link AbstractDAOFactory}.
     *
     * @return {@link AbstractDAOFactory}.
     */
    public static AbstractDAOFactory getDAOFactory() {
        return new SingletonDAOFactory();
    }

    // DAOs that must be implemented by all concrete implementations of this abstract factory.
    public abstract MaterialDAO getMaterialDAO();
    public abstract TranslationDAO getTranslationDAO();
}
