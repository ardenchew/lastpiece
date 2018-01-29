package io.github.ardenchew.lastpiece;

public class ComputerPlayer extends Player {

    public String name;
    public int points;

    public ComputerPlayer(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public Input getInput() {
        this.points = 0;
        Move m = new Move();
        return new Input(Input.INPUTTYPE.CPUINPUT_GAMECOMMAND, m); //TODO
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