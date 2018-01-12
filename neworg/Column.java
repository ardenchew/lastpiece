import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

// a simplified version of column
public class Column {
	
	public ArrayList<Packet> packetList;
	public String key;
	public int colNum;
	public int size;
	public int packetCount;

	public Column1(int col) {
		this.colNum = col;
		this.key = "c" + this.colNum;

		this.packetList = new ArrayList<Packet>();
		this.packetCount = 0;
		this.size = 0;

	}

	public boolean add(Packet p) {
		if this.has(p) {
			return false;
		}
		boolean addSuc = this.packetList.add(p);
		if (addSuc) {
			this.size = this.packetList.size();
			this.packetCount++;
	}

	public void clear() {
		this.packetList.clear();
		this.size = 0;
		this.packetCount = 0;
	}

	public boolean isEmpty() {
		return (this.packetCount == 0);
	}

	public boolean has(Packet p) {
		int idx = p.getPacketNum();
		return ((this.size > idx) && (this.packetList.get(idx) != null));
	}

	public Packet get(int position) {
		return this.packetList.get(position);
	}

	public boolean getKey() {
		return this.key;
	}

	public void setKey(String s) {
		this.key = s;
	}

	public int getSize() {
		return this.size;
	}

	public int getPacketCount() {
		return this.packetCount;
	}



}