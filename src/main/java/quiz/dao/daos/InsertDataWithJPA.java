package quiz.dao.daos;

import quiz.dao.entity.Answer;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.AnswerValue;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class InsertDataWithJPA {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Category category1 = new Category("cat1");
        Category category2 = new Category("cat2");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.create(category1);
        categoryDAO.create(category2);

        Question question1 = new Question("question1", QuestionType.OPEN, QuestionDifficulty.LOW, category1);
        Question question2 = new Question("question2", QuestionType.OPEN, QuestionDifficulty.MEDIUM, category1);
        Question question3 = new Question("question3", QuestionType.OPEN, QuestionDifficulty.HIGH, category1);
        Question question4 = new Question("question4", QuestionType.CHOICE, QuestionDifficulty.LOW, category2);
        Question question5 = new Question("question5", QuestionType.CHOICE, QuestionDifficulty.MEDIUM, category2);
        Question question6 = new Question("question6", QuestionType.CHOICE, QuestionDifficulty.HIGH, category2);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.create(question1);
        questionDAO.create(question2);
        questionDAO.create(question3);
        questionDAO.create(question4);
        questionDAO.create(question5);
        questionDAO.create(question6);

        Answer answer1 = new Answer("answer1", AnswerValue.TRUE, question1);
        Answer answer2 = new Answer("answer2", AnswerValue.TRUE, question2);
        Answer answer3 = new Answer("answer3", AnswerValue.TRUE, question3);
        Answer answer4 = new Answer("answer4", AnswerValue.TRUE, question4);
        Answer answer5 = new Answer("answer5", AnswerValue.TRUE, question5);
        Answer answer6 = new Answer("answer6", AnswerValue.TRUE, question6);
        AnswerDAO answerDAO = new AnswerDAO();
        answerDAO.create(answer1);
        answerDAO.create(answer2);
        answerDAO.create(answer3);
        answerDAO.create(answer4);
        answerDAO.create(answer5);
        answerDAO.create(answer6);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
