package com.senac.desafiopokeapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DesafioPokeAPI {

    public static void main(String[] args) {
        String nomePokemon = null;
        String idMovimento = null;
        String idLocal = null;
        String idEvolucoes = null;
        String idItem = null;
        String idJogos = null;
        String escolhaAPI1 = null;
        String escolhaAPI2 = null;


        try {
            // Abertura do Scanner
            Scanner sc = new Scanner(System.in);
            System.out.println("""
                    Qual item você deseja consultar
                    na API do Pokemon?
                    1-Pokemóns
                    2-Movimentos
                    3-Local
                    4-Evolucoes
                    5-Item
                    6-Jogos""");
            int escolha = sc.nextInt();
            if (escolha == 1) {
                //Escolher o Pokemon
                nomePokemon =
                        JOptionPane.showInputDialog("" +
                                "Escreva o nome do Pokemón desejado: ");
                escolhaAPI2 = nomePokemon;
                escolhaAPI1 = "pokemon";
            } else if (escolha == 2){
                //Escolher o Pokemon
                idMovimento = JOptionPane.showInputDialog("" +
                        "Escreva o ID do Movimento desejado: ");
                escolhaAPI2 = idMovimento;
                escolhaAPI1 = "move";
            } else if (escolha == 3) {
                //Escolha o Movimento
                idLocal = JOptionPane.showInputDialog("" +
                        "Escreva o ID do Local desejado: ");
                escolhaAPI2 = idLocal;
                escolhaAPI1 = "location";
            } else if (escolha == 4) {
                //Escolher as Evolucoes
                idEvolucoes = JOptionPane.showInputDialog("" +
                        "Escreva o ID das evolucoes desejado: ");
                escolhaAPI2 = idEvolucoes;
                escolhaAPI1 = "evolution-chain";
            } else if (escolha == 5) {
                //Escolha o Item
                idItem = JOptionPane.showInputDialog("" +
                        "Escreva o ID do Item desejado: ");
                escolhaAPI2 = idItem;
                escolhaAPI1 = "item";
            } else if (escolha == 6) {
                //Escolha o Jogo
                idJogos = JOptionPane.showInputDialog("" +
                        "Escreva o ID dos Jogos desejado: ");
                escolhaAPI2 = idJogos;
                escolhaAPI1 = "generation";
            }
            // URL da API do PokeAPI
            URL url = new URL("https://pokeapi.co/api/v2/" + escolhaAPI1 + "/" + escolhaAPI2);
            // Abrir conexão HTTP
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("GET");

            // Obter a resposta da API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Tratar o JSON utilizando o Jackson
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.toString());

//                String pokemonName = jsonNode.get("name").asText();
//                int pokemonHeight = jsonNode.get("height").asInt();
//                int pokemonWeight = jsonNode.get("weight").asInt();
//
//
//                System.out.println("Nome: " + pokemonName);
//                System.out.println("Altura: " + pokemonHeight);
//                System.out.println("Peso: " + pokemonWeight);

                System.out.println(jsonNode);

            } else {
                System.out.println("Erro na conexão: " + responseCode);
            }

            // Fechar a conexão
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}