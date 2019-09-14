package quiz.dao.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {

    private static final String PERSISTENCE_UNIT_NAME = "examplePersistenceUnit";
    protected static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}

