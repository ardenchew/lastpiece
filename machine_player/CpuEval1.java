import java.util.ArrayList;

public abstract class CpuEval1 {
	
	public BoardState input;
	public ArrayList<BoardState> options;

	public abstract void getPossibleMoves() {

	}

	public abstract BoardState chooseMove();

	public Move convertBoadStateMove(BoardState bs) {
		ArrayList<Integer> tmpBs = this.input.getList();  {3}
		ArrayList<Integer> inBs = this.input.getList();  {7, 5, 3, 1}
		ArrayList<Integer> newBs = bs.getList(); {7, 5, 1, 1}
		
		Move m = new Move();

		int idxHolder;
		for (int i = 0 ; i < inBs.size(); i++) {
			idxHolder = tmpBs.indexOf(newBs.get(i));
			if (idxHolder != -1) {
				tmpBs.remove(idxHolder); 
			}
		}

		idxHolder = inBs.indexOf(tmpBs.get(0));
		int numToChange = inBs.get(idxHolder) - newBs.get(idxHolder);

		for (int i = 0; i < numToChange; i++) {

		}


	}


}