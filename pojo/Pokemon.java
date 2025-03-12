package org.pokemon.pojo;

import java.util.List;

public class Pokemon {
    private final String name;
    private final int id;
    private final int height;
    private final int weight;
    private final String type;
    private final List<Move> moves;

    public Pokemon(String name, int id, int height, int weight, String type, List<Crie> crieList, List<Form> formList, List<Stat> stats, List<Move> moves, Ability ability) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public String toString() {
        return "Pokemon: " + name + ", id: " + id + ", height: " + height + ", weight: " + weight + ", type: " + type;
    }
}
