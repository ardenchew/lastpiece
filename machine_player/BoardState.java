import java.util.ArrayList;

public class BoardState {
	
	public ArrayList<Integer> boardState;

	public BoardState(Board b) {
		this.boardState = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			this.boardState.add(0);
		}
		ArrayList<Column> boardList = b.boardList;
		for (int i = 0; i < boardList.size(); i++) {
			this.boardState.set(i, boardList.get(i).getPacketCount());
		}
		this.sort();
	}

	public void sort() {
		int tmp;
		for (int i = 1; i < 4; i++) {
			for (int j = i; j > 0; j--) {
				if (this.boardState.get(j) < this.boardState.get(j-1)) {
					tmp = this.boardState.get(j);
					this.boardState.set(j, this.boardState.get(j-1));
					this.boardState.set(j-1, tmp);
				}
			}
		}
	}

	public ArrayList<Integer> getList() {
		return this.boardState;
	}

}