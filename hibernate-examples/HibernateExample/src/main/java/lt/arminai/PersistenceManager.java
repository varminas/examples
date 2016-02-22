package lt.arminai;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Vytautas on 2015-11-22.
 */
public enum  PersistenceManager {
    INSTANCE;

    private EntityManagerFactory factory;

    private PersistenceManager() {
        factory = Persistence.createEntityManagerFactory("hibernatePU");
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public void close() {
        factory.close();
    }
}
