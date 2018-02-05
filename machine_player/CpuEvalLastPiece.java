import java.util.ArrayList;

public abstract class CpuEvalLastPiece {
	
	public ArrayList<Integer> input; //constructor defines board input
	public ArrayList<ArrayList<Integer>> options;
	public Board board;
	public Move move;

	public CpuEvalLastPiece(Board b) {
		this.initiateInputArray();
		this.addBoardState(b);
		this.sortBoardState();
		this.board = b;

		Move m = this.convertBoadStateMove(this.chooseMove());

	}

	private void initiateInputArray() {
		this.input = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			this.input.add(0);
		}
	}

	private void addBoardState(Board b) {
		ArrayList<Column> boardList = b.boardList;
		for (int i = 0); i < boardList.size(); i++) {
			this.input.set(i, boardList.get(i).getPacketCount());
		}
	}

	private void sortBoardState() {
		int tmp;
		for (int i = 1; i < 4; i++) {
			for (int j = i; j > 0; j--) {
				if (this.input.get(j) < this.input.get(j-1)) {
					tmp = this.input.get(j);
					this.input.set(j, this.input.get(j-1));
					this.input.set(j-1, tmp);
				}
			}
		}
	}

	public void getPossibleMoves() {
		return;
	}

	public void chooseMove() {
		return;
	}

	public Move convertBoadStateMove(ArrayList<Integer> bs) {
		ArrayList<Integer> tmpBs = this.input;  {3}
		ArrayList<Integer> inBs = this.input;  {7, 5, 3, 1}
		ArrayList<Integer> newBs = bs; {7, 5, 1, 1}
		
		Move m = new Move();

		int idxHolder;
		for (int i = 0 ; i < inBs.size(); i++) {
			idxHolder = tmpBs.indexOf(newBs.get(i));
			if (idxHolder != -1) {
				tmpBs.remove(idxHolder); 
			}
		}

		ArrayList<Column> boardList = this.board.boardList;
		int cIdx;
		for (int i = 0; i < boardList.size(); i++) {
			if (boardList.get(i).getPacketCount() == tmpBs.get(0)) {
				cIdx = i;
			}
		}

		idxHolder = inBs.indexOf(tmpBs.get(0));
		int numToChange = inBs.get(idxHolder) - newBs.get(idxHolder);
		Column c = boardList.get(cIdx);

		for (int i = 0; (i < c.getSize()) && (numToChange > 0); i++) {
			Packet p = c.get(i);
			if (p != null) {
				m.add(i, cIdx);
				numToChange--;
			}
		}

		return m;

	}

	public Move getMove() {
		return this.move;
	}


}