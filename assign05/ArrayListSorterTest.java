package assign05;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static assign05.ArrayListSorter.*;


//TODO Tests partition
class ArrayListSorterTest {

	///////////////
	// mergesort //
	///////////////

	@BeforeEach
	void setUp() {
		threshold = 2;
		pivotType = 0;
	}

	@Test
	void mergesortPermutedTest() {
		int size = 10;
		ArrayList<Integer> given = generatePermuted(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergesortAscendingTest() {
		int size = 10;
		ArrayList<Integer> given = generateAscending(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergesortDescendingTest() {
		int size = 10;
		ArrayList<Integer> given = generateDescending(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergesortWithInsertionPermutedTest() {
		int size = 100;
		ArrayList<Integer> given = generatePermuted(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergesortWithInsertionAscendingTest() {
		int size = 100;
		ArrayList<Integer> given = generateAscending(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergesortWithInsertionDescendingTest() {
		int size = 100;
		ArrayList<Integer> given = generateDescending(size);
		mergesort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergeTest() {
		Integer[] arr = new Integer[] { 3, 5, 6, 8, 9, 10, 1, 2, 4, 7 };
		ArrayList<Integer> given = new ArrayList<>(Arrays.asList(arr));

		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			copy.add(null);
		}
		merge(given, copy, 0, 6, 9);

		assertEquals(generateAscending(10), given);
	}

	@Test
	void insertionSortSubArrayTest() {
		int size = 20;
		ArrayList<Integer> given = generateDescending(size);
		insertionSortHelper(given, 5, 14);
		Integer[] arr = new Integer[] { 20, 19, 18, 17, 16, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 5, 4, 3, 2, 1 };
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(arr));
		assertEquals(expected, given);
	}

	void insertionSortPermutedTest() {
		int size = 20;
		ArrayList<Integer> given = generatePermuted(size);
		insertionSortHelper(given, 0, size - 1);
		assertEquals(generateAscending(size), given);
	}

	void insertionSortAscendingTest() {
		int size = 20;
		ArrayList<Integer> given = generateAscending(size);
		insertionSortHelper(given, 0, size - 1);
		assertEquals(generateAscending(size), given);
	}

	void insertionSortDescendingTest() {
		int size = 20;
		ArrayList<Integer> given = generateDescending(size);
		insertionSortHelper(given, 0, size - 1);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void mergeSubArrayTest() {
		Integer[] arr = new Integer[] { -1, 2, 4, 5, 7, 8, 9, 0, 1, 3, 6, -2 };
		ArrayList<Integer> given = new ArrayList<>(Arrays.asList(arr));
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			copy.add(null);
		}
		merge(given, copy, 1, 7, 10);

		arr = new Integer[] { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -2 };
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(arr));
		assertEquals(expected, given);
	}

	///////////////
	// quicksort //
	///////////////

	@Test
	void quicksortTest() {
		int size = 20;
		ArrayList<Integer> given = generatePermuted(size);
		quicksort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void quicksortDescendingTest() {
		int size = 20;
		ArrayList<Integer> given = generateDescending(size);
		quicksort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void quicksortAscendingTest() {
		int size = 20;
		ArrayList<Integer> given = generateAscending(size);
		quicksort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void quicksortPivotAsSmallestElementTest() {
		int size = 20;
		pivotType = 3;
		ArrayList<Integer> given = generateDescending(size);
		quicksort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void quicksortPivotAsBiggestElementTest() {
		int size = 20;
		pivotType = 2;
		ArrayList<Integer> given = generateDescending(size);
		quicksort(given);
		assertEquals(generateAscending(size), given);
	}

	@Test
	void quicksortPivotAsRandomElementTest() {
		int size = 20;
		pivotType = 4;
		for (int i = 0; i < 1000; i++) {
			ArrayList<Integer> given = generateDescending(size);
			quicksort(given);
			assertEquals(generateAscending(size), given);
		}
	}

	///////////////////
	// miscellaneous //
	///////////////////

	@Test
	void generateAscendingTest() {
		Integer[] arr = new Integer[] { 1, 2, 3, 4, 5 };
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(arr));
		assertEquals(expected, generateAscending(5));
	}

	@Test
	void generatePermutedTest() {
		int size = 5;
		ArrayList<Integer> arr = generatePermuted(size);
		for (int i = 0; i < size; i++) {
			assertTrue(arr.contains(i + 1));
		}
		assertEquals(arr.size(), size);
		assertNotEquals(arr, generateAscending(size));
		assertNotEquals(arr, generateDescending(size));
	}

	@Test
	void generateDescendingTest() {
		Integer[] arr = new Integer[] { 5, 4, 3, 2, 1};
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(arr));
		assertEquals(expected, generateDescending(5));
	}

}
