public class GameMaster {

	private Board gameBoard;

	GameMaster() {}

	public void setup(int[] setupArray) {
		for (int n : setupArray) {
			gameBoard.add(new Column(n, Column.COLUMNTYPE.COLUMNTYPE_COLLAPSED));
		}
	}
}