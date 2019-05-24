package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates a generic priority queue that supports
 * access of the minimum element only.
 * 
 * @author Michael Hart and Jason Crandall
 * @version January 31, 2019
 * 
 * @param <E>
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterable<E>{

	private int elementsInQueue;
	private int arrLength;
	private E[] array;
	private boolean isComparable;
	private Comparator<? super E> cmp;
	
	/**
	 * Constructor that will use the comparable method for comparing
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		elementsInQueue = 0;
		arrLength = 16;
		array = (E[]) new Object[arrLength];
		isComparable = true;
	}
 
	/**
	 * Constructor that will use the comparator method if user chooses to
	 *
	 * @param cmp
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		elementsInQueue = 0;
		arrLength = 16;
		array = (E[]) new Object[arrLength];
		isComparable = false;
		this.cmp = cmp;
	}

	// Finds the min in a queue of elements
	@Override
	public E findMin() throws NoSuchElementException {
		if (elementsInQueue == 0)
			throw new NoSuchElementException("The queue is empty so there is no minimum");
		return array[elementsInQueue - 1];

	}

	// Finds and deletes the min in a queue of elements
	@Override
	public E deleteMin() throws NoSuchElementException {
		if (elementsInQueue == 0)
			throw new NoSuchElementException("The queue is empty so there is no minimum");
		E min = array[elementsInQueue - 1];
		elementsInQueue--;
		return min;
	}

	// Inserts Item into queue, uses comparator or comparable
	@Override
	public void insert(E item) {
		if(isComparable)
			insertComparable(item);
		else 
			insertComparator(item);
	}

	// Inserts all items in a collection
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for(E item: coll) {
			insert(item);
		}
	}

	// Returns size of queue
	@Override
	public int size() {
		return elementsInQueue;
	}

	// Returns boolean for an empty list
	@Override
	public boolean isEmpty() {
		if (elementsInQueue == 0)
			return true;
		return false;
	}

	// Clears the queue
	@Override
	public void clear() {
		elementsInQueue = 0; 
		return;
	}

	
	/**
	 * Shifts the elements after the index one position to the right 
	 * so the index can be inserted into the queue
	 * 
	 * @param index
	 */
	private void shiftArray(int index) {
		if (elementsInQueue == arrLength)
			doubleLength();
		for (int i = elementsInQueue - 1; i >= index; i--) {
			array[i + 1] = array[i];
		}
	}

	/**
	 * Doubles the size of the queue
	 */
	private void doubleLength() {
		@SuppressWarnings("unchecked")
		E[] doubledArray = (E[]) new Object[arrLength * 2];
		for (int i = 0; i < arrLength; i++)
			doubledArray[i] = array[i];
		arrLength = arrLength*2;
		array = doubledArray;
	}
	
	/**
	 * inserts the Item based upon a comparable .compareTo method
	 * 
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	private void insertComparable(E item) {
		if (elementsInQueue == arrLength)
			doubleLength();
		if (elementsInQueue == 0) {
			array[0] = item;
			elementsInQueue++;
			return;
		}
		if (elementsInQueue == 1) {
			if (((Comparable<? super E>) item).compareTo(array[0]) > 0) {
				array[1] = array[0];
				array[0] = item;
				elementsInQueue++;
				return;
			} else if (((Comparable<? super E>) item).compareTo(array[0]) <= 0) {
				array[1] = item;
				elementsInQueue++;
				return;
			}
		}
		int low = 0;
		int high = elementsInQueue - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			E midItem = array[mid];

			if (((Comparable<? super E>) item).compareTo(midItem) > 0) {
				high = mid - 1;
				continue;
			}
			if (((Comparable<? super E>) item).compareTo(midItem) < 0) {
				low = mid + 1;
				continue;
			}
			if (((Comparable<? super E>) item).compareTo(midItem) == 0) {
				shiftArray(mid);
				array[mid] = item;
				elementsInQueue++;
				return;
			}
		}
		
		if (elementsInQueue == low) {
			array[low] = item;
			elementsInQueue ++;
			return;
		}
		
		shiftArray(low);
		array[low] = item;
		elementsInQueue++;

	}
	
	/**
	 * Inserts the Item based on a comparator that the user
	 * inputs. 
	 * 
	 * @param item
	 */
	public void insertComparator(E item) {
		if (elementsInQueue == arrLength)
			doubleLength();
		if (elementsInQueue == 0) {
			array[0] = item;
			elementsInQueue++;
			return;
		}
		if (elementsInQueue == 1) {
			if (cmp.compare(item, array[0]) > 0) {
				array[1] = array[0];
				array[0] = item;
				elementsInQueue++;
				return;
			} else if (cmp.compare(item, array[0]) <= 0) {
				array[1] = item;
				elementsInQueue++;
				return;
			}
		}
		int low = 0;
		int high = elementsInQueue - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			E midItem = array[mid];

			if (cmp.compare(item, midItem) > 0) {
				high = mid - 1;
				continue;
			}
			if (cmp.compare(item, midItem) < 0) {
				low = mid + 1;
				continue;
			}
			if (cmp.compare(item, midItem) == 0) {
				shiftArray(mid);
				array[mid] = item;
				elementsInQueue++;
				return;
			}
		}
		
		if (elementsInQueue == low) {
			array[low] = item;
			elementsInQueue ++;
			return;
		}
		
		shiftArray(low);
		array[low] = item;
		elementsInQueue++;

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	private class SimplePriorityQIterator implements Iterator<E>{

		private int nextIndex;
		private boolean canRemove;
		
		@Override
		public boolean hasNext() {
			if(nextIndex < size())
				return true;
			else 
				return false;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			nextIndex++;
			canRemove = true;
			
			return queue[nextIndex];
		}
		
		@Override
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException();
			}
			
			canRemove = false;
			
			for (int i = nextIndex; i < size()-1; i++) {
				queue[i] = queue[i+1];
			}
			
			size()--;
		}
		
	}
}
