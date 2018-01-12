import java.util.ArrayList;
import java.util.Scanner;

import java.lang.NumberFormatException;

public class PacketGameMaster {
	
	Move currentMove;
	Board board;
	BoardView boardView;
	ArrayList<Player> players = new ArrayList<Player>();
	int isWhosTurn;
	Player winner; //TODO

	ArrayList<Packet> removeList = new ArrayList<Packet>();

	public void PacketGameMaster() {
		this.board.reset(); //TODO
		this.boardView.setBoard(board); //TODO

		Player userPlayer = new UserPlayer("User Player 1");
		Player computerPlayer = new ComputerPlayer("Computer Player 1");
		this.players.add(userPlayer);
		this.players.add(computerPlayer);
		winner = this.players.get(1); //default for if user quits ?? should go somewhere else

		this.printWelcome();

	}

	public boolean HasMove() {
		return move.isMoveComplete(); //this should return false as the user is building up move and return true when user is ready to provide move
	}

	public void HandleInput(UserInput in) {
		String inString = in.data;
		String[] inStringList = inString.split(" ");
		int[] piece;

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
			case "add": //PACKET DATA CLASS TODO
				if (inStringList.length = 2) {
					piece = this.convertPiece(inStringList[1]);
					if (piece != null) {
						if (this.board.has(piece[0], piece[1])) {
							this.removeList.add(board.get(piece[0], piece[1]));
						}
					}
				}
			case "remove": //TODO
				if (inStringList.length = 2) {
					piece = this.convertPiece(inStringList[1]);
					if (piece != null) {
						if (this.removeList.has())

					}
				}
			case "complete":

			default:
				System.out.println("Invalid input.");
				break;

		}

		//deals with users input (not a full move)
		//help, complete, restart, quit, add, remove,
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
	}

	public boolean isValidInput(Move m) {}

	public boolean isValidMove(Move m){}

	public void changeTurn() {
		this.isWhosTurn = (this.isWhosTurn + 1) % 2;
	}

	public void gameOver() {
		Scanner sc = new Scanner(System.in);

		String winner = players.get(this.isWhosTurn).getName();
		System.out.println(winner + " wins!");
		System.out.println("The game is over. To quit hit <enter>, otherwise input 'restart'... ");

		String endString = sc.nextLine();
		if (endString.equals("")) {
			this.end();
		} else {
			this.restartGame();
		}
	}

	private void end() {
		//TODO
	}

	public void restartGame() {
		this.board.reset();
		this.board.resetBoard();
		this.printBoard();
	}

	public void printWelcome() {
		System.out.println("Welcome to last piece!");
		System.out.println("The objective is to force the opposing player to take the last piece.");
		System.out.println("Taking turns remove pieces from a single column at a time (at least one piece). \n");
		this.printHelp();
		System.out.println("");
		this.printBoard();
	}

	public void printBoard() {
		System.out.println("The current state of the board is: ");
		System.out.println(this.boardView.getData());
	}



}