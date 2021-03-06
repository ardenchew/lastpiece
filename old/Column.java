import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Column {
	
	public enum COLUMNTYPE {
		COLUMNTYPE_COLLAPSED,
		COLUMNTYPE_SPARSE
	}

	private final int size;
	private int packetCount;
	private COLUMNTYPE typeColumn;
	private List<Packet> packetList;

	Column(int size, COLUMNTYPE cType) {
		this.size = size;
		this.packetCount = 0;
		this.typeColumn = cType;
		this.packetList = new ArrayList<Packet>();

		if (this.typeColumn == COLUMNTYPE.COLUMNTYPE_SPARSE) {
			for (int i = 0; i < this.size; i++) {
				this.packetList.add(null);
			}
		}
	}

	public Packet get(int position) throws IllegalArgumentException {
		if (position >= this.size()) {
			throw new IllegalArgumentException();
		}

		return this.packetList.get(position);
	}

	public boolean has(int position) {
		return (position < this.packetList.size()) && (packetList.get(position) != null);
	}

	public boolean has(Packet p) {
		Packet temp;
		for (int i = 0; i < this.packetList.size(); i++) {
			temp = this.packetList.get(i);
			if (temp == p) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return this.size;
	}

	public int count() {
		return this.packetCount;
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
		if (p != null) {
			this.packetCount++;
		}
		return p;
	}

	public Packet remove(Packet p) throws IllegalArgumentException {
		Packet temp;
		for (int i = 0; i < this.packetList.size(); i++) {
			temp = this.packetList.get(i);
			if (temp == p) {
				return this.remove(i);
			}
		}
		throw new IllegalArgumentException();
	}

	public void add(Packet p) throws IllegalArgumentException {
		if (this.typeColumn == COLUMNTYPE.COLUMNTYPE_SPARSE) {
			throw new IllegalArgumentException();
		}

		if (p == null) {
			throw new IllegalArgumentException();
		}

		if (this.packetList.size() < this.size) {
			this.packetList.add(p);
		} else {
			throw new IllegalArgumentException();
		}
		this.packetCount++;
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
		if (temp == null) {
			this.packetCount++;
		}
		return temp;
	}

	public boolean isEmpty() {
		return (this.packetCount == 0);
	}

	public Iterator<Packet> iterator() {
		List<Packet> fullList = new ArrayList<Packet>();
		Packet temp;

		for (int i = 0; i < this.packetList.size(); i++) {
			temp = this.packetList.get(i);
			if (temp != null) {
				fullList.add(temp);
			}
		}
		return fullList.iterator();
	}

}