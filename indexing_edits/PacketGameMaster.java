import java.util.ArrayList;

import java.lang.NumberFormatException;

public class PacketGameMaster {
	
	public Move currentMove;
	public Board board;
	public BoardView boardView;
	public ArrayList<Player> players; //TODO PLAYER ADJUSTMENT
	public int isWhosTurn;
	public Player winner; //TODO
	public boolean isGameOver;

	public void PacketGameMaster(int[] boardSize, ArrayList<Player> playerList) {
		this.board = new Board(boardSize);
		this.isGameOver = false;
		this.players = playerList;

		winner = this.players.get(1); //default for if user quits ?? should go somewhere else

	}

	public Player getCurrentPlayer() { //TODO PLAYER ADJUSTMENT
		return this.players.get(isWhosTurn);
	}

	public boolean isGameOver() {
		return this.isGameOver;
	}

	public boolean hasMove() {
		return this.currentMove.isMoveComplete(); //this should return false as the user is building up move and return true when user is ready to provide move
	}

	public void handleInput(Input in) {
		switch (in.type) {
			case USERINPUT_GAMECOMMAND:
				this.handleUserInput(in);
				break;
			case CPUINPUT_GAMECOMMAND:
				this.handleCpuInput(in);
				break;
		}
	}

	public void handleCpuInput(Input in) {
		this.completeMove(in.move);
	}

	public void handleUserInput(Input in) {
		String inString = in.data;
		String[] inStringList = inString.split(" ");

		switch (inStringList[0]) {
			case "help": 
				this.printHelp();
				break;
			case "quit":
				this.changeTurn();
				this.gameOver();
				break;
			case "restart":
				this.restartGame();
				break;
			case "board":
				this.printBoard();
				break;
			case "add": 
				if (inStringList.length == 2) {
					int[] addPiece = this.convertPiece(inStringList[1]);
					if (addPiece != null) {
						int pIdx = addPiece[0];
						int cIdx = addPiece[1];
						if (this.board.has(pIdx, cIdx)) {
							if(this.currentMove.add(pIdx, cIdx)) {
								break;
							}
						}
					}
				}
			case "remove":
				if (inStringList.length == 2) {
					int[] remPiece = this.convertPiece(inStringList[1]);
					if (remPiece != null) {
						int pIdx = remPiece[0];
						int cIdx = remPiece[1];
						if (this.board.has(pIdx, cIdx)) {
							if (this.currentMove.remove(pIdx, cIdx)) {
								break;
							}
						}
					}
				}
			case "complete":
				if (this.currentMove.markAsComplete()) {
					this.completeMove(this.currentMove);
					break;
				}
			case "selected": 
				System.out.println(this.currentMove.toString());
				break;
			default:
				System.out.println("Invalid input.");
				break;

		}

		//deals with users input (not a full move)
		//help, complete, restart, quit, add, remove,
	}

	public void completeMove(Move m) { //for a computer player use as well
		this.handleMove(m);
		this.printBoard();
		this.changeTurn();
		this.currentMove.reset();
		this.checkGameOver();
	}

	public void handleMove(Move m) {
		if (m.isMoveComplete()) {
			int cIdx = m.getColumnNum();
			ArrayList<Integer> removalPacketList = m.getPacketIdx();
			for (int pIdx = 0; pIdx < removalPacketList.size(); pIdx++) {
				this.board.remove(pIdx, cIdx);
				this.boardView.removeUpdate(pIdx, cIdx);
			}
		} else {
			System.err.println("Move is incomplete.");
		}
	}

	private int[] convertPiece(String piece) {
		int[] pieceInt = new int[2];

		String[] firstTest = piece.split("p");
		if (!(firstTest[0].equals(""))) {
			return null;
		}
		String[] secondTest = firstTest[1].split("b");
		if (secondTest.length != 2) {
			return null;
		}
		try {
			pieceInt[0] = Integer.valueOf(secondTest[0]);
			pieceInt[1] = Integer.valueOf(secondTest[1]);
			return pieceInt;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public void printHelp() {
		System.out.println("The following is a list of available commands:");
		System.out.println(" help        - print this help page");
		System.out.println(" quit        - quit game");
		System.out.println(" restart     - restart game");
		System.out.println(" add p4c2    - select piece 4 from column 2 to take");
		System.out.println(" remove p4c2 - deselect piece 4 from column 2");
		System.out.println(" complete    - end turn");
		System.out.println(" board       - show board");
		System.out.println(" selected       - show pieces selected");
	}

	public void changeTurn() {
		this.isWhosTurn = (this.isWhosTurn + 1) % 2;
	}

	public void printWinner() {
		System.out.println(this.winner.getName() + " wins!");
	}

	public void checkGameOver() {
		if (this.board.size == 0) {
			this.printWinner();
			this.gameOver();
		}
	}

	public void gameOver() {
		this.isGameOver = true;
	}

	public void restartGame() {
		this.board.reset();
		this.printBoard();
		this.isGameOver = false;
	}

	public void printWelcome() {
		System.out.println("Welcome to last piece!");
		System.out.println("The objective is to force the opposing player to take the last piece.");
		System.out.println("Taking turns remove pieces from a single column at a time (at least one piece). \n");
		this.printHelp();
		System.out.println("");
		this.printBoard();
		System.out.println("");
	}

	public void printBoard() {
		System.out.println("The current state of the board is: ");
		this.boardView.view();
	}



}