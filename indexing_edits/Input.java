public class Input {

	public enum INPUTTYPE {
		USERINPUT_APPCOMAND,
		USERINPUT_GAMECOMMAND,
		CPUINPUT_GAMECOMMAND
	}

	public INPUTTYPE type; 
	public String data;
	public Move move;

	public Input(INPUTTYPE I) {
		this.type = I;
	}

	public Input(INPUTTYPE I, String s) throws IllegalArgumentException {
		if (I != INPUTTYPE.USERINPUT_GAMECOMMAND) {
			throw new IllegalArgumentException("Illegal input type.");
		}
		this.type = I;
		this.data = s.toLowerCase(); //keep user input in lower case for consistency
	}

	public Input(INPUTTYPE I, Move m) throws IllegalArgumentException {
		if (I != INPUTTYPE.CPUINPUT_GAMECOMMAND) {
			throw new IllegalArgumentException("Illegal input type.");
		}

		this.type = I;
		this.move = m;
	}

}