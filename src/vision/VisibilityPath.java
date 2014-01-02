package vision;

import java.util.ArrayList;

public class VisibilityPath extends ArrayList<SvgPathItem>{

	public void push(char id, int x, int y) {
		this.add(new SvgPathItem(id, x, y));
	}

	public void push(char id, double x, double y) {
		this.add(new SvgPathItem(id, x, y));
	}

	public void push(String id, double x, double y) {
		this.add(new SvgPathItem(id.charAt(0), x, y));
	}
	
}
