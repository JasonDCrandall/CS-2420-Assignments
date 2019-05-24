package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a class that creates a singly linked list using nodes to 
 * build, add, and navigate through the list.
 * 
 * @author Jason Crandall && Michael Hart
 * @version February 28, 2019
 *
 * @param <T>
 */
public class SinglyLinkedList<T> implements List<T>{
 
	private Node<T> head; 
	private int listCount; 
	
	/**
	 * Constructor for the list
	 */
	public SinglyLinkedList() {
		head = null; 
		listCount = 0;
	}
	
	@Override
	public void addFirst(T element) {
		if(isEmpty()) {
			head = new Node<T>(element, null);
			listCount++;
			return;
		}
		
		Node<T> newNode = new Node<T>(element, head);
		head = newNode;
		listCount++;
		return;
	}

	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException {
		Node<T> current = head;
		
		if(index > listCount - 1 || index < 0) {
			throw new IndexOutOfBoundsException("The index is not within the size of the list");
		}
		Node<T> newNode = new Node<T>(element, null);
		if(index == 0) {
			addFirst(element);
			return;
		}
		
		for (int i = 1; i < index; i++) {
			current = current.next;
			if (i == index-1) {
				newNode.setNext(current.next);
				current.setNext(newNode);
			}
			
		}
		if(1 == index) {						
			newNode.setNext(current.next);
			current.setNext(newNode);
			}

		listCount++;
		return;
	}

	@Override
	public T getFirst() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("The Linked List is empty");
		}
		return head.getCurrent();
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		Node<T> current = head;
		
		if(index > listCount - 1 || index < 0) {
			throw new IndexOutOfBoundsException("The index is not within the size of the list");
		}
		if(index == 0) {
			return getFirst();
		}
		
		for (int i = 1; i <= index; i++) {
			current = current.next;
			}
		return current.getCurrent();
	}

	@Override
	public T removeFirst() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("The Linked List is empty");
		}
		if(listCount == 1) {
			T data = head.getCurrent();
			listCount--;
			head = null;
			return data;
		}
		T data = head.getCurrent();
		head = head.next;
		listCount--;
		return data; 
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		Node<T> current = head;
		T value = null;
		
		if(index > listCount - 1 || index < 0) {
			throw new IndexOutOfBoundsException("The index is not within the size of the list");
		}

		if(index == 0) {
			return removeFirst();
		}
		
		for (int i = 1; i < index; i++) {
			current = current.next;
			if (i == index-1) {
				value = current.getNext().getCurrent();
				current.setNext(current.next.next);
			}
		}
		
		if (index == 1) {
			value = current.getNext().getCurrent();
			current.setNext(current.next.next);
		}
		
		listCount--;
		return value;
	}

	@Override
	public int indexOf(T element) {
		Node<T> current = head;
		
		if(isEmpty()) {
			return -1;
		}
		if(head.getCurrent().equals(element))
			return 0;
		
		for (int i = 1; i < listCount; i++) {
			current = current.next;
			if (current.getCurrent().equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return listCount;
	}

	@Override
	public boolean isEmpty() {
		if(listCount == 0)
			return true;
		return false;
	}

	@Override
	public void clear() {
		head = null;
		listCount = 0;
	}

	@Override
	public Object[] toArray() {
		Node<T> current = head;
		Object[] arr = new Object[listCount];
		
		if(isEmpty()) {
			return arr;
		}
		
		arr[0] = head.getCurrent();
		for (int i = 1; i < listCount; i++) {
			current = current.next;
			arr[i] = current.getCurrent();	
			}
		
		return arr;
	}
	
	protected Node<T> getHead(){
		return head;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator<T>(this);
	}
	
	/**
	 * An iterator class that steps through a singly linked list
	 *
	 * @param <E>
	 */
	protected class SinglyLinkedListIterator<E> implements Iterator<E>{

		SinglyLinkedList<E>.Node<E> current;
		SinglyLinkedList<E> list;
		int count;
		boolean canRemove = false;
		
		
		public SinglyLinkedListIterator(SinglyLinkedList<E> list) {
			this.list = list;
			current = list.getHead();
			count = -1;
		}
		
		@Override
		public boolean hasNext() {
			return current.getNext() != null;
		}

		@Override
		public E next() {
			if (count == -1) {
				count++;
				canRemove = true;
				return current.getCurrent();
			}
			E value = current.next.getCurrent();
			current = current.getNext();
			count++;
			canRemove = true;
			return value;
		}
		
		@Override
		public void remove() throws IllegalStateException{
			if (canRemove == false)
				throw new IllegalStateException();
			if(count == 0){
				list.remove(count);
				canRemove = false;
				return;
			}
			count--;
			list.remove(count);
			canRemove = false;
		}
		
	}
	
	/**
	 * Class that builds a node to be used in the singly linked list.
	 * Each node has a reference to the next node.
	 *
	 * @param <A>
	 */
	protected class Node<A> {
		
		public Node<A> next;
		public A data;
		
		/**
		 * Constructor for a node
		 * 
		 * @param data
		 * @param next
		 */
		public Node(A data, Node<A> next) {
			this.next = next;
			this.data = data;
		}
		
		/**
		 * Getter to retrieve the value held in a node
		 * 
		 * @return data
		 */
		public A getCurrent() {
			return data;
		}
		
		/**
		 * Gets the next node in the list
		 * 
		 * @return next -- the next node in the list
		 */
		public Node<A> getNext() {
			return next;
		}
		
		/**
		 * Sets the reference to the next node in the list
		 * 
		 * @param next -- reference to the next node in the list
		 */
		public void setNext(Node<A> next) {
			this.next = next;
		}
		
	}

}


