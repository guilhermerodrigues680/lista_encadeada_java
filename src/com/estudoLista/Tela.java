package com.estudoLista;

import com.estudoLista.models.Pessoa;

import java.util.LinkedList;
import java.util.Scanner;

public class Tela {

    private LinkedList<Pessoa> listPessoa;

    public Tela() {
        listPessoa = new LinkedList<Pessoa>();
        addInitialPersonToList();
    }

    private void addInitialPersonToList() {
        for (short i = 0; i < Short.MAX_VALUE; i++) {
            Pessoa p1 = new Pessoa("Emanuel", Pessoa.Sexo.MASCULINO);
            Pessoa p2 = new Pessoa("Joana", Pessoa.Sexo.FEMININO);
            listPessoa.add(p1);
            listPessoa.add(p2);
        }
    }

    private String horizontalRuleCLI() {
        byte nHyphen = 26;
        String hr = new String(new char[nHyphen]).replace('\0', '—');
        return "\u001B[33m" + hr + "\u001B[0m";
    }

    private String formatTextCLI(String text) {
        return String.format("\u001B[33m|\u001B[0m %-22s \u001B[33m|\u001B[0m", text); // 2 + 22 + 2 = 26 caracteres
    }

    private String menuOptions() {
        String menu = "";
        menu += horizontalRuleCLI() + '\n';
        menu += formatTextCLI("1 - Inserir no inicio") + '\n';
        menu += formatTextCLI("2 - Inserir no final") + '\n';
        menu += formatTextCLI("3 - Localizar nó") + '\n';
        menu += formatTextCLI("4 - Excluir nó") + '\n';
        menu += formatTextCLI("5 - Exibir lista") + '\n';
        menu += formatTextCLI("6 - Tamanho da lista") + '\n';
        menu += formatTextCLI("7 - Sair") + '\n';
        menu += horizontalRuleCLI() + '\n';

        return menu;
    }

    private Pessoa readPessoaCLI() {
        Scanner scanner = new Scanner(System.in);
        char sexo;

        System.out.print("Nome: ");
        String nome = scanner.next();

        do {
            System.out.print("Sexo (M|F): ");
            sexo = scanner.next().charAt(0);

            if (sexo != 'M' && sexo != 'F')
                System.out.println("Sexo inválido, digite M ou F.");

        } while (sexo != 'M' && sexo != 'F');

        Pessoa pessoa = new Pessoa(nome, sexo == 'M' ? Pessoa.Sexo.MASCULINO : Pessoa.Sexo.FEMININO);
        System.out.println(pessoa);

        return pessoa;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(menuOptions());
            System.out.print("Digite a opcao (1-7): ");
            final char opcao = scanner.nextLine().charAt(0);
            //scanner.nextLine(); // It consumes the '\n character

            switch (opcao) {
                case '1': // Adiciona uma pessoa no inicio da lista
                    listPessoa.addFirst(readPessoaCLI());
                    break;

                case '2': // Adiciona uma pessoa no fim da lista
                    listPessoa.addLast(readPessoaCLI());
                    break;

                case '3': // Localiza uma pessoa pelo nome
                    System.out.print("Digite o nome da pessoa: ");
                    final String searchPessoa = scanner.nextLine().trim();

                    final Pessoa researchedPerson = listPessoa.stream()
                            .filter(pessoa -> searchPessoa.equals(pessoa.getNome()))
                            .findAny()
                            .orElse(null);

                    if (researchedPerson != null) {
                        final int indexPerson = listPessoa.indexOf(researchedPerson);
                        System.out.println("Pessoa encontrada: index " + indexPerson + ", " + researchedPerson);
                    }
                    else
                        System.out.println("Nenhuma pessoa encontrada.");

                    break;

                case '4': // Remove a pessoa contida no index da lista
                    System.out.print("Digite o index da pessoa: ");
                    final int idxPersonRemove = scanner.nextInt();
                    scanner.nextLine(); // It consumes the '\n character

                    if (idxPersonRemove < 0 || idxPersonRemove >= listPessoa.size())
                        System.out.println("Este index não existe. Nenhuma pessoa removida.");
                    else {
                        final Pessoa personRemoved = listPessoa.remove(idxPersonRemove);
                        System.out.println("Removido: " + personRemoved);
                    }

                    break;

                case '5':
                    System.out.println("Pessoas: ");
                    System.out.println(listPessoa);
                    break;

                case '6':
                    System.out.print("Tamanho: ");
                    System.out.println(listPessoa.size());
                    break;

                case '7':
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida");
                    continue;
                    //break;
            }

            System.out.println("\nPressione ENTER para exibir o menu...");
            scanner.nextLine();
        }

    }

}
