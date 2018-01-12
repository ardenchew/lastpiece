public class PacketView {
	
	public String key;
	public String data;

	public PacketView(Packet p) {
		this.key = p.getKey();
		this.data = p.getData();
	}

	public void view() {
		System.out.print(this.data);
	}

	public String getKey() {
		return this.key;
	}

	public String getData() {
		return this.data;
	}

}