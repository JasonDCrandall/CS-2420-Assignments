package assign06;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a tester class to test the methods in the SinglyLinkedList
 * class
 * 
 * @author Jason Crandall && Michael Hart
 * @version February 28, 2019
 */
class SinglyLinkedListTest {
	
	private SinglyLinkedList list, smallList, medList;
	
	@BeforeEach
	void setUp() {
		list = new SinglyLinkedList<Integer>();
		smallList = new SinglyLinkedList<Integer>();
		smallList.addFirst(2);
		smallList.addFirst(5);
		smallList.addFirst(7);
		medList = new SinglyLinkedList<Integer>();
		medList.addFirst(4);
		medList.addFirst(7);
		medList.addFirst(0);
		medList.addFirst(12);
		medList.addFirst(76);
		medList.addFirst(5);
		medList.addFirst(4);
		medList.addFirst(34);
		medList.addFirst(6);
		medList.addFirst(1);
		medList.addFirst(8);
	}

	@Test
	void addFirstListTest() {
		list.addFirst(3);
		assertEquals(3, list.getFirst());
	}
	
	@Test
	void addFirstSmallListTest() {
		smallList.addFirst(45);
		assertEquals(45, smallList.getFirst());
		assertEquals(7, smallList.get(1));
	}
	
	@Test
	void addSmallListTest() {
		smallList.add(2, 4);
		assertEquals(4, smallList.get(2));
		assertEquals(4, smallList.size());
	}
	
	@Test
	void addOutOfBoundsTest() {
		assertThrows(IndexOutOfBoundsException.class , () -> medList.add(-1, 5));
	}
	
	@Test
	void getOutOfBoundsTest() {
		assertThrows(IndexOutOfBoundsException.class , () -> medList.get(52));
	}
	
	@Test
	void addSmallListIndexOneTest() {
		smallList.add(1, 4);
		assertEquals(4, smallList.get(1));
	}
	
	@Test
	void getSmallListTest() {
		assertEquals(7, smallList.get(0));
		assertEquals(5, smallList.get(1));
		assertEquals(2, smallList.get(2));
	}
	
	@Test
	void getFirstSmallListTest() {
		assertEquals(7, smallList.getFirst());
	}
	
	@Test
	void emptyGetFirstTest() {
		assertThrows(NoSuchElementException.class, () -> list.getFirst());
	}
	
	@Test
	void removeFirstSmallListTest() {
		smallList.removeFirst();
		assertEquals(5, smallList.getFirst());
	}

	@Test
	void removeFirstListTest() {
		list.addFirst(1);
		list.removeFirst();
		assertTrue(list.isEmpty());
	}
	
	@Test
	void emptyRemoveFirstTest() {
		assertThrows(NoSuchElementException.class, () -> list.removeFirst());
	}
	
	@Test
	void removeSmallList() {
		smallList.addFirst(9);
		smallList.remove(2);
		assertEquals(2, smallList.get(2));
	}
	
	@Test
	void removeOutOfBoundsTest() {
		assertThrows(IndexOutOfBoundsException.class , () -> medList.remove(52));
	}
	
	@Test
	void removeSmallListIndexOne() {
		smallList.addFirst(9);
		smallList.remove(1);
		assertEquals(5, smallList.get(1));
	}
	
	@Test
	void indexOfTest() {
		assertEquals(0, smallList.indexOf(7));
		assertEquals(1, smallList.indexOf(5));
		assertEquals(2, smallList.indexOf(2));
	}
	
	@Test
	void smallListSizeTest() {
		assertEquals(3, smallList.size());
	}
	
	@Test
	void isEmptyTest() {
		assertTrue(list.isEmpty());
		assertFalse(smallList.isEmpty());
	}
	
	@Test
	void clearTest() {
		smallList.clear();
		assertTrue(smallList.isEmpty());
	}
	
	@Test
	void smallListToArrayTest() {
		Integer[] arr = new Integer[] {7,5,2};
		assertArrayEquals(arr, smallList.toArray());
	}
	
	@Test
	void iteratorTest() {
		Iterator<Integer> itr = medList.iterator();
		assertTrue(itr.hasNext());
		while(itr.hasNext()) {
			Integer element = itr.next();
			itr.remove();
		}
		assertTrue(medList.isEmpty());
	}
	
}
