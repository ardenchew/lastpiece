public class PacketView {
	
	public String key;

	public PacketView(Packet p) {
		this.key = p.getKey();
	}

	public void view() {
		System.out.print(this.key);
	}

	public String getKey() {
		return this.key;
	}

}