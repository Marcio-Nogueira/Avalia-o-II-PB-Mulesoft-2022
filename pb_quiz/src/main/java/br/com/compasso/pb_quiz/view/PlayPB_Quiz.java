package br.com.compasso.pb_quiz.view;

import br.com.compasso.pb_quiz.controller.ResultController;

import java.util.Scanner;

public class PlayPB_Quiz extends ResultController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Carregando...");
                    play(scanner);
                    option = 4;
                    break;
                case 2:
                    System.out.println("Carregando...");
                    System.out.println("PLACAR");
                    showScore();
                    option = 4;
                    break;
                case 0:
                    System.out.println("Fechando aplicação");
                    closeMenu = true;
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }while (!closeMenu);
    }

}
