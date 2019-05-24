package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
	
	SinglyLinkedList<T> newList = new SinglyLinkedList<T>();
	
	@Override
	public void clear() {
		newList.clear();
	}

	@Override
	public boolean isEmpty() {
		return newList.isEmpty();
	}

	@Override
	public T peek() throws NoSuchElementException {
		return newList.getFirst();
	}

	@Override
	public T pop() throws NoSuchElementException {
		return newList.removeFirst();
	}

	@Override
	public void push(T element) {
		newList.addFirst(element);
	}

	@Override
	public int size() {
		return newList.size();
	}

}
