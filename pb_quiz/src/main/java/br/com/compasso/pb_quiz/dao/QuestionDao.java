package br.com.compasso.pb_quiz.dao;

import br.com.compasso.pb_quiz.model.Question;

import javax.persistence.EntityManager;
import java.util.List;

public class QuestionDao {

    private EntityManager em;

    public QuestionDao(EntityManager em) {
        this.em = em;
    }

    public void addQuestion(Question question) {
        this.em.persist(question);
    }

    public void updateQuestion(Question question) {
        this.em.merge(question);
    }

    public void removeQuestion(Question question) {
        question = this.em.merge(question);
        this.em.remove(question);
    }

    public Question searchById(int id) {
        return em.find(Question.class, id);
    }

    public List<Question> getAll() {
        String jpql = "SELECT q FROM Question q";
        return em.createQuery(jpql, Question.class).getResultList();
    }

    public List<Question> getActives() {
        String jpql = "SELECT q FROM Question q WHERE q.isActive = true";
        return em.createQuery(jpql, Question.class).getResultList();
    }


}
