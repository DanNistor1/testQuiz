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

public class TestAnswerDAO {

    Answer answer = new Answer();
    Question question = new Question();
    Category category = new Category();
    CategoryDAO categoryDAO = new CategoryDAO();
    QuestionDAO questionDAO = new QuestionDAO();
    AnswerDAO answerDAO = new AnswerDAO();

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.createNativeQuery("delete from answer").executeUpdate(); // is not necessary
//        entityManager.createNativeQuery("delete from question").executeUpdate(); // is not necessary
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

        categoryDAO.create(category);
        questionDAO.create(question);

        // test this
        answerDAO.create(answer);

        // verify insert answer object
        Answer insertedAnswer = answerDAO.read(answer.getId());
        Assert.assertNotNull(insertedAnswer);
    }

    @Test
    public void readTest() {

        categoryDAO.create(category);
        questionDAO.create(question);
        answerDAO.create(answer);

        // test this
        Answer readedAnswer = answerDAO.read(answer.getId());

        // verify
        Assert.assertNotNull(readedAnswer);
    }

    @Test
    public void testUpdate() {
        Answer newAnswer = new Answer();
        newAnswer.setText("text2");
        newAnswer.setValue(AnswerValue.FALSE);

        categoryDAO.create(category);
        questionDAO.create(question);
        answerDAO.create(answer);

        // test this
        Answer existing = answerDAO.read(answer.getId());
        answerDAO.update(existing, newAnswer);

        // verify
        Answer updatedAnswer = answerDAO.read(existing.getId());
        Assert.assertEquals(newAnswer.getText(), updatedAnswer.getText());
        Assert.assertEquals(newAnswer.getValue(), updatedAnswer.getValue());
}

    @Test
    public void testDelete() {

        categoryDAO.create(category);
        questionDAO.create(question);
        answerDAO.create(answer);

        // test this
        Answer inserted = answerDAO.read(answer.getId());
        Assert.assertNotNull(inserted);
        answerDAO.delete(inserted);

        // verify delete answer
        Answer existing = answerDAO.read(answer.getId());
        Assert.assertNull(existing);
    }
}
