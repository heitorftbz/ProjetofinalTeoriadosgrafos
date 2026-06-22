package br.com.unipe;

import java.util.*;

public class LinkedInAnalyzer {

    private final Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        this.grafo = grafo;
    }

    public Map<String, Integer> sugerirConexoes(String usuario) {

        List<Vertice> amigos = grafo.getAmigos(usuario);

        Set<String> amigosDiretos = new HashSet<>();
        Map<String, Integer> sugestoes = new HashMap<>();

        for (Vertice amigo : amigos) {
            amigosDiretos.add(amigo.getNome());
        }

        for (Vertice amigo : amigos) {

            for (Vertice amigoDoAmigo : amigo.getAdjacencias()) {

                String nome = amigoDoAmigo.getNome();

                if (nome.equals(usuario))
                    continue;

                if (amigosDiretos.contains(nome))
                    continue;

                sugestoes.put(
                        nome,
                        sugestoes.getOrDefault(nome, 0) + 1
                );
            }
        }

        return sugestoes.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(
                        LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll
                );
    }
}