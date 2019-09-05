import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph implements IGraph {
	
	ArrayList<Vertex> vertices;
	
	public Graph() {
		vertices = new ArrayList<Vertex>();
	}

	@Override
	public boolean hasVertex(Vertex v) {
		boolean bool = false;
		if(vertices.contains(v)) bool = true;
		return bool;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public void addVertex(Vertex v) {
		if(hasVertex(v)) {
			throw new IllegalArgumentException("Graph already contains vertex");
		}
		vertices.add(v);
		
	}

	@Override
	public Vertex getVertex(String name) {
		for(Vertex v: vertices) {
			if(v.value.contentEquals(name)) return v;
			else continue;
		}
		return null;
	}

	@Override
	public void addEdge(String start, String dest, Double cost) {
		Edge edge = new Edge();
		edge.weight = cost;
		
		Vertex head = getVertex(start);
		//System.out.println("head: " + head);
		Vertex tail = getVertex(dest);
		//System.out.println("tail: " + tail);
		if(head == null || tail == null) return;
		
		edge.source = head;
		edge.dest = tail;
		//adding edge to source node
		head.addEdge(edge);
		head.totalDistance += cost;
	}

	@Override
	public void addUndirectedEdge(String start, String dest, Double cost) {
		Edge edge1 = new Edge();
		Edge edge2 = new Edge();
		
		edge1.weight = cost;
		edge2.weight = cost;
		
		Vertex head = getVertex(start);
		Vertex tail = getVertex(dest);
		
		if(head == null || tail == null) return;
		
		edge1.source = head;
		edge1.dest = tail;
		
		edge2.source = tail;
		edge2.dest = head;
		
		System.out.println(head);
		head.neighbors.add(edge1);
		head.totalDistance += cost;
		
		tail.neighbors.add(edge2);
		tail.totalDistance += cost;
	}

	@Override
	public int findClosestVertex(List<Vertex> list) {
		if(list.isEmpty()) {
			System.out.println("graph has no vertices");
			return 0;
		}
		double minVal = list.get(0).totalDistance;
		for(Vertex v: list) {
			minVal = Math.min(minVal,  v.totalDistance);
		}
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).totalDistance == minVal) return i;
		}
		return 0;
	}

	@Override
	public void dijkstra(String s) {
		ArrayList<Vertex> UnVisited = new ArrayList<Vertex>();
		Vertex current = null;
		//initializing all vertices w/ dist value of infinity and hasVisited as false
		for(Vertex v: vertices) {
			UnVisited.add(v);
			v.hasVisit = false;
			v.totalDistance = Double.POSITIVE_INFINITY;
			v.comeFrom = null;
		}
		//initializing source vertex
		Vertex source = getVertex(s);
		if(source == null) {
			System.out.println("Graph does not contain vertex " + s);
			return;
		}
		source.totalDistance = 0;
		//going through unvisited nodes
		while(!UnVisited.isEmpty()) {
			int indx = findClosestVertex(UnVisited); //finding node w/ least weight
			current = UnVisited.get(indx);
			if(current.totalDistance == Double.POSITIVE_INFINITY) return;
			UnVisited.remove(current);
			//updating weight for neighbors of current
			for(Edge e: current.neighbors) {
				Vertex v = e.dest;
				v.totalDistance = Math.min(v.totalDistance, current.totalDistance + e.weight);
				if(v.totalDistance == current.totalDistance + e.weight) {
					v.comeFrom = current;
				}
			} 
		}
		

	}

	@Override
	public List<Edge> getDijkstraPath(String start, String dest) {
		LinkedList<Edge> list = new LinkedList<Edge>();
		dijkstra(start);
		Vertex strt = getVertex(start);
		Vertex end = getVertex(dest);
		if(strt == null || end == null) return list;
		//System.out.println("end: " + end);
		
		while(end != strt) {
			for(Edge e: end.comeFrom.neighbors) {
				if(e.dest == end) list.addFirst(e);
				//else System.out.println(false);
			}
			end = end.comeFrom;
		}
		
		return list;
	}

	@Override
	public void printGraph() {
		if(vertices == null || vertices.size() == 0) return;
		for(Vertex v: vertices) {
			System.out.print(v.value +":");
			for(Edge e: v.neighbors) {
				System.out.print(e.toString());
			}
			System.out.println();
		}
	}

}
