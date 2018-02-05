import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Random;

public class CpuEvalLastPiece extends CpuEval {
	
	public ArrayList<Integer> input; //constructor defines board input
	public ArrayList<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> bestOptions  = new ArrayList<ArrayList<Integer>>();
	public Board board;
	public Move move;

	public CpuEvalLastPiece(Board b) {
		this.move = new Move();
		this.initiateInputArray();
		this.addBoardState(b);
		this.input = this.sortBoardState(this.input);
		this.board = b;

		this.getPossibleMoves();
		System.out.println(this.options);
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

	private ArrayList<Integer> sortBoardState(ArrayList<Integer> al) {
		int tmp;
		for (int i = 1; i < 4; i++) {
			for (int j = i; j > 0; j--) {
				if (al.get(j) < al.get(j-1)) {
					tmp = al.get(j);
					al.set(j, al.get(j-1));
					al.set(j-1, tmp);
				}
			}
		}
		return al;
	}

	public void getPossibleMoves() {
		int jIdx;
		for (int i = 0; i < 4; i++) {
			jIdx = this.input.get(i);
			for (int j = jIdx; j > 0; j--) {
				ArrayList<Integer> newAl = new ArrayList<Integer>();
				for (int k = 0; k < 4; k++) {
					newAl.add(this.input.get(k));
				}
				newAl.set(i, (j - 1));
				newAl = this.sortBoardState(newAl);
				this.options.add(newAl);
			}
		}
	}

	public void setBestMoves() {
		this.bestOptions.add(new ArrayList<Integer>(asList(0,0,0,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,1,1,1)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,0,2,2)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,1,2,3)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,1,4,5)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,0,3,3)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,0,4,4)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,0,5,5)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,1,2,2)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,1,3,3)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,1,5,5)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,1,4,4)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,3,4,6)));
		this.bestOptions.add(new ArrayList<Integer>(asList(1,2,5,6)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,3,5,6)));
		this.bestOptions.add(new ArrayList<Integer>(asList(0,2,4,6)));
	}

	public void chooseMove() {
		ArrayList<Integer> temp;
		for (int i = 0; i < this.options.size(); i++) {
			temp = this.options.get(i);
			for (int j = 0; j < this.bestOptions.size(); j++) {
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
		ArrayList<Integer> tmpBs = new ArrayList<Integer>();
		ArrayList<Integer> inBs = new ArrayList<Integer>();

		for (int i = 0; i < 4; i++) {
			tmpBs.add(this.input.get(i));
			inBs.add(this.input.get(i));
		}
		ArrayList<Integer> newBs = bs;

		System.out.println(newBs);
		
		Move m = new Move();

		int idxHolder;
		for (int i = 0 ; i < 4; i++) {
			idxHolder = tmpBs.indexOf(newBs.get(i));
			if (idxHolder != -1) {
				tmpBs.remove(idxHolder); 
			}
		}

		idxHolder = inBs.indexOf(tmpBs.get(0));
		int numToChange = inBs.get(idxHolder) - newBs.get(idxHolder);

		ArrayList<Column> boardList = this.board.boardList;
		int cIdx = -1;
		for (int i = 0; i < boardList.size(); i++) {
			if (boardList.get(i).getPacketCount() == tmpBs.get(0)) {
				cIdx = i;
			}
		}

		
		Column c = boardList.get(cIdx);
		for (int i = 0; (i < c.getSize()) && (numToChange > 0); i++) {
			Packet p = c.get(i);
			if (p != null) {
				m.add(i, cIdx);
				numToChange--;
			}
		}

		m.markAsComplete();
		return m;

	}

	public Move getMove() {
		return this.move;
	}


}