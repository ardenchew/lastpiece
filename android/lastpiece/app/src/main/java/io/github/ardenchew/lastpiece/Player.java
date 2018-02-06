package io.github.ardenchew.lastpiece;

public abstract class Player {

    public abstract String getName();

    public abstract Input getInput(Board b);

    public abstract int getPoints();

    public abstract void addPoint();

    public abstract void resetPoints();

}
