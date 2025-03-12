package org.pokemon.pojo;

public class Crie {
    private final String latest;
    private final String legacy;

    public Crie(String latest, String legacy) {
        this.latest = latest;
        this.legacy = legacy;
    }

    public String getLatest() {
        return latest;
    }

    public String getLegacy() {
        return legacy;
    }
}
