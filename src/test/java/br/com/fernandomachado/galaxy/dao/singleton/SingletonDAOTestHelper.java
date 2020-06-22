package br.com.fernandomachado.galaxy.dao.singleton;

import org.junit.Before;

import java.lang.reflect.Field;

/**
 * Helper class used to reset the singleton DAOs.
 */
public abstract class SingletonDAOTestHelper {

    @Before
    public void reset() throws NoSuchFieldException, IllegalAccessException {
        this.reset(MaterialSingletonDAO.class);
        this.reset(TranslationSingletonDAO.class);
    }

    private void reset(Class clazz) throws NoSuchFieldException, IllegalAccessException {
        Field instance = clazz.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

}
