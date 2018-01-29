package io.github.ardenchew.lastpiece;

public class UserPlayer extends Player {

    public String name;
    public int points;

    public UserPlayer(String n) {
        this.name = n;
        this.points = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Input getInput() {
        return null;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoint() {
        this.points += 1;
    }

    public void resetPoints() {
        this.points = 0;
    }

}