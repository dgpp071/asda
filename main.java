package scr;

import java.util.ArrayList;
import java.util.Scanner;

class Aviao {
    private String numero;
    private int assentosDisponiveis;
    private ArrayList<String> reservas;

    public Aviao(String numero, int assentosDisponiveis) {
        this.numero = numero;
        this.assentosDisponiveis = assentosDisponiveis;
        this.reservas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public boolean reservarAssento(String passageiro) {
        if (assentosDisponiveis > 0) {
            reservas.add(passageiro);
            assentosDisponiveis--;
            return true;
        }
        return false;
    }

    public ArrayList<String> getReservas() {
        return reservas;
    }
}

public class SweetFlight {
    private static ArrayList<Aviao> frota = new ArrayList<>();
    private static final int MAX_RESERVAS = 20;
    private static int totalReservas = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar aviões");
            System.out.println("2. Definir assentos dos aviões");
            System.out.println("3. Reservar passagem aérea");
            System.out.println("4. Consultar reservas por avião");
            System.out.println("5. Consultar reservas por passageiro");
            System.out.println("6. Encerrar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarAvioes();
                    break;
                case 2:
                    definirAssentos();
                    break;
                case 3:
                    reservarPassagem();
                    break;
                case 4:
                    consultarPorAviao();
                    break;
                case 5:
                    consultarPorPassageiro();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    private static void registrarAvioes() {
        frota.clear();
        System.out.println("Informe o número de até 4 aviões:");
        for (int i = 0; i < 4; i++) {
            System.out.print("Número do avião " + (i + 1) + ": ");
            String numero = scanner.nextLine();
            frota.add(new Aviao(numero, 0));
        }
        System.out.println("Aviões registrados com sucesso!");
    }

    private static void definirAssentos() {
        for (Aviao aviao : frota) {
            System.out.print("Informe a quantidade de assentos para o avião " + aviao.getNumero() + ": ");
            int assentos = scanner.nextInt();
            scanner.nextLine();
            frota.set(frota.indexOf(aviao), new Aviao(aviao.getNumero(), assentos));
        }
        System.out.println("Assentos definidos com sucesso!");
    }

    private static void reservarPassagem() {
        if (totalReservas >= MAX_RESERVAS) {
            System.out.println("Limite máximo de reservas atingido!");
            return;
        }
        System.out.print("Informe o número do avião: ");
        String numeroAviao = scanner.nextLine();
        Aviao aviao = buscarAviao(numeroAviao);
        if (aviao == null) {
            System.out.println("Este avião não existe!");
            return;
        }
        if (aviao.getAssentosDisponiveis() == 0) {
            System.out.println("Não há assentos disponíveis para este avião!");
            return;
        }
        System.out.print("Informe o nome do passageiro: ");
        String passageiro = scanner.nextLine();
        if (aviao.reservarAssento(passageiro)) {
            totalReservas++;
            System.out.println("Reserva realizada com sucesso!");
        }
    }

    private static Aviao buscarAviao(String numero) {
        for (Aviao aviao : frota) {
            if (aviao.getNumero().equals(numero)) {
                return aviao;
            }
        }
        return null;
    }

    private static void consultarPorAviao() {
        System.out.print("Informe o número do avião: ");
        String numeroAviao = scanner.nextLine();
        Aviao aviao = buscarAviao(numeroAviao);
        if (aviao == null) {
            System.out.println("Este avião não existe!");
            return;
        }
        ArrayList<String> reservas = aviao.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas realizadas para este avião!");
        } else {
            System.out.println("Reservas para o avião " + numeroAviao + ": " + reservas);
        }
    }

    private static void consultarPorPassageiro() {
        System.out.print("Informe o nome do passageiro: ");
        String passageiro = scanner.nextLine();
        boolean encontrado = false;
        for (Aviao aviao : frota) {
            if (aviao.getReservas().contains(passageiro)) {
                System.out.println("Passageiro " + passageiro + " possui reserva no avião " + aviao.getNumero());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Não há reservas realizadas para este passageiro!");
        }
    }
}
