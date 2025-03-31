package scr;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op = 0;
        ArrayList<Aviao> avioes = new ArrayList<>();
        int qntMax = 4;
        while (op!=5) {
            System.out.println("====MENU====");
            System.out.println("1 - Registrar Aviões ");
            System.out.println("2 - Registrar quantidade de assentos");
            System.out.println("3 - Reservar passagem aérea");
            System.out.println("4 - Realizar consulta por avião");
            System.out.println("5 - Realizar consulta por passageiro");
            System.out.println("6 - Sair");
            System.out.println("Escolha uma opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    for (int a = 0; a<=qntMax; a++) {
                        System.out.println("Digite o número do "+(a+1)+"º Avião: ");
                        int numero = input.nextInt();
                        avioes.add(new Aviao(numero, 0));
                        System.out.println("Avião "+numero+ "Cadastrado com sucesso.");
                    }       
                    break;
            
                default:
                    break;
            }
        }
    

        input.close();
        
    }
}
