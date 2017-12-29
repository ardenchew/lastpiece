import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private List<Column> boardList;

	Board() {
		this.boardList = new ArrayList<Column>();
	}

	public int size() {
		return this.boardList.size();
	}

	public void insert(Column c) {
		this.boardList.add(c);
	}
}