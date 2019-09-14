package quiz.dao.daos;

import quiz.dao.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class CategoryDAO {

    public void create(Category category) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(category);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category read(Integer id) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Category category = entityManager.find(Category.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return category;
    }

    public void update(Category category) throws EntityNotFoundException {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Category existing = entityManager.find(Category.class, category.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Entitatea nu exista.");
        }
        existing.setName(category.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Category category) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Category existingCategory = entityManager.find(Category.class, category.getId());
        if (existingCategory != null) {
            entityManager.remove(existingCategory);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
