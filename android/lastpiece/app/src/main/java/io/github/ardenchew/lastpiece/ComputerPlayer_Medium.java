package io.github.ardenchew.lastpiece;

/**
 * Created by arden on 2/5/2018.
 */

public class ComputerPlayer_Medium extends Player {

    public String name;
    public int points;

    public ComputerPlayer_Medium(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public Input getInput(Board b) {
        CpuEvalLastPiece_Medium celp = new CpuEvalLastPiece_Medium(b);
        return new Input(Input.INPUTTYPE.CPUINPUT_GAMECOMMAND, celp.getMove()); //TODO
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
