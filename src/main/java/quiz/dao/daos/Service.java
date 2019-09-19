package quiz.dao.daos;

import quiz.dao.entity.Question;
import quiz.dao.entity.TestSet;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

public class Service {
    public static void main(String[] args) {

        List resultList = getList();
        setOfQuestions(resultList);

        print();

    }

    public static void print() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        //  List<String> result1 = entityManager.createQuery("SELECT DISTINCT q.text FROM Question q, IN (q.tests) t").getResultList(); // fara JOIN
        List<String> result1 = entityManager.createQuery("SELECT q.text FROM Question q RIGHT JOIN q.tests t").getResultList(); // cu JOIN
        TestSet result2 = (TestSet) entityManager.createQuery("SELECT t FROM TestSet t").getSingleResult();
        System.out.println("Testul cu id " + result2.getId() + " din data de " + result2.getDate() + " contine intrebarile:");
        for (String str : result1) {
            System.out.println(str);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void setOfQuestions(List resultList) {
        TestSet testSet = new TestSet();
        for (int i = 1; i <= 3; i++) {
            Question question;
            do {
                question = randomQuestion(resultList);
            } while (testSet.getQuestions().contains(question));
            testSet.addQuestion(question);
        }
        TestDAO testDAO = new TestDAO();
        testDAO.create(testSet);
    }

    public static List getList() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();

        List<Question> resultList = (List<Question>) entityManager.createQuery("SELECT q FROM Question q").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    public static Question randomQuestion(List<Question> questionList) {
        Random random = new Random();
        int n = random.nextInt(questionList.size());
        Question question = questionList.get(n);
        return question;
    }

}
