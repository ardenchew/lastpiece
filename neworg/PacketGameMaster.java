import java.util.ArrayList;

public class PacketGameMaster {
	
	Move currentMove;
	Board board;
	BoardView boardView;
	ArrayList players = new ArrayList<Player>();
	int isWhosTurn;

	public void PacketGameMaster() {
		this.board.reset(); //TODO
		this.boardView.setBoar(board); //TODO

		Player userPlayer = new UserPlayer("User Player 1");
		Player computerPlayer = new ComputerPlayer("Computer Player 1");
		this.players.add(userPlayer);
		this.players.add(computerPlayer);

	}

	public boolean HasMove() {
		return move.isMoveComplete(); //this should return false as the user is building up move and return true when user is ready to provide move
	}

	public void HandleInput(Move m) {
		isValidMove(in); //TODO
		//deals with users input (not a full move)
	}

	public boolean isValidMove(Move m)



}