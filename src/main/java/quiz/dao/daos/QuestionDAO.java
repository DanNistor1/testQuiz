package quiz.dao.daos;

import quiz.dao.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class QuestionDAO {

    public void create(Question question) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(question);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Question read(Integer id) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Question question = entityManager.find(Question.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return question;
    }

    public void update(Question existingQuestion, Question newQuestion) throws EntityNotFoundException {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Question existing = entityManager.find(Question.class, existingQuestion.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Entitatea nu exista.");
        }
        existing.setText(newQuestion.getText());
        existing.setQuestionType(newQuestion.getQuestionType());
        existing.setQuestionDifficulty(newQuestion.getQuestionDifficulty());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Question question) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Question existingQuestion = entityManager.find(Question.class, question.getId());
        if (existingQuestion != null) {
            entityManager.remove(existingQuestion);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
