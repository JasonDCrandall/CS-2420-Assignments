package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Vertex<Type> implements Comparable {

	// used to id the Vertex
	private Type value;
	private boolean visited = false;
	private boolean visiting = false;
	private int inDegree = 0;

	// adjacency list
	private LinkedList<Edge<Type>> adjacent;

	public Vertex(Type name) {
		this.value = name;
		this.adjacent = new LinkedList<Edge<Type>>();
	}

	public Type getValue() {
		return value;
	}


	public Iterator<Edge<Type>> edges() {
		return adjacent.iterator();
	}

	public String toString() {
		String s = "Vertex " + value + " adjacent to ";
		Iterator<Edge<Type>> itr = adjacent.iterator();
		while (itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}

	public void addEdge(Vertex<Type> v) {
		for(Edge<Type> edge : adjacent) {
			if (edge.getOtherVertex().equals(v))
				return;
		}
		adjacent.add(new Edge<Type>(v));
		v.setInDegree(v.getInDegree() + 1);
	}


	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public LinkedList<Edge<Type>> getEdges() {
		return adjacent;
	}


	public boolean getVisited() {
		return visited;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	public boolean isVisiting() {
		return visiting;
	}

	public void setVisiting(boolean visiting) {
		this.visiting = visiting;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
}