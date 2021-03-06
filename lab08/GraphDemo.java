package lab08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * An example of using the Graph class.
 * 
 * @author Erin Parker
 * @version March 1, 2019
 */
public class GraphDemo {

	  public static void main(String[] args) {

	    // build a sample graph
	    Graph g = new Graph();
	    g.addEdge("u0", "u2", 13); // u0 --> u2
	    g.addEdge("u0", "u4", 16);
	    g.addEdge("u0", "u5", 8);
	    g.addEdge("u1", "u3", 6);
	    g.addEdge("u3", "u2", 14);
	    g.addEdge("u4", "u3", 5);
	    g.addEdge("u5", "u1", 10);
	    g.addEdge("u5", "u2", 11);
	    g.addEdge("u5", "u3", 17);
	    g.addEdge("u5", "u4", 7);

	    g.dijkstras("u0");
	    System.out.println();

	    // build another sample graph
	    g = new Graph();
	    g.addEdge("s", "v1", 3);
	    g.addEdge("s", "v2", 6);
	    g.addEdge("s", "v7", 1);
	    g.addEdge("v1", "v2", 2);
	    g.addEdge("v2", "v3", 1);
	    g.addEdge("v3", "v4", 6);
	    g.addEdge("v3", "v5", 3);
	    g.addEdge("v3", "v6", 4);
	    g.addEdge("v4", "v5", 3);
	    g.addEdge("v5", "v6", 2);
	    g.addEdge("v7", "v1", 2);
	    g.addEdge("v7", "v6", 10);

	    g.dijkstras("s");
	  }
	  
	  public static void generateRandomDotFile(String filename, int vertexCount) {
		    PrintWriter out = null;
		    try {
		      out = new PrintWriter(filename);
		    } 
		    catch (IOException e) {
		      System.out.println(e);
		    }

		    Random rng = new Random();

		    // randomly construct either a graph or a digraph
		    String edgeOp = "--";
		    if (rng.nextBoolean()) {
		      out.print("di");
		      edgeOp = "->";
		    }
		    out.println("graph G {");

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using 2 * |V| edges
		    for(int i = 0; i < 2 * vertexCount; i++)
		      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp
		          + vertex[rng.nextInt(vertexCount)]);

		    out.println("}");
		    out.close();
		  }
}