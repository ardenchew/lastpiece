public class Packet {

	public String key; //a unique identifier for each packet
	public PacketData packetData;

	public Packet(int pac) {
		this.pacNum = pac;
		this.key = "p" + this.pacNum;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String k) {
		this.key = k;
	}

	public int getPacketNum() {
		return this.pacNum;
	}

}