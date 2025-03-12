package org.pokemon.ApiPokemon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.pokemon.pojo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiPokemon {
    private static final Gson gson = new Gson();

    public static HttpURLConnection getConexion(String cadena)  {
        // Este es el método más adecuado para generar una URL. new URL(cadena) está deprecated.
        try {
            URL url = new URI(cadena).toURL();
            // Generamos la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Establecemos el método GET, es decir, que vamos a recibir datos
            conn.setRequestMethod("GET");
            // Establecemos que tipo de información vamos a procesar en el get, en nuestro caso va a ser un json
            conn.setRequestProperty("Accept", "application/json");
            // Comprobamos que el código sea 200, es decir, que ha funcionado. Si es distino, generamos una excepción.
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
            }
            // Devolvemos el objeto conexión.
            return conn;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPokemonData(String url)  {
        try{
            HttpURLConnection conn = getConexion(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();
            return response.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<Pokemon> recogePokemons(String url, int limite) {

        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
        try {

            // Fíjate. El método getPokemonData recibe la url. Sobre esta se le concatena el texto para indicar el número de pokémon
            String jsonResponse = getPokemonData(url + "pokemon?limit=" + limite);

            // Un String de por sí no sirve para nada, de tal manera que a partir de la información recibida generamos un objeto json. (JsonObject)
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

            // Una vez convertido a un jsonObject, podemos utilizar métodos que nos permiten acceder a propiedades y leer la información del mismo.
            // Generamos un array (JsonArray) que llamamos results, como la propiedad
            JsonArray results = jsonObject.getAsJsonArray("results");

            // Bucleamos y dentro de estos results, de tipo element, sacamos uno a uno los JsonObject y trabajamos con ellos.
            for (var element : results) {
                JsonObject obj = element.getAsJsonObject();
                // Lanzamos una nueva conexión para obtener la información del pokémon en cuestión,  obj.get("name").getAsString()
                String pokemonJson = getPokemonData(url+"pokemon/" + obj.get("name").getAsString());

                // Lo serializamos con el método de gson: gson.fromJson(pokemonJson, JsonObject.class);
                JsonObject pokemonData = gson.fromJson(pokemonJson, JsonObject.class);

                // Obtener nombre (name)
                String name = pokemonData.get("name").getAsString();
                // Obtener id (id)
                int id = pokemonData.get("id").getAsInt();
                // Obtener altura (height)
                int height = pokemonData.get("height").getAsInt();
                // Obtener peso (weight)
                int weight = pokemonData.get("weight").getAsInt();

                // Obtener tipo. Recuerda, es un array. De este solo nos hace falta el primero
                String type = pokemonData.getAsJsonArray("types").get(0)
                        .getAsJsonObject().get("type")
                        .getAsJsonObject().get("name")
                        .getAsString();

                // Obtener estadísticas
                List<Stat> stats = new ArrayList<>();
                JsonArray statsArray = pokemonData.getAsJsonArray("stats");
                // Debes buclear para poder obtener estas
                for (var statElement : statsArray) {
                    JsonObject statObj = statElement.getAsJsonObject();
                    // Código para obtener el valor de "base_stat" que lo dejarás en la variable baseStat
                    int baseStat = statObj.get("base_stat").getAsInt();
                    // Código para obtener el valor de name, que lo dejarás en la variable statName
                    String statName = statObj.get("stat")
                            .getAsJsonObject().get("name")
                            .getAsString();

                    // Una vez obtenido, deberás almacenar este en la lista. Fíjate porque se hace así de una manera sencilla.
                    stats.add(new Stat(baseStat, new StatDetail(statName)));
                }
                // Obtener movimientos (limítalo a 10 para que sea jugable)
                List<Move> moves = new ArrayList<>();
                JsonArray movesArray = pokemonData.getAsJsonArray("moves");
                int cont = Math.min(10, movesArray.size());
                
                for (int i = 0; i < cont; i++) {
                    // Obtener el nombre del movimiento
                    JsonObject moveObj = movesArray.get(i).getAsJsonObject().get("move").getAsJsonObject();
                    String moveName = moveObj.get("name").getAsString();

                    // Obtener detalles del movimiento
                    String moveJson = getPokemonData(url + "move/" + moveName);
                    JsonObject moveData = gson.fromJson(moveJson, JsonObject.class);
                    // Nombre del tipo de movimiento.
                    String moveType = moveData.get("type").getAsJsonObject().get("name").getAsString();
                    int power = moveData.has("power") && !moveData.get("power").isJsonNull()
                            ? moveData.get("power").getAsInt()
                            : 40; // Default en caso de null

                    moves.add(new Move(moveName, moveType, power));
                }
                // Obtener habilidad
                JsonObject abilityObj = pokemonData.getAsJsonArray("abilities")
                        .get(0).getAsJsonObject()
                        .get("ability").getAsJsonObject();
                String abilityName = abilityObj.get("name").getAsString();
                // Obtener detalles de la habilidad
                String abilityJson = getPokemonData(url + "ability/" + abilityName);
                JsonObject abilityData = gson.fromJson(abilityJson, JsonObject.class);
                String abilityEffect = abilityData.get("effect_entries")
                        .getAsJsonArray().get(0).getAsJsonObject()
                        .get("effect").getAsString();

                Ability ability = new Ability(abilityName, abilityEffect);

                // Gritos
                List<Crie> chillito = new ArrayList<>();
                JsonObject cries = pokemonData.getAsJsonObject("cries");
                String latestCry = cries.has("latest") ? cries.get("latest").getAsString() : "N/A";
                String legacyCry = cries.has("legacy") ? cries.get("legacy").getAsString() : "N/A";
                chillito.add(new Crie(latestCry,legacyCry));

                List<Form> forms = new ArrayList<>();
                JsonArray formsArray = pokemonData.getAsJsonArray("forms");

                for (var formElement : formsArray) {
                    JsonObject formObj = formElement.getAsJsonObject();
                    String formUrl = formObj.get("url").getAsString();

                    // Hacer una petición extra a la API para obtener detalles de la forma
                    String formJson = getPokemonData(formUrl);
                    JsonObject formData = gson.fromJson(formJson, JsonObject.class);

                    // Extraer imágenes de la forma
                    JsonObject sprites = pokemonData.getAsJsonObject("sprites");
                    String backDefault = sprites.has("back_default")
                            ? sprites.get("back_default").getAsString() : "N/A";
                    String backShiny = sprites.has("back_shiny")
                            ? sprites.get("back_shiny").getAsString() : "N/A";
                    String frontDefault = sprites.has("frontDefault")
                            ? sprites.get("frontDefault").getAsString() : "N/A";
                    String frontShiny = sprites.has("front_shiny")
                            ? sprites.get("front_shiny").getAsString() : "N/A";

                    forms.add(new Form(backDefault,frontShiny,frontDefault,backShiny));
                }

                pokemonArrayList.add(new Pokemon(name,id, height, weight,type,chillito,forms,stats, moves, ability));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pokemonArrayList;
    }
}
