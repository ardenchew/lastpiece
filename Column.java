import java.util.ArrayList;
import java.util.List;

public class Column {
	
	public enum COLUMNTYPE {
		COLUMNTYPE_COLLAPSED,
		COLUMNTYPE_SPARSE
	}

	private final int size;
	private COLUMNTYPE typeColumn;
	private List<Packet> packetList;

	Column(int size, COLUMNTYPE cType) {
		this.size = size;
		this.typeColumn = cType;
		this.packetList = new ArrayList<Packet>();

		for (int i = 0; i < this.size; i++) {
			packetList.add(new Packet());
		}
	}

	public Packet get(int position) {
		return this.packetList.get(position);
	}

	public boolean has(int position) {
		return (position > this.size) && (packetList.get(position) != null);
	}

	public int size() {
		return this.packetList.size();
	}

	public Packet remove(int position) throws IllegalArgumentException {
		if (!this.has(position)) {
			throw new IllegalArgumentException();
		}

		Packet p = packetList.get(position);
		switch (this.typeColumn) {
			case COLUMNTYPE_COLLAPSED:
				packetList.remove(position);
				break;
			case COLUMNTYPE_SPARSE:
				packetList.set(position, null);
				break;
			default:
				break;
		}
		return p;
	}

	public void add(Packet p) throws IllegalArgumentException {
		if (this.typeColumn == COLUMNTYPE.COLUMNTYPE_SPARSE) {
			throw new IllegalArgumentException();
		}

		if (this.packetList.size() < this.size) {
			this.packetList.add(p);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Packet set(int position, Packet p) throws IllegalArgumentException {
		if (this.typeColumn == COLUMNTYPE.COLUMNTYPE_COLLAPSED) {
			throw new IllegalArgumentException();
		}

		if (position >= this.size) {
			throw new IllegalArgumentException();
		}

		Packet temp = packetList.get(position);
		this.packetList.set(position, p);
		return temp;
	}

}