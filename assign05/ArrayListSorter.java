package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * This class contains variations on the mergesort and quicksort sorting
 * algorithms, and a collection of helper methods designed for testing the
 * variations.
 * 
 * @author Mario A. Bruun and Jason Cradall
 * @version February 13, 2019
 */
public class ArrayListSorter {

	/**
	 * (public for testing) Threshold is the array size at which the recursive
	 * partition in the mergesort will switch to the insertion sort routine.
	 */
	public static int threshold = 5;

	/**
	 * (public for testing) pivotType determines the pivot choosing strategy of the
	 * quicksort routine: (0) deterministic middle index, (1) deterministic left
	 * index, (2) deterministic right index, (3) median of previous three
	 * deterministic choices, (4) random index, and (else) median of three random
	 * indexes.
	 */
	public static int pivotType = 0;

	////////////////////////
	// Merge sort routine //
	////////////////////////

	/**
	 * Performs a merge sort on the generic ArrayList given as input. Sorts by the
	 * objects' standard comparable value in ascending order (less to more, in the
	 * context of the comparable).
	 * 
	 * @param ArrayList of Comparable objects
	 * @see Comparable
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {

		ArrayList<T> temp = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(null);
		}

		mergesort(list, temp, 0, list.size() - 1);
	}

	/**
	 * Recursive method to the mergesort driver method. Performs the mergesort
	 * routine on the given list within the delimited sub-array (left to right),
	 * using the copy as extra space for its operations.
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, ArrayList<T> copy, int left,
			int right) {
		if (right - left > threshold) {
			int mid = (left + right) / 2;
			mergesort(list, copy, left, mid);
			mergesort(list, copy, mid + 1, right);
			merge(list, copy, left, mid + 1, right);

		} else {
			insertionSortHelper(list, left, right);
		}
	}

	/**
	 * (public for testing) Part of the mergesort routine. Assumes it is given two
	 * sections (sub-arrays) within the list that have been sorted in ascending
	 * order (left to mid and mid to right) to be merged into one sorted sub-array
	 * within the list.
	 */
	public static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> copyArray, int left,
			int mid, int right) {
		int subLeft = left;
		int subRight = mid;
		int lastLeft = mid - 1;
		int lastRight = right;
		int copyIndex = 0;

		// While both sub-arrays are not empty, merges elements in copyArray.
		while (subLeft <= lastLeft && subRight <= lastRight) {
			if (list.get(subLeft).compareTo(list.get(subRight)) < 0) {
				copyArray.set(copyIndex, list.get(subLeft));
				subLeft++;
			} else {
				copyArray.set(copyIndex, list.get(subRight));
				subRight++;
			}
			copyIndex++;
		}

		// When one sub-array runs out of elements to merge, copies other sub-array to
		// copyArray
		if (subLeft - 1 == lastLeft) {
			for (int i = subRight; i <= lastRight; i++) {
				copyArray.set(copyIndex, list.get(i));
				copyIndex++;
			}
		}
		if (subRight - 1 == lastRight) {
			for (int i = subLeft; i <= lastLeft; i++) {
				copyArray.set(copyIndex, list.get(i));
				copyIndex++;
			}
		}

		// Once merging is finished, copies sorted elements back into list's sub-array.
		for (int i = left; i <= right; i++) {
			list.set(i, copyArray.get(i - left));
		}
	}

	/**
	 * (public for testing) Part of the mergesort routine. Performs insertion sort
	 * on a given sub-array (left to right) of list.
	 */
	public static <T extends Comparable<? super T>> void insertionSortHelper(ArrayList<T> list, int left, int right) {

		for (int i = left + 1; i < right + 1; i++) {
			T val = list.get(i);
			int j;
			for (j = i - 1; j >= left && list.get(j).compareTo(val) > 0; j--) {
				list.set(j + 1, list.get(j));
			}
			list.set(j + 1, val);
		}

	}

	////////////////////////
	// Quick sort routine //
	////////////////////////

	/**
	 * Performs a quick sort on the generic ArrayList given as input. Sorts by the
	 * objects' standard comparable value in ascending order (less to more, in the
	 * context of the comparable).
	 * 
	 * @param ArrayList of Comparable objects
	 * @see Comparable
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
		quicksort(list, 0, list.size() - 1);
	}

	/**
	 * Recursive method to the quicksort driver method. Performs the quicksort
	 * routine on the given list within the delimited sub-array (left to right).
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int left, int right) {
		if ((right - left) > 1) {
			int index = partition(list, left, right);
			quicksort(list, left, index - 1);
			quicksort(list, index, right);
		} else {
			if (right > left && list.get(right).compareTo(list.get(left)) < 0) // FIXME
			{
				swap(list, right, left);
			}
		}

	}

	/**
	 * (public for testing) Part of the quicksort routine. Chooses a pivot (ideally
	 * a close-to-median value within the sub-array) and organizes the sub-array so
	 * that elements "greater than" the pivot are right of it (the conceptual right
	 * sub-array) and elements "less than" the pivot are left of it (the conceptual
	 * left sub-array) by the end of the routine.
	 * 
	 * @returns index of the pivot after ordering
	 */
	public static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int left, int right) {
		int currLeft = left;
		int currRight = right - 1;
		int pivotIndex = choosePivot(list, left, right, pivotType);

		// Moves the pivot "out of the way."
		swap(list, pivotIndex, right);
		pivotIndex = right;

		// Orders the sub-array in the stated manner (right of and left of pivot value).
		while (currLeft < currRight) {
			while ((list.get(currLeft).compareTo(list.get(pivotIndex)) <= 0) && currLeft != pivotIndex) {
				currLeft++;
			}
			while ((list.get(currRight).compareTo(list.get(pivotIndex)) > 0) && currRight != 0) {
				currRight--;
			}
			if (currLeft < currRight) {
				swap(list, currLeft, currRight);
			}
		}

		// Moves pivot between sub-arrays after they are ordered.
		swap(list, pivotIndex, currLeft);

		// Returns first position of right sub-array
		if (currLeft == left)
			return currLeft + 1;
		else
			return currLeft;
	}

	/**
	 * Returns a pivot position within the given sub-array (left to right within
	 * list) dependent on the strategy choice.
	 */
	private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> list, int left, int right,
			int choice) {

		Random rng = new Random();
		switch (choice) {
		case 0: // Deterministic middle index
			return (right + left) / 2;

		case 1: // Deterministic left index
			return left;

		case 2: // Deterministic right index
			return right;

		case 3: // Median of deterministic middle, right, and left
			return medianOfThree(left, (right + left) / 2, right);

		case 4: // random index
			return rng.nextInt((right - left) + 1) + left;

		default: // Case 5 - median of three random indexes
			int a = rng.nextInt((right - left) + 1) + left;
			int b = rng.nextInt((right - left) + 1) + left;
			int c = rng.nextInt((right - left) + 1) + left;
			return medianOfThree(a, b, c);
		}
	}

	//////////////////////////////////
	// ArrayList generation methods //
	//////////////////////////////////

	/**
	 * Generates and returns an ArrayList of integers 1 to size in ascending order.
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			temp.add(i + 1);
		}

		return temp;
	}

	/**
	 * Generates and returns an ArrayList of integers 1 to size in permuted order.
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			temp.add(i + 1);
		}

		Collections.shuffle(temp);

		return temp;
	}

	/**
	 * Generates and returns an ArrayList of integers 1 to size in descending order
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = size - 1; i >= 0; i--) {
			temp.add(i + 1);
		}

		return temp;
	}

	//////////////////////////////////
	// Miscellaneous helper methods //
	//////////////////////////////////

	/** Swaps the elements at the given indexes */
	private static <T extends Comparable<? super T>> void swap(ArrayList<T> list, int a, int b) {
		T temp;
		temp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, temp);
	}

	/** Returns the median of three values */
	private static <T extends Comparable<? super T>> int medianOfThree(int a, int b, int c) {
		if (a > b && b > c)
			return b;
		else {
			if (c > a && a > b)
				return a;
			else
				return c;
		}
	}
}
