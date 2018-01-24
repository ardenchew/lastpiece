public abstract class UserInput {

	public enum INPUTTYPE {
		USERINPUT_APPCOMAND,
		USERINPUT_GAMECOMMAND
	}

	public INPUTTYPE type; 
	public String data;

	public UserInput(INPUTTYPE I) {
		this.type = I;
	}

	public UserInput(INPUTTYPE I, String s) throws IllegalArgumentException {
		if (I != INPUTTYPE.USERINPUT_GAMECOMMAND) {
			throw new IllegalArgumentException("Illegal UserInput type.");
		}
		this.type = I;
		this.data = s.toLowerCase(); //keep user input in lower case for consistency
	}

}