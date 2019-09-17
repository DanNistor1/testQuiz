package quiz.dao.daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.entity.Answer;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.entity.TestSet;
import quiz.dao.enums.AnswerValue;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class TestTestSetDAO {

    Answer answer = new Answer();
    Question question = new Question();
    Category category = new Category();
    TestSet testSet = new TestSet();
    CategoryDAO categoryDAO = new CategoryDAO();
    QuestionDAO questionDAO = new QuestionDAO();
    TestDAO testDAO = new TestDAO();
    AnswerDAO answerDAO = new AnswerDAO();

    @Before
    public void cleanup() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
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

        testSet.setDate("2019-09-17");
        testSet.addQuestion(question);

        category.setName("cat1");
        category.addQuestion(question);
    }

    @Test
    public void testCreate() {

        categoryDAO.create(category);
        questionDAO.create(question);

        // test this
        testDAO.create(testSet);
        // verify insert question object
        TestSet insertedTestSet = testDAO.read(testSet.getId());
        Assert.assertNotNull(insertedTestSet);
    }

    @Test
    public void readTest() {

        categoryDAO.create(category);
        questionDAO.create(question);
        testDAO.create(testSet);

        // test this
        TestSet readedTestSet = testDAO.read(testSet.getId());

        // verify
        Assert.assertNotNull(readedTestSet);
    }

    @Test
    public void testUpdate() {
        TestSet newTestSet = new TestSet();
        newTestSet.setDate("2000-09-17");

        categoryDAO.create(category);
        questionDAO.create(question);
        testDAO.create(testSet);

        // test this
        TestSet existing = testDAO.read(testSet.getId());
        testDAO.update(existing, newTestSet);

        // verify
        TestSet updatedTestSet = testDAO.read(existing.getId());
        Assert.assertEquals(newTestSet.getDate(), updatedTestSet.getDate());
    }

    @Test
    public void testDelete() {

        categoryDAO.create(category);
        questionDAO.create(question);
        testDAO.create(testSet);

        // test this
        TestSet inserted = testDAO.read(testSet.getId());
        Assert.assertNotNull(inserted);
        testDAO.delete(inserted);

        // verify delete question
        TestSet existing = testDAO.read(testSet.getId());
        Assert.assertNull(existing);
    }
}
