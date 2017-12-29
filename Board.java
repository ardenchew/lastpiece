import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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

	public Column remove(int position) throws IllegalArgumentException {
		if (position >= this.size()) {
			throw new IllegalArgumentException();
		}
		Column c = this.boardList.remove(position);
		return c;
	}

	public Column get(int position) throws IllegalArgumentException {
		if (position >= this.size()) {
			throw new IllegalArgumentException();
		}

		Column c = this.boardList.get(position);
		return c;
	}

	public boolean has(Packet p) {
		Column c;
		for (int i = 0; i < this.size(); i++) {
			c = this.boardList.get(i);
			if (c.has(p)) {
				return true;
			}
		}
		return false;
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

	public Packet remove(Packet p) throws IllegalArgumentException {
		Column c;
		for (int i = 0; i < this.size(); i++) {
			c = this.boardList.get(i);
			if (c.has(p)) {
				Packet temp = c.remove(p);
				return temp;
			}
		}
		return null;

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
		Column c;
		for (int i = 0; i < this.size(); i++) {
			c = this.boardList.get(i);
			if (!c.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public Iterator<Column> iterator() {
		List<Column> fullList = new ArrayList<Column>();
		Column temp;

		for (int i = 0; i < this.size(); i++) {
			temp = this.boardList.get(i);
			fullList.add(temp);
		}
		return fullList.iterator();
	}

}