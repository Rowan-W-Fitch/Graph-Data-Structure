import java.util.ArrayList;

public class Vertex implements IVertex {
	String value;
	ArrayList<Edge> neighbors = new ArrayList<Edge>();
	int x;
	int y;
	double totalDistance;
	boolean hasVisit;
	Vertex comeFrom;
	
	public Vertex() {
		//empty constructor
	}
	
	public Vertex(String val, int x, int y) {
		value = val;
		this.x = x;
		this.y = y;
	}
	
	public Vertex(String val) {
		value = val;
	}
	
	public void addEdge(Edge e) {
		neighbors.add(e);
	}
	
	public String toString() {
		return value;
	}

}
