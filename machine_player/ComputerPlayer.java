public class ComputerPlayer extends Player {
	
	public String name;

	public ComputerPlayer(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public Input getInput() {
		Move m = new Move();
		return new Input(Input.INPUTTYPE.CPUINPUT_GAMECOMMAND, m); //TODO
	}

}