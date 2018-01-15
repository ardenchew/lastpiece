import java.util.Iterator;

public class ColumnView {
	
	public String data;

	public ColumnView(Column c) {
		this.constructHelper(c);
	}

	private void constructHelper(Column c) {
		this.data = c.getKey() + ": [ ";
		Iterator<Packet> iter = c.iterator();
		while (iter.hasNext()) {
			this.data += iter.next().getKey() + " ";
		}
		this.data += "]";
	}

	public String getData() {
		return this.data;
	}

	public void view() {
		System.out.println(this.data);
	}

	public void removeUpdate(int pIdx) {
		String remPac = "p" + pIdx;
		this.data.replace(remPac, "  ");
	}

}