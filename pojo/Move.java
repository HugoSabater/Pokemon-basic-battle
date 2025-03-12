package org.pokemon.pojo;

public class Move {
    private final String moveName;
    private final String moveType;
    private final int power;

    public Move(String moveName, String moveType, int power) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.power = power;
    }

    public String getName() {
        return moveName;
    }

    public String getType() {
        return moveType;
    }

    public int getPower() {
        return power;
    }

}
