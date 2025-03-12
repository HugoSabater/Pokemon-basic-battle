package org.pokemon;

import org.pokemon.ApiPokemon.ApiPokemon;
import org.pokemon.pojo.LuchaPokemon;
import org.pokemon.pojo.Pokemon;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    public static void main(String[] args) {
        ArrayList<Pokemon> misPokemon = ApiPokemon.recogePokemons(BASE_URL,50);
        misPokemon.forEach(System.out::println);

        Random rand = new Random();
        Pokemon p1 = misPokemon.get(rand.nextInt(misPokemon.size()));
        Pokemon p2 = misPokemon.get(rand.nextInt(misPokemon.size()));

        LuchaPokemon.lucha(p1, p2);
    }
}