package br.com.compasso.pb_quiz.dao;

import br.com.compasso.pb_quiz.model.Question;

import javax.persistence.EntityManager;
import java.util.List;

public class QuestionDao {

    private EntityManager em;

    public QuestionDao(EntityManager em) {
        this.em = em;
    }

    public List<Question> getActives() {
        String jpql = "SELECT q FROM Question q WHERE q.isActive = true";
        return em.createQuery(jpql, Question.class).getResultList();
    }


}
