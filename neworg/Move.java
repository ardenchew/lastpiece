public class Move {

	String moveStr; //should take pieces individually

	public Move(String s) {
		this.moveStr = s;
	}

	public String getData() {
		return this.moveStr;
	}

	public boolean isMoveComplete() {
		return false; //TODO
	}
}

// running piece collection, add pice, remove piece, show board, show pieces to remove, complete