package br.com.unipe;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Grafo rede = new Grafo(false, false);

        // Usuários
        rede.adicionaVertices(
                "Ana",
                "Bruno",
                "Carlos",
                "Daniela",
                "Eduardo",
                "Fernanda"
        );

        // Conexões
        rede.addAresta("Ana", "Bruno");
        rede.addAresta("Ana", "Carlos");

        rede.addAresta("Bruno", "Eduardo");

        rede.addAresta("Carlos", "Eduardo");
        rede.addAresta("Carlos", "Daniela");

        rede.addAresta("Daniela", "Fernanda");

        LinkedInAnalyzer analyzer = new LinkedInAnalyzer(rede);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== LinkedIn Analyzer ===");
        System.out.print("Digite o nome do usuário: ");
        System.out.print("Lembrando que java e sensitive case");
        System.out.print("Nomes:Ana, Bruno, Carlos, Daniela, Eduardo, Fernanda");

        String nome = scanner.nextLine();

        Map<String, Integer> sugestoes =
                analyzer.sugerirConexoes(nome);

        System.out.println();
        System.out.println("Sugestões de conexão para " + nome + ":");

        if (sugestoes.isEmpty())
        {
            System.out.println("Nenhuma sugestão encontrada.");
        }
        else
        {
            for (Map.Entry<String, Integer> sugestao : sugestoes.entrySet())
            {
                System.out.println(
                        sugestao.getKey()
                                + " (" + sugestao.getValue()
                                + " amigo(s) em comum)"
                );
            }
        }

        scanner.close();
    }
}