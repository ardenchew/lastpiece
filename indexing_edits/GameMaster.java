import java.util.Scanner;
import java.util.ArrayList;

public class GameMaster {

	public static void main(String[] args) {

		ArrayList<Player> players = setPlayers();

		int[] boardSize = {7, 5, 3, 1};
		PacketGameMaster game = new PacketGameMaster(boardSize, players);
		game.printWelcome();

		while (!(game.isGameOver())) {

			if (game.getCurrentPlayer() instanceof UserPlayer) {
				System.out.print(game.getCurrentPlayer().getName() + ": ");
			}

			Input in = game.getCurrentPlayer().getInput();

			switch(in.type) {
				case USERINPUT_GAMECOMMAND:
				case CPUINPUT_GAMECOMMAND:
					game.handleInput(in);
					break;
				case USERINPUT_APPCOMMAND:
					handleAppCommand(in);
					break;
				default:
					break;
			}
		}
	}

	public static void checkRestart() {
		Scanner sc = new Scanner(System.in);

		System.out.print("To restart the game input 'restart', otherwise hit <enter>: ");
		String toRestart = sc.nextLine();
		toRestart = toRestart.toLowerCase();
		toRestart = toRestart.replace(" ", "");
		if (toRestart.equals("restart")) {
			restartGame = true;
		} else {
			restartGame = false;
		}
	}

	public static ArrayList<Player> setPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player userPlayer1 = new UserPlayer("User Player 1");  //TODO PLAYER ADJUSTMENT
		Player userPlayer2 = new UserPlayer("User Player 2"); //TODO PLAYER ADJUSTMENT
		players.add(userPlayer1); //TODO PLAYER ADJUSTMENT
		players.add(userPlayer2); //TODO PLAYER ADJUSTMENT	
		return players;
	}

	public static void handleAppCommand(Input in) {
		return; //TODO
	}

}