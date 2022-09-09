package br.com.compasso.pb_quiz.dao;

import br.com.compasso.pb_quiz.model.Result;

import javax.persistence.EntityManager;
import java.util.List;

public class ResultDao {

    private EntityManager em;

    public ResultDao(EntityManager em) {
        this.em = em;
    }

    public void addResult(Result result) {
        this.em.persist(result);
    }

    public List<Result> getAll() {
        String jpql = "SELECT r FROM Result r ORDER BY r.hits DESC";
        return em.createQuery(jpql, Result.class).getResultList();
    }

}
