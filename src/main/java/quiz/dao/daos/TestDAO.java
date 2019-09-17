package quiz.dao.daos;

import quiz.dao.entity.TestSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class TestDAO {

    public void create(TestSet test) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(test);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public TestSet read(Integer id) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        TestSet test = entityManager.find(TestSet.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return test;
    }

    public void update(TestSet existingTestSet, TestSet newTestSet) throws EntityNotFoundException {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        TestSet existing = entityManager.find(TestSet.class, existingTestSet.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Entitatea nu exista.");
        }
        existing.setDate(newTestSet.getDate());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(TestSet test) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        TestSet existingTestSet = entityManager.find(TestSet.class, test.getId());
        if (existingTestSet != null) {
            entityManager.remove(existingTestSet);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
