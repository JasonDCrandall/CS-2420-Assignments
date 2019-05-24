package assign10;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class to determine the correctness of the FindKLargest class
 * 
 * @author Jason Crandall & Michael Hart
 * @version April 11, 2019
 *
 */
class FindKLargestTest {

	List<Integer> intList;
	List<String> stringList;
	
	@BeforeEach
	void setUp() {
		intList = new ArrayList<Integer>();
		intList.add(5);
		intList.add(12);
		intList.add(8);
		intList.add(1);
		intList.add(6);
		intList.add(2);
		
		stringList = new ArrayList<String>();
		stringList.add("banana");
		stringList.add("apple");
		stringList.add("kiwi");
		stringList.add("peach");
		stringList.add("pear");
	}
	
	@Test
	void findKLargestTestNoComparator() {
		Integer[] result1 = {12, 8, 6};
		assertArrayEquals(result1, FindKLargest.findKLargestHeap(intList, 3).toArray());
		assertArrayEquals(result1, FindKLargest.findKLargestSort(intList, 3).toArray());
		
		String[] result2 = {"pear", "peach", "kiwi"};
		assertArrayEquals(result2, FindKLargest.findKLargestHeap(stringList, 3).toArray());
		assertArrayEquals(result2, FindKLargest.findKLargestSort(stringList, 3).toArray());
	}
	
	@Test
	void findKLargestTestComparator() {
		Integer[] result1 = {1, 2, 5};
		assertArrayEquals(result1, FindKLargest.findKLargestHeap(intList, 3, (i1,i2) -> i2.compareTo(i1)).toArray());
		assertArrayEquals(result1, FindKLargest.findKLargestSort(intList, 3, (i1,i2) -> i2.compareTo(i1)).toArray());
		
		String[] result2 = {"banana", "apple", "peach"};
		assertArrayEquals(result2, FindKLargest.findKLargestHeap(stringList, 3, (i1,i2) -> ((Integer)i1.length()).compareTo(i2.length())).toArray());
		String[] result3 = {"banana", "peach", "apple"};
		assertArrayEquals(result3, FindKLargest.findKLargestSort(stringList, 3, (i1,i2) -> ((Integer)i1.length()).compareTo(i2.length())).toArray());
		
	}
	
	@Test
	void findKLargestEntireArrayNoComparator() {
		Integer[] result1 = {12, 8, 6, 5, 2, 1};
		assertArrayEquals(result1, FindKLargest.findKLargestHeap(intList, intList.size()).toArray());
		assertArrayEquals(result1, FindKLargest.findKLargestSort(intList, intList.size()).toArray());
		
		String[] result2 = {"pear", "peach", "kiwi", "banana", "apple"};
		assertArrayEquals(result2, FindKLargest.findKLargestHeap(stringList, stringList.size()).toArray());
		assertArrayEquals(result2, FindKLargest.findKLargestSort(stringList, stringList.size()).toArray());
	}
	
	@Test
	void findKLargestEntireArrayComparator() {
		Integer[] result1 = {1, 2, 5, 6, 8, 12};
		assertArrayEquals(result1, FindKLargest.findKLargestHeap(intList, intList.size(), (i1,i2) -> i2.compareTo(i1)).toArray());
		assertArrayEquals(result1, FindKLargest.findKLargestSort(intList, intList.size(), (i1,i2) -> i2.compareTo(i1)).toArray());
		
		String[] result2 = {"banana", "apple", "peach", "kiwi", "pear"};
		assertArrayEquals(result2, FindKLargest.findKLargestHeap(stringList, stringList.size(), (i1,i2) -> ((Integer)i1.length()).compareTo(i2.length())).toArray());
		String[] result3 = {"banana", "peach", "apple", "pear", "kiwi"};
		assertArrayEquals(result3, FindKLargest.findKLargestSort(stringList, stringList.size(), (i1,i2) -> ((Integer)i1.length()).compareTo(i2.length())).toArray());
	}
	
	@Test
	void kOutOfBoundsTest() {
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(intList, 12, (i1,i2) -> i2.compareTo(i1)));
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(intList, 12, (i1,i2) -> i2.compareTo(i1)));

	}

}
