import java.util.ArrayList;

public class Move {

	//actual way of dealing with pieces, not the string
	// an array of pieces to remove from the board

	ArrayList<Packet> packetList; //list of packets to remove
	boolean complete; // not using for now
	int itemCount;
	Column currentColumn;


	public Move() {
		this.packetList = new ArrayList<Packet>();
		this.complete = false; //default setting for now
		this.itemCount = 0;
	}

	private String[] convert(String s) {
		String[] converted = new String[2];
		converted[0] = s.split("c")[0];
		converted[1] = s.split(converted[0])[1];
		return converted;
	}

	public boolean remove(Packet p, Column c) {
		if (this.currentColumn.getKey() != c.getKey()) {
			return false;
		}
		for (int i = 0; i < this.packetList.size(); i++) {
			 if (p.getKey() == this.packetList.get(i).getKey()) {
			 	this.packetList.remove(i);
			 	this.itemCount--;
			 	return true;
			 }
		}
		return false;
	}

	public boolean add(Packet p, Column c) {
		if (this.isValidAdd(p, c)) {
			if (this.itemCount == 0) {
				this.currentColumn = c;
			}
			this.packetList.add(p);
			this.itemCount++;
			return true;
		}
		return false;
	}

	public boolean isValidAdd(Packet p, Column c) {
		if ((this.itemCount != 0) && (this.currentColumn.getKey() != c.getKey())) {
			return false;
		}
		for (int i = 0; i < this.packetList.size(); i++) {
			if (this.packetList.get(i).getKey() == p.getKey()) {
				return false;
			}
		}
		return true;
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
		String s = this.currentColumn.getKey() + ": ";
		for (int i = 0; i < this.packetList.size(); i++) {
			s += this.packetList.get(i).getKey() + " ";
		}
		return s;
	}

}

// running piece collection, add pice, remove piece, show board, show pieces to remove, complete