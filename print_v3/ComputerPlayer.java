public class ComputerPlayer extends Player {
	
	public String name;

	public ComputerPlayer(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public Input getInput(Board b) {
		CpuEvalLastPiece_Easy celp = new CpuEvalLastPiece_Easy(b);
		return new Input(Input.INPUTTYPE.CPUINPUT_GAMECOMMAND, celp.getMove()); //TODO
	}

}