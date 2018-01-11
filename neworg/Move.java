import java.util.ArrayList;

public class Move {

	//actual way of dealing with pieces, not the string
	// an array of pieces to remove from the board

	ArrayList packetList; //list of packets to remove

	public Move(ArrayList<Packet> pl) {
		this.packetList = pl;
	}

	public ArrayList getData() {
		return this.moveStr;
	}

	public boolean isMoveComplete() {
		return false; //TODO
	}
}

// running piece collection, add pice, remove piece, show board, show pieces to remove, complete