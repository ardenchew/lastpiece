import java.util.ArrayList;

public abstract class CpuEval {
	
	public BoardState input;
	public ArrayList<BoardState> options;

	public abstract void getPossibleMoves() {

	}

	public abstract BoardState chooseMove();

	public Move convertBoadStateMove(BoardState bs) {
		ArrayList<Integer> tmpBs = this.input.getList();
		ArrayList<Integer> inBs = this.input.getList();
		ArrayList<Integer> curBs = bs.getList();
		
		Move m = new Move();

		int idxHolder;
		for (int i = 0 ; i < inBs.size(); i++) {
			idxHolder = tmpBs.indexOf(curBs.get(i));
			if (idxHolder != -1) {
				tmpBs.remove(idxHolder);
			} else {
				int cIdx = inBs.indexOf(idxHolder)
				
			}
		}
	}

	private void convertHelper() {}




}