import java.util.Iterator;

public class ColumnView {
	
	public String key;
	public String data;

	public ColumnView(Column c) {
		this.key = c.getKey();
		this.constructHelper(c);
	}

	private void constructHelper(Column c) {
		this.data = this.key + ": [ ";
		Iterator<Packet> iter = c.iterator();
		while (iter.hasNext()) {
			this.data += iter.next().getKey() + " ";
		}
		this.data += "]";
	}

	public String getKey() {
		return this.key;
	}

	public String getData() {
		return this.data;
	}

	public void view() {
		System.out.println(this.data);
	}

	public void removeUpdate(Packet p) {
		this.data.replace(p.getKey(), "  ");
	}

}