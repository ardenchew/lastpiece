public class Player {
	
	public enum PLAYERTYPE {
		POSITIVE_ONLY,
		NEGATIVE_POSITIVE
	}

	private String name;
	private String color;
	private int points;
	private PLAYERTYPE ptype;

	Player(String name, String color, PLAYERTYPE ptype) {
		this.name = name;
		this.color = color;
		this.points = 0;
		this.ptype = ptype;
	}

	public int getPoints() {
		return this.points;
	}

	// returns new number of points
	public int addPoint() {
		this.points += 1;
		return this.points;
	}

	public int addPoints(int more) {
		this.points += more;
		return this.points;
	}

	public int removePoint() {
		if 
	}

	public int removePoints()

}