import java.util.Iterator;
import java.util.ArrayList;
//import java.util.List;

public class BoardView {

	Board b;
	String data;

	BoardView(Board temp) {
		this.b = temp;
		this.data = this.toString(this.b);
	}

	public String toString(Board b) {
		String s = "[";
		Iterator<Column> iter = this.b.iterator();
		Column c;
		ColumnView cv;
		boolean editEnd = false;
		while (iter.hasNext()) {
			editEnd = true;
			c = iter.next();
			cv = new ColumnView(c);
			s += cv.getData() + ", ";
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