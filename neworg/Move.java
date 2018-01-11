import java.util.ArrayList;

public class Move {

	//actual way of dealing with pieces, not the string
	// an array of pieces to remove from the board

	ArrayList packetList; //list of packets to remove
	boolean complete; // not using for now

	public Move(ArrayList<Packet> pl) {
		this.packetList = pl;
		this.complete = true; //default setting for now
	}

	public ArrayList<Packet> getData() {
		return this.packetList;
	}

	public boolean isMoveComplete() {
		return this.complete;
	}

	public void markAsComplete() {
		this.complete = false;
	}

	public void markAsIncomplete() {
		this.complete = true;
	}
}

// running piece collection, add pice, remove piece, show board, show pieces to remove, complete