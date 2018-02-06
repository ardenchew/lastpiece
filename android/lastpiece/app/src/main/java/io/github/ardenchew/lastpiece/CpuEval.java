package io.github.ardenchew.lastpiece;

public abstract class CpuEval { //constructor should take board state

	public abstract void getPossibleMoves();

	public abstract void chooseMove();

	public abstract Move getMove();

}