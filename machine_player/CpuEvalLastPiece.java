import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Random;

public abstract class CpuEvalLastPiece extends CpuEval {
	
	public ArrayList<Integer> input; //constructor defines board input
	public ArrayList<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> bestOptions  = new ArrayList<ArrayList<Integer>>();
	public Board board;
	public Move move;

	public CpuEvalLastPiece(Board b) {
		this.initiateInputArray();
		this.addBoardState(b);
		this.sortBoardState();
		this.board = b;

		this.getPossibleMoves();
		this.setBestMoves();
		this.chooseMove();

	}

	private void initiateInputArray() {
		this.input = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			this.input.add(0);
		}
	}

	private void addBoardState(Board b) {
		ArrayList<Column> boardList = b.boardList;
		for (int i = 0; i < boardList.size(); i++) {
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
		int jIdx;
		for (int i = 0; i < 4; i++) {
			jIdx = this.input.get(i);
			for (int j = jIdx; j > 0; j--) {
				ArrayList<Integer> newAl = this.input;
				newAl.set(i, (jIdx - 1));
				this.options.add(newAl);
			}
		}
	}

	public void setBestMoves() {
		this.bestOptions.add(new ArrayList<Integer>(asList(1,0,0,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,1,1,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(2,2,0,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(3,2,1,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(5,4,1,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(3,3,0,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(4,4,0,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(5,5,0,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(2,2,1,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(3,3,1,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(5,5,1,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(4,4,1,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(6,4,3,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(6,5,2,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(6,5,3,0)));
		this.bestOptions.add(new ArrayList<Integer>(asList(6,4,2,0)));
	}

	public void chooseMove() {
		ArrayList<Integer> temp;
		for (int i = 0; i < this.options.size(); i++) {
			temp = this.options.get(i);
			for (int j = 0; j < this.bestOptions.size(); i++) {
				if (temp.equals(this.bestOptions.get(j))) {
					this.move = this.convertBoardStateMove(temp);
					return;
				}
			}
		}
		Random rand = new Random();
		int n = rand.nextInt(this.options.size());
		this.move = this.convertBoardStateMove(this.options.get(n));
	}

	public Move convertBoardStateMove(ArrayList<Integer> bs) {
		ArrayList<Integer> tmpBs = this.input;
		ArrayList<Integer> inBs = this.input;
		ArrayList<Integer> newBs = bs;
		
		Move m = new Move();

		int idxHolder;
		for (int i = 0 ; i < inBs.size(); i++) {
			idxHolder = tmpBs.indexOf(newBs.get(i));
			if (idxHolder != -1) {
				tmpBs.remove(idxHolder); 
			}
		}

		ArrayList<Column> boardList = this.board.boardList;
		int cIdx = -1;
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