package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;



/**
 * Represents a directed graph (a set of vertices and a set of edges). The graph
 * is not generic and assumes that a string name is stored at each vertex.
 * 
 * Dijkstra's shortest-path algorithm is included.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class Graph<Type> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	HashMap<Type, Vertex<Type>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	// TODO Change the comments
	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex
	 * with name "name2". The edge is associated with the "weight". If either vertex
	 * does not already exist in the graph, it is added.
	 */
	public void addEdge(Type value1, Type value2) {
		Vertex<Type> vertex1;
		if (vertices.containsKey(value1))
			vertex1 = vertices.get(value1);
		else {
			vertex1 = new Vertex<Type>(value1);
			vertices.put(value1, vertex1);
		}

		Vertex<Type> vertex2;
		if (vertices.containsKey(value2))
			vertex2 = vertices.get(value2);
		else {
			vertex2 = new Vertex<Type>(value2);
			vertices.put(value2, vertex2);
		}

		vertex1.addEdge(vertex2);
	}
	
	public void unsetAllVisited() {
		for (Vertex<Type> vertex : vertices.values()) {
			vertex.setVisited(false);
		}
	}
	
	public Vertex<Type> getVertex(Type key) {
		return vertices.get(key);
	}
	
	public boolean isCyclic() {
		for (Vertex<Type> v : vertices.values()) {
			if (!v.isVisited()) {
				if (containsCycle(v))
					return true;
			}
		}
		return false;
	}

	private boolean containsCycle(Vertex<Type> v) {
		v.setVisiting(true);
		Iterator<Edge<Type>> itr = v.edges();
		while(itr.hasNext()) {
			Vertex<Type> w = itr.next().getOtherVertex();
			if(!w.isVisited()) {
				if (w.isVisiting() || containsCycle(w))
					return true;
			}
		}
		v.setVisiting(false);
		v.setVisited(true);
		return false;
	}
	
}