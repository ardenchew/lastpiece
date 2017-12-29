import java.util.ArrayList;
import java.util.List;

public class Column {
	

	private int size;

	Column(int size) {
		this.size = size;

		List<Packet> packetList = new ArrayList<Packet>();
		for (int i = 0; i < this.size; i++) {
			packetList.add(new Packet());
		}
	}

	public Packet get(int position) {
		return this.packetList.get(position);
	}

	public boolean has(int position) {
		return (position > this.size);
	}

	public int size() {
		return this.size;
	}

	public abstract Packet remove(int position) {}

}