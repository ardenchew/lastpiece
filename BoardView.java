import java.util.Iterator;
import java.util.ArrayList;
//import java.util.List;

public class BoardView {

	Board b;
	ArrayList<ArrayList<String>> data;

	BoardView(Board temp) {
		this.b = temp;
		this.data = new ArrayList<ArrayList<String>>();
		Iterator<Column> iter = this.b.iterator();
		ColumnView cView;


		while(iter.hasNext()) {
			cView = new ColumnView(iter.next());
			this.data.add(cView.getData());
		}
	}

	public ArrayList<ArrayList<String>> getData() {
		return this.data;
	}
	
}