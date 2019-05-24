package assign07;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign07.Graph;
import assign07.GraphUtility;

class GraphTest {
	
	private ArrayList<String> source, dst;

//	@BeforeEach
//	void setup() throws Exception {
//		source.add("a");
//		source.add("b");
//		source.add("e");
//		dst.add("e");
//		dst.add("e");
//		dst.add("f");
//	}
	
	@Test
	public void graphTest() {
		ArrayList<Integer> source = new ArrayList<Integer>();
		source.add(1);
		//source.add(2);
		ArrayList<Integer> dest = new ArrayList<Integer>();
		//dest.add(2);
		dest.add(1);
		//assertEquals(source.size(), dest.size());
		//assertTrue(GraphUtility.areConnected(source,dest,1, 2));
		assertTrue(GraphUtility.isCyclic(source, dest));
		
	}
	
	@Test
	public void cyclicTest() {
		ArrayList<Integer> sources = new ArrayList<Integer>();
		sources.add(1);
		sources.add(3);
		sources.add(4);
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		destinations.add(2);
		destinations.add(4);
		destinations.add(3);
		assertTrue(GraphUtility.isCyclic(sources, destinations));
	}
	
	@Test
	public void stringTest() {
		ArrayList<String> source = new ArrayList<String>();
		source.add("a");
		source.add("b");
		source.add("e");
		ArrayList<String> dst = new ArrayList<String>();
		dst.add("e");
		dst.add("e");
		dst.add("b");
		assertTrue(GraphUtility.areConnected(source, dst, "a", "e"));
		assertTrue(GraphUtility.isCyclic(source, dst));
	}

}
