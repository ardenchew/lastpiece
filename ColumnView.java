import java.util.Iterator;
import java.util.ArrayList;
//import java.util.List;

public class ColumnView {
	
	Column c;
	ArrayList<String> data;

	ColumnView(Column temp) {
		this.c = temp;
		this.data = new ArrayList<String>();
		Iterator<Packet> iter = this.c.iterator();
		Packet packetTemp;

		while (iter.hasNext()) {
			packetTemp = iter.next();
			this.data.add(packetTemp.getData());
		}
	}

	public ArrayList<String> getData() {
		return this.data;
	}

}