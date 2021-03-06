import java.util.Scanner;

public class GameMaster {

	public Scanner sc = new Scanner(System.in);
	public PacketGameMaster game;

	public void main(String[] args) {

		int[] boardSize = {7, 5, 3, 1};
		this.game = new PacketGameMaster(boardSize);
		this.game.printWelcome();

		while (!(this.checkGameOver())) {

			System.out.print(this.game.getCurrentPlayer().getName() + ": ");
			UserInput in = this.getUserInput();

			switch(in.type) {
				case UserInput.USERINPUTTYPE.USERINPUT_GAMECOMMAND:
					this.game.handleInput(in);
				case UserInput.USERINPUTTYPE.USERINPUT_APPCOMMAND:
					this.handleAppCommand(in);
				default:
					break;
			}
		}
	}

	public void restart() {
		return; //TODO
	}

	public UserInput getUserInput() {
		String in = this.sc.nextLine();
		UserInput ui = new UserInput(UserInput.INPUTTYPE.USERINPUT_GAMECOMMAND, in);
		return ui;
	}

	public boolean checkGameOver() {
		return this.game.isGameOver();
	}

	public void handleAppCommand(UserInput in) {
		return; //TODO
	}

}