package mycontacts.app;

import mycontacts.controller.Agenda;
import mycontacts.exceptions.ContatoNaoEncontradoException;
import mycontacts.model.Contato;
import mycontacts.model.ContatoComercial;
import mycontacts.utils.ValidadorEmail;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Agenda agenda = new Agenda();

    public static void main(String[] args) {

        System.out.println("Bem-vindo ao MyContacts!");

        int opcao = -1;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> adicionarContato();
                case 2 -> agenda.listarContatos();
                case 3 -> buscarContato();
                case 4 -> removerContato();
                case 5 -> System.out.println("\nAté logo! ");
                default -> System.out.println("\n Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n**** AGENDA DE CONTATOS ****");
        System.out.println("1. Adicionar novo contato");
        System.out.println("2. Listar contatos");
        System.out.println("3. Buscar por nome");
        System.out.println("4. Remover contato");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            int op = scanner.nextInt();
            scanner.nextLine();
            return op;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void adicionarContato() {
        System.out.println("\n*** ADICIONAR CONTATO ***");
        System.out.println("Tipo de contato:");
        System.out.println("  1. Pessoal");
        System.out.println("  2. Comercial");
        System.out.print("Escolha: ");

        int tipo = lerOpcao();
        if (tipo != 1 && tipo != 2) {
            System.out.println(" Tipo inválido. Operação cancelada.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        if (nome.isBlank()) {
            System.out.println(" O nome não pode ser vazio. Operação cancelada.");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        String email = lerEmailValido();
        if (email == null) return;

        if (tipo == 1) {
            agenda.adicionarContato(new Contato(nome, telefone, email));
        } else {
            System.out.print("Empresa: ");
            String empresa = scanner.nextLine().trim();
            agenda.adicionarContato(new ContatoComercial(nome, telefone, email, empresa));
        }
    }

    private static String lerEmailValido() {
        for (int tentativa = 0; tentativa < 3; tentativa++) {
            System.out.print("Email (ou Enter para pular): ");
            String email = scanner.nextLine().trim();

            if (email.isBlank()) return "";

            if (ValidadorEmail.validar(email)) {
                return email;
            }

            System.out.println(" Email inválido. Tente novamente (" + (2 - tentativa) + " tentativa(s) restante(s)).");
        }
        System.out.println(" Número máximo de tentativas atingido. Operação cancelada.");
        return null;
    }

    private static void buscarContato() {
        System.out.println("\n*** BUSCAR CONTATO ***");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine().trim();

        try {
            Contato encontrado = agenda.buscarPorNome(nome);
            System.out.println("\n Contato encontrado:");
            System.out.println("  " + encontrado);
        } catch (ContatoNaoEncontradoException e) {
            System.out.println("\n " + e.getMessage());
        }
    }

    private static void removerContato() {
        System.out.println("\n*** REMOVER CONTATO ***");

        if (agenda.isEmpty()) {
            System.out.println("Nenhum contato cadastrado para remover.");
            return;
        }

        System.out.print("Digite o nome do contato a remover: ");
        String nome = scanner.nextLine().trim();

        try {
            agenda.removerContato(nome);
        } catch (ContatoNaoEncontradoException e) {
            System.out.println("\n " + e.getMessage());
        }
    }
}