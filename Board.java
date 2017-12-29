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

	public void add(Column c) {
		this.boardList.add(c);
	}

	public Column get(int position) throws IllegalArgumentException {
		if (position >= this.size()) {
			throw new IllegalArgumentException();
		}

		Column c = this.boardList.get(position);
		return c;
	}

	public Packet get(int columnPosition, int rowPosition) throws IllegalArgumentException {
		Column c = this.get(columnPosition);
		Packet p = c.get(rowPosition);

		return p;
	}

	public Packet remove(int columnPosition, int rowPosition) throws IllegalArgumentException {
		Column c = this.get(columnPosition);
		Packet p = c.get(rowPosition);
		c.remove(rowPosition);
		return p;
	}

	public void add(int columnPosition, Packet p) throws IllegalArgumentException {
		Column c = this.get(columnPosition);
		c.add(p);
	}

	public Packet set(int columnPosition, int rowPosition, Packet p) throws IllegalArgumentException {
		Column c = this.get(columnPosition);
		Packet temp = c.get(rowPosition);
		c.set(rowPosition, p);
		return temp;
	}

	public boolean isEmpty() {
		
	}
}