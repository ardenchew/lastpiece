public class GameMaster {

	private Board gameBoard;
	private int[] setupSize;

	GameMaster() {}

	public void setSize(int[] setupArray) {
		this.setupSize = setupArray;
	}

	public void setup() {
		Column tempC;
		for (int n : this.setupSize) {
			tempC = new Column(n, Column.COLUMNTYPE.COLUMNTYPE_COLLAPSED);
			tempC = this.fillEmpty(tempC);
			gameBoard.add(tempC));
		}
	}



	public Column fillEmpty(Column c) throws IllegalArgumentException {
		if (!c.isEmpty()) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < c.size(); i++) {
			c.add(new Packet());
		}
		return c;
	}


	public void Main () {

			


		while (event = UIGetEvent()) {
			switch (event.type) {
				case PAINT:
					gamemaster.Paint(event);
					break;

					case TAP:





			}

			gamemaster.CheckForMove();
			gamemaster.CheckGameOver();
		}
	}

	public void CheckForMove()
	{
		if (this.currentPlayer.hasMove())
		{				
			Move move = this.currentPlayer.GetMove();

		}
	}

	public boolean isMoveValid(Move move){

	}

	public void makeMove(Move move)
	{
		// check that the move is valid.  if it is, make the move and change the player.  otherwise reject
		// change currnet player
	}



}