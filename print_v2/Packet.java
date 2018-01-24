public class Packet {

	public String key; //a unique identifier for each packet
	public PacketData packetData = new PacketData();

	public Packet(int pac) {
		this.key = "p" + pac;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String k) {
		this.key = k;
	}

}