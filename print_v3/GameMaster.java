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
				System.out.print("\n" + game.getCurrentPlayer().getName() + ": ");
			}

			Input in = game.getCurrentPlayer().getInput(game.board);

			switch(in.type) {
				case USERINPUT_GAMECOMMAND:
				case CPUINPUT_GAMECOMMAND:
					game.handleInput(in);
					break;
				default:
					break;
			}
		}
	}

	public static boolean checkRestart() {
		Scanner sc = new Scanner(System.in);

		System.out.print("To restart the game input 'restart', otherwise hit <enter>: ");
		String toRestart = sc.nextLine();
		toRestart = toRestart.toLowerCase();
		toRestart = toRestart.replace(" ", "");
		if (toRestart.equals("restart")) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<Player> setPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player userPlayer = new UserPlayer("User Player");  //TODO PLAYER ADJUSTMENT
		Player cpuPlayer = new ComputerPlayer("Computer Player"); //TODO PLAYER ADJUSTMENT
		players.add(userPlayer); //TODO PLAYER ADJUSTMENT
		players.add(cpuPlayer); //TODO PLAYER ADJUSTMENT	
		return players;
	}

	public static void handleAppCommand(Input in) {
		return; //TODO
	}

}