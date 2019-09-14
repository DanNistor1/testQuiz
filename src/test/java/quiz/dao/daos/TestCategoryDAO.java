package quiz.dao.daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class TestCategoryDAO {

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCreate() {
        // verify question table before is empty
        Assert.assertTrue(EMF.createEntityManager().createNativeQuery("select * from " + "question").getResultList().isEmpty());

        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("cat1");

        Question question1 = new Question();
        question1.setText("text1");
        question1.setQuestionType(QuestionType.OPEN);
        question1.setQuestionDifficulty(QuestionDifficulty.HIGH);

        category.addQuestion(question1);

        // test this
        categoryDAO.create(category);

        // verify insert category object
        Category insertedCategory = categoryDAO.read(category.getId());
        Assert.assertNotNull(insertedCategory);
        // verify question table after is not empty
        Assert.assertFalse(EMF.createEntityManager().createNativeQuery("select * from " + "question").getResultList().isEmpty());

    }

    @Test
    public void readTest() {

        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("cat2");

        Question question1 = new Question();
        question1.setText("text2");
        question1.setQuestionType(QuestionType.OPEN);
        question1.setQuestionDifficulty(QuestionDifficulty.HIGH);
        category.addQuestion(question1);

        categoryDAO.create(category);

        // test this
        Category readedCategory = categoryDAO.read(category.getId());

        // verify
        Assert.assertNotNull(readedCategory);

    }

    @Test
    public void testUpdate() {

        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("cat3");

        Question question1 = new Question();
        question1.setText("text3");
        question1.setQuestionType(QuestionType.OPEN);
        question1.setQuestionDifficulty(QuestionDifficulty.HIGH);
        category.addQuestion(question1);

        categoryDAO.create(category);

        // test this
        Category category1 = categoryDAO.read(category.getId());
        category1.setName("yyy");
        categoryDAO.update(category1);

        // verify
        Category updatedCategory = categoryDAO.read(category.getId());
        Assert.assertEquals("yyy", updatedCategory.getName());

    }

    @Test
    public void testDelete() {

        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("cat4");

        Question question1 = new Question();
        question1.setText("text4");
        question1.setQuestionType(QuestionType.OPEN);
        question1.setQuestionDifficulty(QuestionDifficulty.HIGH);
        category.addQuestion(question1);

        categoryDAO.create(category);

        // verify question table before is not empty
        Assert.assertFalse(EMF.createEntityManager().createNativeQuery("select * from " + "question").getResultList().isEmpty());

        // test this
        Category insertedCategory = categoryDAO.read(category.getId());
        Assert.assertNotNull(insertedCategory);
        categoryDAO.delete(insertedCategory);

        // verify delete category object
        Category existingCategory = categoryDAO.read(category.getId());
        Assert.assertNull(existingCategory);
        // verify question table after is empty
        Assert.assertTrue(EMF.createEntityManager().createNativeQuery("select * from " + "question").getResultList().isEmpty());

    }
}
