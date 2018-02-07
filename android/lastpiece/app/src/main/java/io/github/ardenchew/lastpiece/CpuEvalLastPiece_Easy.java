package io.github.ardenchew.lastpiece;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;

public class CpuEvalLastPiece_Easy extends CpuEval {
	
	public ArrayList<Integer> input; //constructor defines board input
	public ArrayList<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> bestOptions  = new ArrayList<ArrayList<Integer>>();
	public Board board;
	public Move move;

	public CpuEvalLastPiece_Easy(Board b) {
		this.move = new Move();
		this.initiateInputArray();
		this.addBoardState(b);
		this.input = this.sortBoardState(this.input);
		this.board = b;

		this.getPossibleMoves();
		this.setBestMoves();
		this.chooseMove();

	}

	public void initiateInputArray() {
		this.input = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			this.input.add(0);
		}
	}

	public void addBoardState(Board b) {
		ArrayList<Column> boardList = b.boardList;
		for (int i = 0; i < boardList.size(); i++) {
			this.input.set(i, boardList.get(i).getPacketCount());
		}
	}

	public ArrayList<Integer> sortBoardState(ArrayList<Integer> al) {
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
		this.chooseRandomMove();
	}

	public void chooseRandomMove() {
		Random rand = new Random();
		int n = rand.nextInt(this.options.size());
		this.move = this.convertBoardStateMove(this.options.get(n));
	}

	public Move convertBoardStateMove(ArrayList<Integer> bs) {
		ArrayList<Integer> inBs1 = new ArrayList<Integer>(); 
		ArrayList<Integer> inBs2 = new ArrayList<Integer>(); 
		ArrayList<Integer> newBs1 = bs; 
		ArrayList<Integer> newBs2 = new ArrayList<Integer>(); 

		for (int i = 0; i < 4; i++) {
			inBs1.add(this.input.get(i));
			inBs2.add(this.input.get(i));
			newBs2.add(newBs1.get(i));
		}
		
		Move m = new Move();

		int removal1;
		int removal2;
		for (int i = 0; i < 4; i++) {
			removal1 = newBs1.get(i);
			removal2 = inBs2.get(i);
			if (inBs1.indexOf(removal1) != -1) {
				inBs1.remove(inBs1.indexOf(removal1));
			}
			if (newBs2.indexOf(removal2) != -1) {
				newBs2.remove(newBs2.indexOf(removal2));
			}
		}

		int first = inBs1.get(0);
		int second = newBs2.get(0);
		int difference = first - second;

		ArrayList<Column> boardList = this.board.boardList;
		int cIdx = -1;
		for (int i = 0; i < boardList.size(); i++) {
			if (boardList.get(i).getPacketCount() == first) {
				cIdx = i;
			}
		}
		
		Column c = boardList.get(cIdx);
		Packet p;
		for (int i = 0; (i < c.getSize()) && (difference > 0); i++) {
			p = c.get(i);
			if (p != null) {
				m.add(i, cIdx);
				difference--;
			}
		}

		m.markAsComplete();
		return m;

	}

	public Move getMove() {
		return this.move;
	}


}