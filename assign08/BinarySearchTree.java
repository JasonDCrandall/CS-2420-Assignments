package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private Node root = null;
	private int size = 0;
	
	@Override
	public boolean add(Type item) {
		if (root == null) {
			root = new Node(item, null);
			size++;
			return true;
		}
		return root.add(item);
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean added = false;
		for (Type item : items) {
			added = added || add(item);
		}
		return added;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		if (root == null)
			return false;
		return root.contains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type item : items) {
			if(!contains(item))
				return false;
		}
		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected class Node {
		
		private Type _value;
		private Node _parent;
		private Node _left;
		private Node _right;
		
		public Node(Type value, Node parent) {
			_value = value;
			_parent = parent;
			_left = null;
			_right = null;
		}
		
		public boolean add(Type value) {
			int compare = value.compareTo(_value);
			if (compare == 0) {
				return false;
			}
			else if (compare < 0) {
				if(_left == null) {
					_left = new Node(value, this);
					size++;
					return true;
				}
				return _left.add(value);
			}
			else {
				if(_right == null) {
					_right = new Node(value, this);
					size++;
					return true;
				}
				return _right.add(value);
			}
		}
		
		public boolean contains(Type value) {
			int compare = value.compareTo(_value);
			if (compare == 0) {
				return true;
			}
			else if (compare < 0) {
				if(_left == null) {
					return false;
				}
				return _left.contains(value);
			}
			else {
				if(_right == null) {
					return false;
				}
				return _right.contains(value);
			}
		}
		
	}

}
