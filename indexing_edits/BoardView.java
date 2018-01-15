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

	public void removeUpdate(int cIdx, int pIdx) {
		this.board.get(cIdx).removeUpdate(pIdx);
	}


}