package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import assign03.SimplePriorityQueue;

/**
 * This class contains tests for SimplePriorityQueue
 * 
 * @author Michael Hart and Jason Crandall
 * @version January 31, 2019
 *
 */
public class SimplePriorityQueueTest {

	private static final Executable NoSuchElementException = null;
	private SimplePriorityQueue<Integer> spq1, spqSizeTen, spqSizeOne;
	private SimplePriorityQueue<String> spqString;
	private SimplePriorityQueue<Integer> spqComparator;
	
	@BeforeEach
	void setUp() throws Exception {
		// Simple priority queue that is empty (integer)
		spq1 = new SimplePriorityQueue<Integer>();
		// Simple priority queue with ten elements (integer)
		spqSizeTen = new SimplePriorityQueue<Integer>();
		for (int i = 0; i < 10; i++)
			spqSizeTen.insert(i);
		// Simple priority queue with one element (integer)
		spqSizeOne = new SimplePriorityQueue<Integer>();
		spqSizeOne.insert(1);
		// Simple priority queue of string elements 
		spqString = new SimplePriorityQueue<String>();
		// Simple priority queue using a comparator (integer)
		spqComparator = new SimplePriorityQueue<Integer>((i1, i2) -> i1.compareTo(i2));
	}

	@Test
	public void queueIsEmpty() {
		assertTrue(spq1.isEmpty());
	}

	@Test
	public void queueIsNotEmpty() {
		spq1.insert(1);
		assertFalse(spq1.isEmpty());
	}

	@Test
	public void queueClear() {
		spqSizeTen.clear();
		assertTrue(spqSizeTen.isEmpty());
	}

	@Test
	public void queueClearAlreadyEmpty() {
		spq1.clear();
		assertTrue(spq1.isEmpty());
	}

	@Test
	public void queueSizeEmpty() {
		assertEquals(0, spq1.size());
	}

	@Test
	public void queueSizeTen() {
		assertEquals(10, spqSizeTen.size());
	}

	@Test
	public void queueFindMinEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			spq1.findMin();
		});
	}

	@Test
	public void queueDeleteMinEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			spq1.deleteMin();
		});
	}

	@Test
	public void queueFindMinSizeTen() {
		assertEquals((Integer) 0, spqSizeTen.findMin());
	}

	@Test
	public void queueDelelteMinSizeTen() {
		spqSizeTen.deleteMin();
		assertEquals(9, spqSizeTen.size());
	}

	@Test
	public void queueDeleteAndReturnMin() {;
		assertEquals((Integer) 0, spqSizeTen.deleteMin());
		assertEquals(9, spqSizeTen.size());
	}

	@Test
	public void queueFindMinSizeOne() {
		spqSizeOne.findMin();
		assertEquals((Integer) 1, spqSizeOne.findMin());
	}

	@Test
	public void queueInsertOneComparableElement() {
		spqSizeOne.insert(1);
		assertEquals(spqSizeOne.findMin(), (Integer) 1);
		assertEquals(spqSizeOne.size(), 2);
	}

	@Test
	public void queueInsertComparableElement() {
		Integer x = -3;
		spq1.insert(4);
		spq1.insert(x);
		spq1.insert(2);
		assertEquals(3, spq1.size());
		assertEquals((Integer) x, spq1.findMin());
	}

	@Test
	public void queueInsertOneString() {
		spqString.insert("Hello");
		assertEquals(1, spqString.size());
	}

	@Test
	public void queueInsertComparableStrings() {
		spqString.insert("hello");
		spqString.insert("hi");
		spqString.insert("as");
		assertEquals((String) "as", spqString.findMin());
	}

	@Test
	public void queueInsertComparator() {
		spqComparator.insert(1);
		spqComparator.insert(18);
		spqComparator.insert(5);
		assertEquals((Integer) 1, spqComparator.findMin());
	}

	// TEST using the toArray() method
	@Test
	public void queueIsEqual() {
		String[] array = new String[16];
		array[0] = "hi";
		array[1] = "hello";
		array[2] = "as";
		spqString.insert("hello");
		spqString.insert("hi");
		spqString.insert("as");
		//assertTrue(Arrays.toString(array).equals(Arrays.toString(spqString.toArray())));

	}
	
	// TEST using the toArray() method
	@Test
	public void comparableInsertAll() {
		ArrayList<Integer> arr = new ArrayList<Integer>();

		for (int i = 15; i >= 0; i--) {
			arr.add(i);
		}

		spq1.insertAll(arr);

		assertFalse(spq1.isEmpty());
		//assertTrue((arr.toString()).equals(Arrays.toString(spq1.toArray())));
	}

}