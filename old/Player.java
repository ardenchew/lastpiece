public abstract class Player {
	
	public enum PLAYERTYPE {
		POSITIVE_ONLY,
		NEGATIVE_POSITIVE
	}

	private String name;
	private String color;
	private int points;
	private PLAYERTYPE ptype;
	private boolean hasMove;
	private Move moveCurrent;

	Player(String name, String color, PLAYERTYPE ptype) {
		this.name = name;
		this.color = color;
		this.points = 0;
		this.ptype = ptype;
	}

	public abstract Move getMove();

	public boolean hasMove() {
		return this.moveCurrent != NULL;
	}

	public String name() {
		return this.name;
	}

	public String color() {
		return this.color;
	}

	public String setColor(String newColor) {
		this.color = newColor;
		return this.color;
	}

	public int points() {
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
		this.points -= 1;
		if (this.points < 0) {
			switch (this.ptype) {
				case POSITIVE_ONLY:
					this.points = 0;
					break;
				case NEGATIVE_POSITIVE:
					break;
				default:
					break;
			}
		}
		return this.points;
	}

	public int removePoints(int less) {
		this.points -= less;
		if (this.points < 0) {
			switch (this.ptype) {
				case POSITIVE_ONLY:
					this.points = 0;
					break;
				case NEGATIVE_POSITIVE:
					break;
				default:
					break;
			}
		}
		return this.points;
	}

	public int setPoints(int set) {
		this.points = set;
		if (this.points < 0) {
			switch (this.ptype) {
				case POSITIVE_ONLY:
					this.points = 0;
					break;
				case NEGATIVE_POSITIVE:
					break;
				default:
					break;
			}
		}
		return this.points;
	}

}