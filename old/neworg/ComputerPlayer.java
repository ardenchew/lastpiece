public class ComputerPlayer extends Player {
	
	public String name;

	public ComputerPlayer(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public Move getMove() {
		return new Move("temp"); //TODO
	}

}