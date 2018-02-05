import java.util.ArrayList;

public abstract class CpuEval {

	public abstract CpuEval(Board b);

	public abstract void getPossibleMoves();

	public abstract void chooseMove();

	public abstract Move getMove();

}