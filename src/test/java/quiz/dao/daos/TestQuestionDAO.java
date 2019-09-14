package quiz.dao.daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class TestQuestionDAO {

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from question").executeUpdate(); // is not necessary
        entityManager.createNativeQuery("delete from category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCreate() {

        Category category = new Category();
        category.setName("cat1");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.create(category);

        Question question1 = new Question();
        question1.setText("text1");
        question1.setQuestionType(QuestionType.OPEN);
        question1.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question1.setCategory(category);

        QuestionDAO questionDAO = new QuestionDAO();

        // test this
        questionDAO.create(question1);

        // verify insert question object
        Question insertedQuestion1 = questionDAO.read(question1.getId());
        Assert.assertNotNull(insertedQuestion1);

    }

    @Test
    public void readTest() {

        Category category = new Category();
        category.setName("cat2");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.create(category);

        Question question = new Question();
        question.setText("text3");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.setCategory(category);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.create(question);

        // test this
        Question readedQuestion = questionDAO.read(question.getId());

        // verify
        Assert.assertNotNull(readedQuestion);

    }

    @Test
    public void testUpdate() {

        Category category = new Category();
        category.setName("cat3");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.create(category);

        Question existingQuestion = new Question();
        existingQuestion.setText("text4");
        existingQuestion.setQuestionType(QuestionType.OPEN);
        existingQuestion.setQuestionDifficulty(QuestionDifficulty.HIGH);
        existingQuestion.setCategory(category);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.create(existingQuestion);

        Question newQuestion = new Question();
        newQuestion.setText("text5");
        newQuestion.setQuestionType(QuestionType.CHOICE);
        newQuestion.setQuestionDifficulty(QuestionDifficulty.LOW);
        newQuestion.setCategory(category);

        // test this
        Question existing = questionDAO.read(existingQuestion.getId());
        questionDAO.update(existing, newQuestion);

        // verify
        Question updatedQuestion = questionDAO.read(existingQuestion.getId());
        Assert.assertEquals(updatedQuestion.getText(), newQuestion.getText());
        Assert.assertEquals(updatedQuestion.getQuestionType(), newQuestion.getQuestionType());
        Assert.assertEquals(updatedQuestion.getQuestionDifficulty(), newQuestion.getQuestionDifficulty());

    }

    @Test
    public void testDelete() {

        Category category = new Category();
        category.setName("cat4");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.create(category);

        Question question = new Question();
        question.setText("text6");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.setCategory(category);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.create(question);

        // test this
        Question insertedQuestion = questionDAO.read(question.getId());
        Assert.assertNotNull(insertedQuestion);
        questionDAO.delete(insertedQuestion);

        // verify delete question
        Question existingQuestion = questionDAO.read(question.getId());
        Assert.assertNull(existingQuestion);
        // verify if exist category
        Assert.assertFalse(EMF.createEntityManager().createNativeQuery("select * from " + "category").getResultList().isEmpty());

    }
}
