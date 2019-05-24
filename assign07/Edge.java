package assign07;



/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Edge<Type>{

	// destination of this directed edge
	private Vertex<Type> destination;

	public Edge(Vertex<Type> destination) {
		this.destination = destination;
	}

	public Vertex<Type> getOtherVertex() {
		return this.destination;
	}

	public String toString() {
		return this.destination.getValue().toString();
	}
}