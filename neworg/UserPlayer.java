import java.util.Scanner;

public class UserPlayer implements Player {
	
	public String name;

	public UserPlayer(String n) {
		this.name = n;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Move getMove() {
		Scanner sc = new Scanner(System.in);
		String moveStr = sc.nextLine();
		Move mv = new Move(moveStr);

		while (!this.isValidMove(mv)) {
			System.out.println("Not a valid move, try again: ");
			mv = this.getMove();
		}

		return mv;
	}

	public boolean isValidMove(Move mv) {
		return true; //TODO
	}

}