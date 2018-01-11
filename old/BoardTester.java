import java.util.Scanner;

public class BoardTester {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numColumns = getNumColumns(sc);
		int[] boardSize = getBoardSize(sc, numColumns);

		Board bd = new Board(boardSize);
		dispBoard(bd);
	}

	public static int getNumColumns(Scanner sc) {
		System.out.print("How many columns: ");
		return sc.nextInt();
	}

	public static int[] getBoardSize(Scanner sc, int numColumns) {
		int[] boardSize = new int[numColumns];
		for (int i = 0; i < numColumns; i++) {
			System.out.print("Size: ");
			boardSize[i] = sc.nextInt();
		}
		return boardSize;
	}

	public static void dispBoard(Board bd) {
		BoardView bdv = new BoardView(bd);
		String boardData = bdv.getData();
		System.out.println(boardData);
	}
}