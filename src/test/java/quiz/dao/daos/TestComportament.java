package quiz.dao.daos;

import org.junit.Test;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestComportament {
    @Test
    public void ashghj() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category1 = entityManager.find(Category.class,191);
        Question question = category1.getQuestions().get(0);
        category1.removeQuestion(question);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
