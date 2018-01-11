public class PacketView {
	
	Packet p;
	String data;

	PacketView(Packet temp) {
		this.p = temp;
		this.data = this.p.getData();
	}

	public String getData() {
		return this.data;
	}

}