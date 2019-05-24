package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private Comparator<? super E> cmp;
	
	@SuppressWarnings("unchecked")
	private E[] array = (E[]) new Object[100];
	private int size = 0;
	
	public BinaryMaxHeap() {
		
	}
		
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = cmp; 
	}
	
	public BinaryMaxHeap(List<? extends E> list) {
		buildHeap(list);
	}
	
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) { 
		this.cmp = cmp; 
		buildHeap(list);
	}
	
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		array = (E[]) new Object[list.size()];
		size = list.size();
		if (size >= array.length)
			resize();
		for(int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		for (int i = size/2; i >= 0; i--) {
			percolateDown(i);
		}
	}
	
	@SuppressWarnings("unchecked")
	private int innerCompare(E item1, E item2) {
		if(cmp == null)
			return ((Comparable<? super E>)item1).compareTo(item2);
		return cmp.compare(item1, item2);
	}
	
	private void percolateUp(int i) {
		int p = parent(i);
		while(i > 0 && innerCompare(array[i], array[p]) > 0) {
			swap(i,p);
			i = p;
			p = parent(i);
		}
	}
	
	private void percolateDown(int i) {
		do {
			int j = -1; 
			int r = right(i); 
			if (r < size && innerCompare(array[r], array[i]) > 0) {
				int l = left(i);
				if(innerCompare(array[l], array[r]) > 0) {
					j = l; 
				}
				else {
					j = r; 
				}
			}
			else {
				int l = left(i);
				if(l < size && innerCompare(array[l], array[i]) > 0) {
					j = l; 
				}
			}
			if(j >= 0) 
				swap(i,j);
			i = j;
		}
		while(i >= 0);
	}
	
	private int left(int i) {
		return 2*i + 1; 
	}
	
	private int right(int i) {
		return 2*i + 2; 
	}
	
	private int parent(int i) {
		return (i-1)/2;
	}
	
	private void swap(int i, int p) {
		E temp = array[p];

		array[p] = array[i];
		array[i] = temp;
	}
	@Override
	public void add(E item) {
		if(size == array.length)
			resize();
		array[size] = item;
		percolateUp(size);
		size++;
	}

	@Override
	public E peek() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException("The Binary Max Heap is empty");
		return array[0];
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("The Binary Max Heap is empty");
		}
		E maxItem = array[0];
		array[0] = array[--size];
		percolateDown(0);
		return maxItem;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		array = (E[]) new Object[100];
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for (int i = 0; i < size; i++) {
			result[i] = array[i];
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] newArr = (E[]) new Object[size*2];
		for(int i = 0; i < size; i++)
			newArr[i] = array[i];
		array = newArr;
	}

}
