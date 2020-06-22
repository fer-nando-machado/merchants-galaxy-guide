package br.com.fernandomachado.galaxy.dao.singleton;

import br.com.fernandomachado.galaxy.dao.AbstractDAOFactory;
import br.com.fernandomachado.galaxy.dao.MaterialDAO;
import br.com.fernandomachado.galaxy.dao.TranslationDAO;

/**
 * Implementation of {@link AbstractDAOFactory}.
 * Simulates data persistence by returning singleton DAO instances stored in memory.
 */
public class SingletonDAOFactory extends AbstractDAOFactory {

    public SingletonDAOFactory() {
        // empty constructor
    }

    @Override
    public MaterialDAO getMaterialDAO() {
        return MaterialSingletonDAO.getInstance();
    }

    @Override
    public TranslationDAO getTranslationDAO() {
        return TranslationSingletonDAO.getInstance();
    }
}
