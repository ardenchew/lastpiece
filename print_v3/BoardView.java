import java.util.ArrayList;

public class BoardView {
	
	public ArrayList<ColumnView> board;

	public BoardView(Board b) {
		this.board = new ArrayList<ColumnView>();
		int size = b.size();
		for (int i = 0; i < size; i++) {
			ColumnView temp = new ColumnView(b.get(i));
			this.board.add(temp);
		}
	}

	public void view() {
		for (int i = 0; i < this.board.size(); i++) {
			this.board.get(i).view();
		}
	}

	public void removeUpdate(int pIdx, int cIdx) {
		ColumnView temp = this.board.get(cIdx);
		temp.removeUpdate(pIdx);
		this.board.set(cIdx, temp);
		System.out.println("AHHAHA");
	}

	public void removeUpdate(int cIdx, Board b) {
		ColumnView temp = new ColumnView(b.get(cIdx));
		this.board.set(cIdx, temp);
	}

	public void reset(Board b) {
		this.board.clear();
		int size = b.size();
		for (int i = 0; i < size; i++) {
			ColumnView temp = new ColumnView(b.get(i));
			this.board.add(temp);
		}
	}


}