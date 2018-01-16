import java.util.ArrayList;

public class Move {

	//actual way of dealing with pieces, not the string
	// an array of pieces to remove from the board

	ArrayList<Integer> packetList; //list of packets to remove
	boolean complete;
	int itemCount;
	int currentColumn;


	public Move() {
		this.packetList = new ArrayList<Integer>();
		this.complete = false;
		this.itemCount = 0;
	}

	public int getColumnNum() {
		return this.currentColumn;
	}

	public ArrayList<Integer> getPacketIdx() {
		return this.packetList;
	}

	public void reset() {
		this.packetList.clear();
		this.complete = false;
		this.itemCount = 0;
	}

	public boolean add(int pIdx, int cIdx) {
		if (this.valid(pIdx, cIdx)) {
			if (this.itemCount == 0) {
				this.currentColumn = cIdx;
			}
			this.packetList.add(pIdx);
			this.itemCount++;
			return true;
		}
		return false;
	}

	public boolean valid(int pIdx, int cIdx) {
		if (this.packetList.indexOf(pIdx) != -1) {
			return false;
		}
		if ((cIdx != this.currentColumn) && (this.itemCount != 0)) {
			return false;
		}
		return true;
	}

	public boolean remove(int pIdx, int cIdx) {
		if (this.valid(pIdx, cIdx)) {
			this.packetList.remove(this.packetList.indexOf(pIdx));
			this.itemCount--;
			return true;
		}
		return false;
	}

	public boolean isMoveComplete() {
		return this.complete;
	}

	public boolean markAsComplete() {
		if (this.itemCount == 0) {
			System.out.println("No pieces selected");
			return false;
		} else {
			this.complete = false;
			return true;
		}
	}

	public void markAsIncomplete() {
		this.complete = true;
	}

	public String toString() {
		if (this.itemCount == 0) {
			return "";
		}
		String s = "c" + this.currentColumn + ": ";
		for (int i = 0; i < this.packetList.size(); i++) {
			s += "p" + this.packetList.get(i) + " ";
		}
		return s;
	}

}

// running piece collection, add pice, remove piece, show board, show pieces to remove, complete