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
import java.util.logging.Level;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); //collection that turns off log from hibernate
        EntityManager em = JPAUtil.getEntityManager();
        QuestionDao questionDao = new QuestionDao(em);
        Result result = new Result();
        ResultDao resultDao = new ResultDao(em);
        em.getTransaction().begin();

        boolean closeMenu = false;
        int option = 3;
         do {
             System.out.println("------PB_QUIZ------");
             if (option == 3) {
                 System.out.println("1 - Jogar");
             } else {
                 System.out.println("1 - Jogar novamente");
             }
             System.out.println("2 - Ver placar\n" +
                     "0 - Sair");
             try {
                 option = Integer.parseInt(scanner.nextLine());
             } catch (NumberFormatException ex) {
                 System.out.println("NumberFormat Exception: entrada inválida");
             }
            switch (option) {
                case 1:
                    System.out.println("Jogando");
                    play(scanner, em, questionDao, result, resultDao);
                    option = 4;
                    break;
                case 2:
                    System.out.println("vendo placar");
                    showScore(resultDao);
                    break;
                case 0:
                    System.out.println("Saindo");
                    closeMenu = true;
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }while (!closeMenu);

        em.close();
    }

    private static void play(Scanner scanner, EntityManager em, QuestionDao questionDao, Result result, ResultDao resultDao) {

        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        result.setPlayer(name);

        System.out.println("Responda com verdadeiro ou falso as questões abaixo");
        List<Question> questions = questionDao.getActives();
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
    }

    private static void showScore(ResultDao resultDao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Result> all = resultDao.getAll();
        all.forEach(r -> System.out.println("JOGADOR: " + r.getPlayer() + "\t" +
                "\tACERTOS: " + r.getHits() + "\tDATA: " + r.getMatchDate().format(formatter)
        ));
    }
}
