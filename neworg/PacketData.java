import java.lang.NumberFormatException;

public class PacketData {
	
	public String columnKey;
	public String packetKey;
	public Int columnIdx;
	public Int rowIdx;

	public PacketData(Packet p, Column c) {
		this.columnKey = c.getKey();
		this.packetKey = p.getKey();
	}

	public PacketData(String s) {
		if (this.isValidInputString) {
			this.packetKey = s.split("b")[0];
			this.columnKey = s.split(this.packetKey)[1];
		} else {
			System.err.println("Invalid input.");
		}
	}

	private boolean isValidInputString(String s) {
		if (!(s.split("p")[0].equals(""))) {
			return false;
		}
		if (s.split("p")[1].split("b").length != 2) {
			return false;
		}
		try {
			Integer.valueOf(s.split("p")[1].split("b")[0]);
			Integer.valueOf(s.split("p")[1].split("b")[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	
}