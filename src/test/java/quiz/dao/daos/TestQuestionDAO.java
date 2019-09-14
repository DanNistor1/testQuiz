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
//        entityManager.createNativeQuery("delete from question").executeUpdate(); // is not necessary
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

        Question question2 = new Question();
        question2.setText("text2");
        question2.setQuestionType(QuestionType.OPEN);
        question2.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question2.setCategory(category);

        QuestionDAO questionDAO = new QuestionDAO();
        // test this
        questionDAO.create(question1);
        questionDAO.create(question2);

        // verify insert question object
        Question insertedQuestion1 = questionDAO.read(question1.getId());
        Assert.assertNotNull(insertedQuestion1);
        Question insertedQuestion2 = questionDAO.read(question2.getId());
        Assert.assertNotNull(insertedQuestion2);
        // verify if is synchronized List<Question>
        Assert.assertEquals(2, categoryDAO.read(category.getId()).getQuestions().size());
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

        Question question = new Question();
        question.setText("text4");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.setCategory(category);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.create(question);

        // test this
        Question question1 = questionDAO.read(question.getId());
        question1.setText("text5");
        questionDAO.update(question1);

        // verify
        Question updatedQuestion = questionDAO.read(question.getId());
        Assert.assertEquals("text5", updatedQuestion.getText());

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
