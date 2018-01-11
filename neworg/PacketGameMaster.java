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


	}

	



}