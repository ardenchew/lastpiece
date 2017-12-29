public class GameMaster {

	private Board gameBoard;
	private int[] setupSize;

	GameMaster() {}

	public void setSize(int[] setupArray) {
		this.setupSize = setupArray;
	}

	public void setup() {
		for (int n : setupArray) {
			gameBoard.add(new Column(n, Column.COLUMNTYPE.COLUMNTYPE_COLLAPSED));
		}
	}
}