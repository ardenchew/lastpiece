import java.util.ArrayList;

public class ColumnView {
	
	public String data;

	public ColumnView(Column c) {
		this.constructHelper(c);
	}

	private void constructHelper(Column c) {
		this.data = c.getKey() + ": [ ";
		for (int i = 0; i < c.getSize(); i++) {
			if (c.get(i) != null) {
				this.data += c.get(i).getKey() + " ";
			} else {
				this.data += "   ";
			}
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