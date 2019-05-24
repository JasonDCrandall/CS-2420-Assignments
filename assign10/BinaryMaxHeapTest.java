package assign10;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {
	
	BinaryMaxHeap<Integer> intHeap;
	List<Integer> list, emptyList;

	@BeforeEach
	void setUp() {
		list = new ArrayList<Integer>();
		emptyList = new ArrayList<Integer>();
		
		list.add(3);
		list.add(4);
		list.add(8);
	}

	@Test
	void heapSizeTest() {
		intHeap = new BinaryMaxHeap<Integer>();
		assertEquals(intHeap.size(), 0);
		
		intHeap = new BinaryMaxHeap<Integer>(list);
		assertEquals(intHeap.size(), 3);
		
		intHeap = new BinaryMaxHeap<Integer>(emptyList);
		assertEquals(intHeap.size(), 0);
	}

	@Test
	void heapAddTest() {
		intHeap = new BinaryMaxHeap<Integer>();
		intHeap.add(5);
		intHeap.add(3);
		intHeap.add(28);
		assertEquals(intHeap.size(), 3);
	}
	
	@Test	
	void heapPeekTest() {
		intHeap = new BinaryMaxHeap<Integer>();
		intHeap.add(5);
		intHeap.add(3);
		intHeap.add(28);
		assertTrue(intHeap.peek().equals(28));
		
		intHeap = new BinaryMaxHeap<Integer>(emptyList);
		assertThrows(NoSuchElementException.class, () -> intHeap.peek());
	}
	
	@Test
	void heapExtractMaxTest() {
		intHeap = new BinaryMaxHeap<Integer>();
		intHeap.add(5);
		intHeap.add(28);
		intHeap.add(3);
		assertTrue(intHeap.extractMax().equals(28));
		assertEquals(intHeap.size(), 2);
		assertTrue(intHeap.peek().equals(5));
		
		intHeap = new BinaryMaxHeap<Integer>(emptyList);
		assertThrows(NoSuchElementException.class, () -> intHeap.extractMax());
	}
	
	@Test
	void heapIsEmptyTest() {
		intHeap = new BinaryMaxHeap<Integer>(emptyList);
		assertTrue(intHeap.isEmpty());
		
		intHeap = new BinaryMaxHeap<Integer>(list);
		assertFalse(intHeap.isEmpty());
	}
	
	@Test
	void heapClearTest() {
		intHeap = new BinaryMaxHeap<Integer>(list);
		assertEquals(intHeap.size(), 3);
		intHeap.clear();
		assertEquals(intHeap.size(), 0);
	}
	
	@Test
	void heapToArrayTest() {
		list.add(16);
		list.add(2); 
		list.add(5);
		list.add(9);
		list.add(50);
		intHeap = new BinaryMaxHeap<Integer>(list);
		Object[] result = new Object[]{50, 16, 9, 4, 2, 5, 8, 3};
		System.out.println(Arrays.toString(intHeap.toArray()));
		//System.out.println(intHeap.extractMax());
		assertArrayEquals(intHeap.toArray(), result);
	}
}
