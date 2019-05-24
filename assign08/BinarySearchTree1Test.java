package assign08;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTree1Test {
	
	private ArrayList<String> list;

	@BeforeEach
	void setUp() throws Exception {
		list = new ArrayList<String>();;
		list.add("Hello");
		list.add("Bacon");
		list.add("Apple");
		list.add("Aaron");
	}

	@Test
	void test() {
		BinarySearchTree1<String> tree = new BinarySearchTree1<String>();
		assertTrue(tree.addAll(list));
		assertEquals(4, tree.size());
		assertTrue(tree.first().equals("Aaron"));
		String[] arr = new String[] {"Aaron", "Apple", "Bacon", "Hello"};
		assertArrayEquals(arr, tree.toArrayList().toArray());
		tree.add("Street");
		assertEquals(5, tree.size());
		assertTrue(tree.contains("Street"));
		assertFalse(tree.contains("boo"));
		assertTrue(tree.containsAll(list));
		assertEquals("Street", tree.last());
		
		tree.remove("Street");
		assertEquals(4, tree.size());
		assertEquals("Hello", tree.last());
		
		tree.removeAll(list);
		//System.out.println(tree.toString());
		assertTrue(tree.isEmpty());
		
		BinarySearchTree1<Integer> newTree = new BinarySearchTree1<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			newTree.add(i);
			array.add(i);
		}
		
		newTree.add(-1);
		System.out.println(newTree.last());
		//assertEquals((Integer)-1, newTree.first());
		
		assertEquals(1001, newTree.size());
		
	}

}
