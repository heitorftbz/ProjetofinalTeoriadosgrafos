package br.com.unipe;

import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Grafo rede = new Grafo(false, true);

        rede.adicionaVertices(
                "Ana",
                "Bruno",
                "Carlos",
                "Daniela",
                "Eduardo",
                "Fernanda",
                "Gabriel",
                "Hugo",
                "Igor",
                "Juliana"
        );

        // ======================================================
        // #### 4 - CONEXÕES PONDERADAS
        // ======================================================

        rede.addAresta("Ana", "Bruno", 1);
        rede.addAresta("Ana", "Carlos", 2);
        rede.addAresta("Ana", "Daniela", 8);
        rede.addAresta("Bruno", "Eduardo", 1);
        rede.addAresta("Carlos", "Eduardo", 1);
        rede.addAresta("Daniela", "Fernanda", 5);
        rede.addAresta("Eduardo", "Fernanda", 1);

        // ======================================================
        // #### 5 - GRUPOS ISOLADOS
        // ======================================================

        rede.addAresta("Gabriel", "Hugo", 1);
        rede.addAresta("Igor", "Juliana", 1);

        // ======================================================
        // #### 1 - CONSTRUTOR DA ANÁLISE
        // ======================================================

        LinkedInAnalyzer analyzer = new LinkedInAnalyzer(rede);

        Scanner scanner = new Scanner(System.in);

        // ======================================================
        // #### 2 - SUGESTÃO DE CONEXÕES
        // ======================================================

        System.out.println("=== LinkedIn Analyzer ===\n");
        System.out.println("Usuarios disponiveis:");
        System.out.println("Ana, Bruno, Carlos, Daniela, Eduardo, Fernanda\n");

        System.out.print("Digite o nome do usuario: ");
        String nome = scanner.nextLine();

        Map<String, Integer> sugestoes = analyzer.sugerirConexoes(nome);

        System.out.println("\nSugestoes de conexao para " + nome + ":");

        if (sugestoes.isEmpty())
        {
            System.out.println("Nenhuma sugestao encontrada.");
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
        // ======================================================
        // #### 3 - GRAU DE SEPARAÇÃO
        // ======================================================

        System.out.println("\n=== Grau de Separaçao ===");

        System.out.print("Digite a origem: ");
        String origem = scanner.nextLine();

        System.out.print("Digite o destino: ");
        String destino = scanner.nextLine();

        int grau = analyzer.grauDeSeparacao(origem, destino);

        if (grau == -1)
        {
            System.out.println("Os usuarios nao possuem conexao.");
        }
        else
        {
            System.out.println("Grau de separaçao: " + grau);
        }

        // ======================================================
        // #### 4 - ROTA E CUSTO DE MAIOR AFINIDADE
        // ======================================================

        System.out.println("\n=== Melhor Rota ===");

        System.out.print("Digite novamente a origem: ");
        origem = scanner.nextLine();

        System.out.print("Digite novamente o destino: ");
        destino = scanner.nextLine();

        ResultadoRota rota = analyzer.melhorRota(origem, destino);

        if (rota.getCusto() == -1)
        {
            System.out.println("Nao existe caminho entre os usuarios.");
        }
        else
        {
            System.out.println("\nMelhor rota:");
            System.out.println(String.join(" -> ", rota.getCaminho()));
            System.out.println("Custo total: " + rota.getCusto());
        }

        // ======================================================
        // #### 5 - MAPEAR GRUPOS ISOLADOS
        // ======================================================

        System.out.println("\n=== Grupos Isolados ===");

        List<List<String>> grupos = rede.mapeiaSubRedes();

        int contador = 1;

        for (List<String> grupo : grupos)
        {
            System.out.println("Grupo " + contador + ":");

            for (String usuario : grupo)
            {
                System.out.println(" - " + usuario);
            }

            contador++;
            System.out.println();
        }

        scanner.close();
    }
}