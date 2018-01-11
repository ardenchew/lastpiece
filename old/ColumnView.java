import java.util.Iterator;
import java.util.ArrayList;
//import java.util.List;

public class ColumnView {
	
	Column c;
	String data;

	ColumnView(Column temp) {
		this.c = temp;
		this.data = this.toString(this.c);
	}

	public String toString(Column c) {
		String s = "[";
		Iterator<Packet> iter = this.c.iterator();
		Packet p;
		PacketView pv;
		boolean editEnd = false;
		while (iter.hasNext()) {
			editEnd = true;
			p = iter.next();
			pv = new PacketView(p);
			s += pv.getData() + ", ";
		}
		if (editEnd) {
			s = s.substring(0, (s.length() - 2));
		}
		s += "]";
		return s;
	}

	public String getData() {
		return this.data;
	}

}