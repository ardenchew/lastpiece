import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class Board {
	
	public ArrayList<Column> boardList;
	public int[] boardDim;
	public int size;

	Board(int[] bd) {
		this.boardList = new ArrayList<Column>();
		this.resize(bd);
	}

	public Column get(int position) {
		return this.boardList.get(position);
	}

	public Column remove(int position) {
		Column temp = this.boardList.remove(position);
		this.size--;
		return temp;
	}

	public Packet remove(Packet p, Column c) {
		String colKey = c.getKey();
		for (int i = 0; i < this.size; i++) {
			if (this.boardList.get(i).getKey() == colKey) {
				Packet retPacket = this.boardList.get(i).remove(p);
				return p;
			}
		}
		return null;
	}

	public boolean has(int cIdx) {
		return (cIdx < this.size);
	}

	public boolean has(Column c) {
		String hasKey = c.getKey();
		for (int i =0; i < this.size; i++) {
			if (this.boardList.get(i).getKey() == hasKey) {
				return true;
			}
		}
		return false;
	}

	public boolean has(Packet p, Column c) {
		return c.has(p);
	}

	public boolean add(Column c) {
		this.size++;
		return this.boardList.add(c);
	}

	public void clear() {
		this.boardList.clear();
		this.size = 0;
	}

	public void reset() {
		this.clear();
		this.resize(this.boardDim);

	}

	public void resize(int[] bd) {
		this.clear();
		this.boardDim = bd;
		this.size = this.boardDim.length;

		for (int i = 0; i < this.boardDim.length; i++) {
			Column temp = new Column(i);
			temp.fill(this.boardDim[i]);
			this.boardList.add(temp);
		}
	}

	public boolean isEmpty() {
		return (this.getPacketNum() == 0);
	}

	public int size() {
		return this.size;
	}

	public int getPacketNum() {
		int count = 0;
		for (int i = 0; i < this.size; i++) {
			count += this.get(i).getPacketCount();
		}
		return count;
	}

}