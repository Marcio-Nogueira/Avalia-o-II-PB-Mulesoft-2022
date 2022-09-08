package br.com.compasso.pb_quiz.main;

import br.com.compasso.pb_quiz.dao.QuestionDao;
import br.com.compasso.pb_quiz.dao.ResultDao;
import br.com.compasso.pb_quiz.model.Question;
import br.com.compasso.pb_quiz.model.Result;
import br.com.compasso.pb_quiz.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean closeMenu = false;
         do {
             System.out.println("1 - Jogar novamente\n" +
                     "2 - Ver placar\n" +
                     "0 - Sair");
             int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Jogando");
                    play();
                    System.out.flush();
                    break;
                case 2:
                    System.out.println("vendo placar");
                    showScore();
                    break;
                case 0:
                    System.out.println("Saindo");
                    System.exit(0);
                default:
                    System.out.println("Digite uma opção válida");


            }
        }while (closeMenu == false);
    }

    private static void play() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Result result = new Result();
        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        result.setPlayer(name);

        QuestionDao questionDao = new QuestionDao(em);
        List<Question> questoes = questionDao.getActives();
        for (int i = 0; i < questoes.size(); i++) {
            System.out.println(questoes.get(i).getQuestion());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("verdadeiro")) {
                result.setHits();
            } else {
                result.setMisses();
            }
        }

        ResultDao resultDao = new ResultDao(em);
        System.out.println(result.getMisses());
        resultDao.addResult(result);
        em.getTransaction().commit();
        em.close();
    }

    private static void showScore() {
        EntityManager em = JPAUtil.getEntityManager();
        ResultDao resultDao = new ResultDao(em);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Result> all = resultDao.getAll();
        all.forEach(r -> System.out.println("JOGADOR: " + r.getPlayer() + "\t" +
                "\tACERTOS: " + r.getHits() + "\tDATA: " + r.getMatchDate().format(formatter)
        ));
    }
}
