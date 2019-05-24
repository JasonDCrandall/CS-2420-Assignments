package lab08;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Vertex implements Comparable{

	// used to id the Vertex
	private String name;
	private Vertex prev;
	private double dist;
	private boolean visted = false;

	// adjacency list
	private LinkedList<Edge> adj;

	public Vertex(String name) {
		this.name = name;
		this.adj = new LinkedList<Edge>();
	}

	public String getName() {
		return name;
	}

//	public void addEdge(Vertex otherVertex) {
//		adj.add(new Edge(otherVertex));
//	}

	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	public String toString() {
		String s = "Vertex " + name + " adjacent to ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
	
	public void addEdge(Vertex v, double weight) {
		adj.add(new Edge(v, weight));
	}

	public double getDistanceFromStart() {
		return dist;
	}

	public void setDistanceFromStart(double dist) {
		this.dist = dist;
	}

	public Vertex getPrevious() {
		return prev;
	}

	public void setPrevious(Vertex prev) {
		this.prev = prev;
	}

	public boolean isVisited() {
		return visted;
	}

	public void setVisited(boolean visted) {
		this.visted = visted;
	}
	
	public LinkedList<Edge> getEdges() {
		return adj;
	}

	@Override
	public int compareTo(Object o) {
		
		return Double.compare(this.dist, ((Vertex) o).dist);
	}

	public boolean getVisited() {
		return visted;
	}
}