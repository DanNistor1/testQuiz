package quiz.dao.daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.entity.Answer;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.AnswerValue;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class TestCategoryDAO {

    Answer answer = new Answer();
    Question question = new Question();
    Category category = new Category();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from test").executeUpdate();
        entityManager.createNativeQuery("delete from category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Before
    public void createObject() {
        answer.setText("raspuns1");
        answer.setValue(AnswerValue.TRUE);

        question.setText("text1");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.addAnswer(answer);

        category.setName("cat1");
        category.addQuestion(question);
    }

    @Test
    public void testCreate() {

        // test this
        categoryDAO.create(category);

        // verify insert category object
        Category insertedCategory = categoryDAO.read(category.getId());
        Assert.assertNotNull(insertedCategory);
    }

    @Test
    public void readTest() {

        categoryDAO.create(category);

        // test this
        Category readedCategory = categoryDAO.read(category.getId());

        // verify
        Assert.assertNotNull(readedCategory);
    }

    @Test
    public void testUpdate() {
        Category newCategory = new Category();
        newCategory.setName("cat2");

        categoryDAO.create(category);

        // test this
        Category existing = categoryDAO.read(category.getId());
        categoryDAO.update(existing, newCategory);

        // verify
        Category updatedCategory = categoryDAO.read(existing.getId());
        Assert.assertEquals(newCategory.getName(), updatedCategory.getName());
    }

    @Test
    public void testDelete() {

        categoryDAO.create(category);

        // test this
        Category inserted = categoryDAO.read(category.getId());
        Assert.assertNotNull(inserted);
        categoryDAO.delete(inserted);

        // verify delete category object
        Category existing = categoryDAO.read(category.getId());
        Assert.assertNull(existing);
    }
}
