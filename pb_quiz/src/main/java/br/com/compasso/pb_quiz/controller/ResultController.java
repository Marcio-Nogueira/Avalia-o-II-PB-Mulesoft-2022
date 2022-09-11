package br.com.compasso.pb_quiz.controller;

import br.com.compasso.pb_quiz.dao.QuestionDao;
import br.com.compasso.pb_quiz.dao.ResultDao;
import br.com.compasso.pb_quiz.model.Question;
import br.com.compasso.pb_quiz.model.Result;
import br.com.compasso.pb_quiz.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class ResultController {

    protected static void play(Scanner scanner) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); //collection that turns off log from hibernate
        EntityManager em = JPAUtil.getEntityManager();
        QuestionDao questionDao = new QuestionDao(em);
        Result result = new Result();
        ResultDao resultDao = new ResultDao(em);
        em.getTransaction().begin();

        List<Question> questions = questionDao.getActives();
        try {
            if (questions.size() < 1) {
                throw new NoDataException("Ainda não foram adcionadas nenhuma questão a base de dados, por favor adcione" +
                        " questões ou contate o administrador.");
            }
        } catch (NoDataException ex) {
            String msg = ex.getMessage();
            System.out.println(msg);
            System.exit(0);
        }

        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        result.setPlayer(name);

        System.out.println("Responda com verdadeiro ou falso as questões abaixo");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Questão " + (i+1) + ": " + questions.get(i).getQuestion());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("verdadeiro") && questions.get(i).isAnswer()) {
                result.setHits();
            } else if (answer.equalsIgnoreCase("falso") && !questions.get(i).isAnswer()) {
                result.setHits();
            } else if (answer.equalsIgnoreCase("verdadeiro") || answer.equalsIgnoreCase("falso")) {
                result.setMisses();
            } else {
                System.out.println("Resposta não aceita, Digite verdadeiro ou falso");
                i--;
            }
        }

        System.out.println("Você acertou " + result.getHits() + " questões");
        resultDao.addResult(result);
        em.getTransaction().commit();
        em.close();
    }

    protected static void showScore() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); //collection that turns off log from hibernate
        EntityManager em = JPAUtil.getEntityManager();
        ResultDao resultDao = new ResultDao(em);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Result> all = resultDao.getAll();
        try {
            if (all.size() < 1) {
                throw new NoDataException("Ainda não há dados no placar, por favor jogue uma partida e depois volte aqui pra conferir.");
            }
        } catch (NoDataException ex) {
            String msg = ex.getMessage();
            System.out.println(msg);
        }

        String leftAlignFormat = " %-25s %-8s %-4d %-6s %-12s %n";
        all.forEach(r -> System.out.format(leftAlignFormat,"JOGADOR: " + r.getPlayer(),
                "ACERTOS: ", r.getHits(), "\tDATA: ", r.getMatchDate().format(formatter).toString()
        ));

        em.close();
    }
}
