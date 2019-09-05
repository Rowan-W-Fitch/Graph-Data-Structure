
public class Edge implements IEdge {
	double weight;
	Vertex source;
	Vertex dest;
	
	public Edge() {
		// empty constructor
	}
	
	public Edge(Vertex src, Vertex end) {
		source = src;
		dest = end;
	}
	
	public String toString() {
		String str = "(" +source.toString() + " - " + dest.toString() + ")";
		return str;
	}

}
