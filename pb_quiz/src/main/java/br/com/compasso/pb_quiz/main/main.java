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
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        boolean closeMenu = false;
         do {
             System.out.println("1 - Jogar novamente\n" +
                     "2 - Ver placar\n" +
                     "0 - Sair");
             int option = 3;
             try {
                 option = Integer.parseInt(scanner.nextLine());
             } catch (NumberFormatException ex) {
                 System.out.println("NumberFormat Exception: entrada inválida");
             }
            switch (option) {
                case 1:
                    System.out.println("Jogando");
                    play(em, scanner);
                    System.out.flush();
                    break;
                case 2:
                    System.out.println("vendo placar");
                    showScore(em);
                    break;
                case 0:
                    System.out.println("Saindo");
                    closeMenu = true;
                default:
                    System.out.println("Digite uma opção válida");


            }
        }while (closeMenu == false);

        em.getTransaction().commit();
        em.close();
    }

    private static void play(EntityManager em, Scanner scanner) {

        Result result = new Result();
        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        result.setPlayer(name);

        QuestionDao questionDao = new QuestionDao(em);
        List<Question> questions = questionDao.getActives();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Questão " + (i+1) + ": " + questions.get(i).getQuestion());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("verdadeiro") && questions.get(i).isAnswer() == true ) {
                result.setHits();
            } else if (answer.equalsIgnoreCase("falso") && questions.get(i).isAnswer() == false) {
                result.setHits();
            } else if (answer.equalsIgnoreCase("verdadeiro") || answer.equalsIgnoreCase("falso")) {
                result.setMisses();
            } else {
                System.out.println("Resposta não aceita, Digite verdadeiro ou falso");
                i--;
            }
        }

        ResultDao resultDao = new ResultDao(em);
        System.out.println(result.getMisses());
        resultDao.addResult(result);
    }

    private static void showScore(EntityManager em) {
        ResultDao resultDao = new ResultDao(em);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Result> all = resultDao.getAll();
        all.forEach(r -> System.out.println("JOGADOR: " + r.getPlayer() + "\t" +
                "\tACERTOS: " + r.getHits() + "\tDATA: " + r.getMatchDate().format(formatter)
        ));
    }
}
