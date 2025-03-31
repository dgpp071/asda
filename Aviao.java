package scr;

import java.util.ArrayList;
import java.util.Scanner;

class Aviao {
    private int numero;
    private int assentosDisponiveis;
    private ArrayList<String> reservas;

    public Aviao(int numero, int assentosDisponiveis) {
        this.numero = numero;
        this.assentosDisponiveis = assentosDisponiveis;
        this.reservas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void reservarPassagem(String nomePassageiro) {
        if (assentosDisponiveis > 0) {
            reservas.add(nomePassageiro);
            assentosDisponiveis--;
        }
    }

    public ArrayList<String> getReservas() {
        return reservas;
    }

    public boolean temAssentoDisponivel() {
        return assentosDisponiveis > 0;
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas realizadas para este avião!");
        } else {
            System.out.println("Reservas para o avião " + numero + ":");
            for (String reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }
}

class SistemaDeReservas {
    private ArrayList<Aviao> avioes;
    private int contadorReservas;

    public SistemaDeReservas() {
        avioes = new ArrayList<>();
        contadorReservas = 0;
    }

    public void registrarAviao(int numero, int assentosDisponiveis) {
        avioes.add(new Aviao(numero, assentosDisponiveis));
    }

    public Aviao buscarAviao(int numero) {
        for (Aviao aviao : avioes) {
            if (aviao.getNumero() == numero) {
                return aviao;
            }
        }
        return null;
    }

    public boolean realizarReserva(int numeroAviao, String nomePassageiro) {
        if (contadorReservas >= 20) {
            System.out.println("Limite de reservas atingido!");
            return false;
        }

        Aviao aviao = buscarAviao(numeroAviao);
        if (aviao == null) {
            System.out.println("Este avião não existe!");
            return false;
        }

        if (!aviao.temAssentoDisponivel()) {
            System.out.println("Não há assentos disponíveis para este avião!");
            return false;
        }

        aviao.reservarPassagem(nomePassageiro);
        contadorReservas++;
        System.out.println("Reserva realizada com sucesso!");
        return true;
    }

    public void consultarPorAviao(int numeroAviao) {
        Aviao aviao = buscarAviao(numeroAviao);
        if (aviao == null) {
            System.out.println("Este avião não existe!");
        } else {
            aviao.mostrarReservas();
        }
    }

    public void consultarPorPassageiro(String nomePassageiro) {
        boolean encontrado = false;
        for (Aviao aviao : avioes) {
            for (String reserva : aviao.getReservas()) {
                if (reserva.equals(nomePassageiro)) {
                    if (!encontrado) {
                        System.out.println("Reservas para o passageiro " + nomePassageiro + ":");
                    }
                    System.out.println("Avião: " + aviao.getNumero());
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("Não há reservas realizadas para este passageiro!");
        }
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Registrar o número de cada avião.");
            System.out.println("2. Registrar o quantitativo de assentos disponíveis em cada avião.");
            System.out.println("3. Reservar passagem aérea.");
            System.out.println("4. Realizar consulta por avião.");
            System.out.println("5. Realizar consulta por passageiro.");
            System.out.println("6. Encerrar.");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    registrarNumeroAvioes(scanner);
                    break;
                case 2:
                    registrarAssentos(scanner);
                    break;
                case 3:
                    realizarReserva(scanner);
                    break;
                case 4:
                    consultaPorAviao(scanner);
                    break;
                case 5:
                    consultaPorPassageiro(scanner);
                    break;
                case 6:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    private void registrarNumeroAvioes(Scanner scanner) {
        System.out.print("Digite a quantidade de aviões disponíveis (máximo de 4): ");
        int qtdAvioes = scanner.nextInt();
        if (qtdAvioes > 4) {
            System.out.println("Máximo de 4 aviões.");
            return;
        }
        for (int i = 0; i < qtdAvioes; i++) {
            System.out.print("Digite o número do avião " + (i + 1) + ": ");
            int numeroAviao = scanner.nextInt();
            registrarAviao(numeroAviao, 0);
        }
        System.out.println("Aviões registrados com sucesso!");
    }

    private void registrarAssentos(Scanner scanner) {
        for (Aviao aviao : avioes) {
            System.out.print("Digite a quantidade de assentos disponíveis para o avião " + aviao.getNumero() + ": ");
            int assentos = scanner.nextInt();
            aviao.reservarPassagem("Teste"); // Simula um processo de ajuste, não utilizado para reservas reais.
        }
        System.out.println("Assentos registrados com sucesso!");
    }

    private void realizarReserva(Scanner scanner) {
        System.out.print("Digite o número do avião: ");
        int numeroAviao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Digite o nome do passageiro: ");
        String nomePassageiro = scanner.nextLine();
        realizarReserva(numeroAviao, nomePassageiro);
    }

    private void consultaPorAviao(Scanner scanner) {
        System.out.print("Digite o número do avião para consulta: ");
        int numeroAviao = scanner.nextInt();
        consultarPorAviao(numeroAviao);
    }

    private void consultaPorPassageiro(Scanner scanner) {
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Digite o nome do passageiro para consulta: ");
        String nomePassageiro = scanner.nextLine();
        consultarPorPassageiro(nomePassageiro);
    }
}

public class Main {
    public static void main(String[] args) {
        SistemaDeReservas sistema = new SistemaDeReservas();
        sistema.exibirMenu();
    }
}
