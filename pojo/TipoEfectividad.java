package org.pokemon.pojo;

import java.util.HashMap;
import java.util.Map;


// Se trata de un tipo enumerado. PokemonType solo puede tomar uno de estos valores

enum PokemonType {
    NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE,
    FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG,
    ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY;
}

public class TipoEfectividad {
    private static final Map<String, Map<String, Double>> efectividad = new HashMap<>();

    // Añado la primera entrada del Map. Para cada uno de los elementos de tipo PokemonType del tipo enum
    // le creo un MashMap vacío que luego llenaré.
    static {
        for (PokemonType type : PokemonType.values()) {
            efectividad.put(String.valueOf(type), new HashMap<>());
        }

        //        2.0 → Súper efectivo
        //        0.5 → Poco efectivo
        //        0.0 → No afecta

        // Definición de efectividad de tipos
        // El llenado consiste en buscar en el Map una primera entrada y asociarle a su HashMap asociado una serie de elementos.

// Tipo normal
        efectividad.get("NORMAL").put("ROCK", 0.5);
        efectividad.get("NORMAL").put("GHOST", 0.0);
        efectividad.get("NORMAL").put("STEEL", 0.5);

// Tipo FUEGO
        efectividad.get("FIRE").put("FIRE", 0.5);
        efectividad.get("FIRE").put("WATER", 0.5);
        efectividad.get("FIRE").put("GRASS", 2.0);
        efectividad.get("FIRE").put("ICE", 2.0);
        efectividad.get("FIRE").put("BUG", 2.0);
        efectividad.get("FIRE").put("ROCK", 0.5);
        efectividad.get("FIRE").put("DRAGON", 0.5);
        efectividad.get("FIRE").put("STEEL", 2.0);

// Tipo AGUA
        efectividad.get("WATER").put("FIRE", 2.0);
        efectividad.get("WATER").put("WATER", 0.5);
        efectividad.get("WATER").put("GRASS", 0.5);
        efectividad.get("WATER").put("GROUND", 2.0);
        efectividad.get("WATER").put("ROCK", 2.0);
        efectividad.get("WATER").put("DRAGON", 0.5);

// Tipo ELÉCTRICO
        efectividad.get("ELECTRIC").put("WATER", 2.0);
        efectividad.get("ELECTRIC").put("ELECTRIC", 0.5);
        efectividad.get("ELECTRIC").put("GRASS", 0.5);
        efectividad.get("ELECTRIC").put("GROUND", 0.0);
        efectividad.get("ELECTRIC").put("FLYING", 2.0);
        efectividad.get("ELECTRIC").put("DRAGON", 0.5);

// Tipo PLANTA
        efectividad.get("GRASS").put("FIRE", 0.5);
        efectividad.get("GRASS").put("WATER", 2.0);
        efectividad.get("GRASS").put("GRASS", 0.5);
        efectividad.get("GRASS").put("POISON", 0.5);
        efectividad.get("GRASS").put("GROUND", 2.0);
        efectividad.get("GRASS").put("FLYING", 0.5);
        efectividad.get("GRASS").put("BUG", 0.5);
        efectividad.get("GRASS").put("ROCK", 2.0);
        efectividad.get("GRASS").put("DRAGON", 0.5);
        efectividad.get("GRASS").put("STEEL", 0.5);

// Tipo HIELO
        efectividad.get("ICE").put("FIRE", 0.5);
        efectividad.get("ICE").put("WATER", 0.5);
        efectividad.get("ICE").put("GRASS", 2.0);
        efectividad.get("ICE").put("ICE", 0.5);
        efectividad.get("ICE").put("GROUND", 2.0);
        efectividad.get("ICE").put("FLYING", 2.0);
        efectividad.get("ICE").put("DRAGON", 2.0);
        efectividad.get("ICE").put("STEEL", 0.5);

// Tipo LUCHA
        efectividad.get("FIGHTING").put("NORMAL", 2.0);
        efectividad.get("FIGHTING").put("ICE", 2.0);
        efectividad.get("FIGHTING").put("POISON", 0.5);
        efectividad.get("FIGHTING").put("FLYING", 0.5);
        efectividad.get("FIGHTING").put("PSYCHIC", 0.5);
        efectividad.get("FIGHTING").put("BUG", 0.5);
        efectividad.get("FIGHTING").put("ROCK", 2.0);
        efectividad.get("FIGHTING").put("GHOST", 0.0);
        efectividad.get("FIGHTING").put("DARK", 2.0);
        efectividad.get("FIGHTING").put("STEEL", 2.0);
        efectividad.get("FIGHTING").put("FAIRY", 0.5);

// Tipo VENENO
        efectividad.get("POISON").put("GRASS", 2.0);
        efectividad.get("POISON").put("POISON", 0.5);
        efectividad.get("POISON").put("GROUND", 0.5);
        efectividad.get("POISON").put("ROCK", 0.5);
        efectividad.get("POISON").put("GHOST", 0.5);
        efectividad.get("POISON").put("STEEL", 0.0);
        efectividad.get("POISON").put("FAIRY", 2.0);

// Tipo TIERRA
        efectividad.get("GROUND").put("FIRE", 2.0);
        efectividad.get("GROUND").put("ELECTRIC", 2.0);
        efectividad.get("GROUND").put("GRASS", 0.5);
        efectividad.get("GROUND").put("POISON", 2.0);
        efectividad.get("GROUND").put("FLYING", 0.0);
        efectividad.get("GROUND").put("BUG", 0.5);
        efectividad.get("GROUND").put("ROCK", 2.0);
        efectividad.get("GROUND").put("STEEL", 2.0);

// Tipo VOLADOR
        efectividad.get("FLYING").put("ELECTRIC", 0.5);
        efectividad.get("FLYING").put("GRASS", 2.0);
        efectividad.get("FLYING").put("FIGHTING", 2.0);
        efectividad.get("FLYING").put("BUG", 2.0);
        efectividad.get("FLYING").put("ROCK", 0.5);
        efectividad.get("FLYING").put("STEEL", 0.5);

// Tipo PSÍQUICO
        efectividad.get("PSYCHIC").put("FIGHTING", 2.0);
        efectividad.get("PSYCHIC").put("POISON", 2.0);
        efectividad.get("PSYCHIC").put("PSYCHIC", 0.5);
        efectividad.get("PSYCHIC").put("DARK", 0.0);
        efectividad.get("PSYCHIC").put("STEEL", 0.5);

// Tipo BICHO
        efectividad.get("BUG").put("FIRE", 0.5);
        efectividad.get("BUG").put("GRASS", 2.0);
        efectividad.get("BUG").put("FIGHTING", 0.5);
        efectividad.get("BUG").put("POISON", 0.5);
        efectividad.get("BUG").put("FLYING", 0.5);
        efectividad.get("BUG").put("PSYCHIC", 2.0);
        efectividad.get("BUG").put("GHOST", 0.5);
        efectividad.get("BUG").put("DARK", 2.0);
        efectividad.get("BUG").put("STEEL", 0.5);
        efectividad.get("BUG").put("FAIRY", 0.5);

// Tipo ROCA
        efectividad.get("ROCK").put("FIRE", 2.0);
        efectividad.get("ROCK").put("ICE", 2.0);
        efectividad.get("ROCK").put("FIGHTING", 0.5);
        efectividad.get("ROCK").put("GROUND", 0.5);
        efectividad.get("ROCK").put("FLYING", 2.0);
        efectividad.get("ROCK").put("BUG", 2.0);
        efectividad.get("ROCK").put("STEEL", 0.5);

// Tipo FANTASMA
        efectividad.get("GHOST").put("NORMAL", 0.0);
        efectividad.get("GHOST").put("PSYCHIC", 2.0);
        efectividad.get("GHOST").put("GHOST", 2.0);
        efectividad.get("GHOST").put("DARK", 0.5);

// Tipo DRAGÓN
        efectividad.get("DRAGON").put("DRAGON", 2.0);
        efectividad.get("DRAGON").put("STEEL", 0.5);
        efectividad.get("DRAGON").put("FAIRY", 0.0);

// Tipo SINIESTRO
        efectividad.get("DARK").put("FIGHTING", 0.5);
        efectividad.get("DARK").put("PSYCHIC", 2.0);
        efectividad.get("DARK").put("GHOST", 2.0);
        efectividad.get("DARK").put("DARK", 0.5);
        efectividad.get("DARK").put("FAIRY", 0.5);

// Tipo ACERO
        efectividad.get("STEEL").put("FIRE", 0.5);
        efectividad.get("STEEL").put("WATER", 0.5);
        efectividad.get("STEEL").put("ELECTRIC", 0.5);
        efectividad.get("STEEL").put("ICE", 2.0);
        efectividad.get("STEEL").put("ROCK", 2.0);
        efectividad.get("STEEL").put("STEEL", 0.5);
        efectividad.get("STEEL").put("FAIRY", 2.0);

// Tipo HADA
        efectividad.get("FAIRY").put("FIRE", 0.5);
        efectividad.get("FAIRY").put("FIGHTING", 2.0);
        efectividad.get("FAIRY").put("POISON", 0.5);
        efectividad.get("FAIRY").put("DRAGON", 2.0);
        efectividad.get("FAIRY").put("DARK", 2.0);
        efectividad.get("FAIRY").put("STEEL", 0.5);

    }

    // Este método nos devolverá la efectividad de un ataque en función del tipo de defensa.
    // En el caso que no encuentre esta combinación, devolverá 1.0
    // Si ves que falta combinaciones, las puedes añadir.

    public static double getEfectividad(String tipoAtaque, String tipoDefensa) {
        return efectividad.getOrDefault(tipoAtaque.toUpperCase(), new HashMap<>()).getOrDefault(tipoDefensa, 1.0);
    }
}

