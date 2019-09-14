package quiz.dao.daos;

import quiz.dao.entity.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class AnswerDAO {

    public void create(Answer answer) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(answer);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Answer read(Integer id) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Answer answer = entityManager.find(Answer.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return answer;
    }

    public void update(Answer existingAnswer, Answer newAnswer) throws EntityNotFoundException {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Answer existing = entityManager.find(Answer.class, existingAnswer.getId());
        if (existing == null) {
            throw new EntityNotFoundException("Entitatea nu exista.");
        }
        existing.setText(newAnswer.getText());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Answer answer) {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        Answer existingAnswer = entityManager.find(Answer.class, answer.getId());
        if (existingAnswer != null) {
            entityManager.remove(existingAnswer);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
