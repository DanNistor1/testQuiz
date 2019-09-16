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

public class TestQuestionDAO {

    Answer answer = new Answer();
    Question question = new Question();
    Category category = new Category();
    CategoryDAO categoryDAO = new CategoryDAO();
    QuestionDAO questionDAO = new QuestionDAO();

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.createNativeQuery("delete from question").executeUpdate(); // is not necessary
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

        categoryDAO.create(category);

        // test this
        questionDAO.create(question);

        // verify insert question object
        Question insertedQuestion = questionDAO.read(question.getId());
        Assert.assertNotNull(insertedQuestion);
    }

    @Test
    public void readTest() {

        categoryDAO.create(category);
        questionDAO.create(question);

        // test this
        Question readedQuestion = questionDAO.read(question.getId());

        // verify
        Assert.assertNotNull(readedQuestion);
    }

    @Test
    public void testUpdate() {
        Question newQuestion = new Question();
        newQuestion.setText("text3");
        newQuestion.setQuestionType(QuestionType.CHOICE);
        newQuestion.setQuestionDifficulty(QuestionDifficulty.LOW);

        categoryDAO.create(category);
        questionDAO.create(question);

        // test this
        Question existing = questionDAO.read(question.getId());
        questionDAO.update(existing, newQuestion);

        // verify
        Question updatedQuestion = questionDAO.read(existing.getId());
        Assert.assertEquals(newQuestion.getText(), updatedQuestion.getText());
        Assert.assertEquals(newQuestion.getQuestionType(), updatedQuestion.getQuestionType());
        Assert.assertEquals(newQuestion.getQuestionDifficulty(), updatedQuestion.getQuestionDifficulty());
    }

    @Test
    public void testDelete() {

        categoryDAO.create(category);
        questionDAO.create(question);

        // test this
        Question inserted = questionDAO.read(question.getId());
        Assert.assertNotNull(inserted);
        questionDAO.delete(inserted);

        // verify delete question
        Question existing = questionDAO.read(question.getId());
        Assert.assertNull(existing);
    }
}
