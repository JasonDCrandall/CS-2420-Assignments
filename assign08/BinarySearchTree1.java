package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree1<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	int size = 0;
	Node root = null;

	@Override
	public boolean add(Type item) {

		if (root == null) {
			root = new Node(null, item);
			size++;
			return true;
		}
		return addItem(item, root);
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean added = false;

		for (Type item : items) {
			boolean current = add(item);
			if (current == true)
				added = true;
		}

		return added;
	}

	@Override
	public void clear() {
		root = null;
		size = 0; // TODO: write test making sure it is clear
	}

	@Override
	public boolean contains(Type item) {
		if (root == null)
			return false;

		return containsItem(item, root);
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {

		for (Type item : items)
			if (!(contains(item)))
				return false;

		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("The set is Empty");

		return firstItem(root);
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;

		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("The set is Empty");

		return lastItem(root);
	}

	@Override
	public boolean remove(Type item) {
		boolean isRoot = false;
		if (isEmpty())
			return false;
		if (item.compareTo(root.getData()) == 0)
			isRoot = true;
		return removeItem(item, root, isRoot);
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean removed = false;

		for (Type item : items) {
			boolean current = remove(item);
			if (current == true)
				removed = true;
		}
		return removed;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> arr = new ArrayList<Type>();
		buildArrayList(root, arr);
		return arr;
	}

	private void buildArrayList(Node n, ArrayList<Type> arr) {
		if (n == null)
			return;
		buildArrayList(n.left, arr);
		arr.add(n.data);
		buildArrayList(n.right, arr);
	}

	/*
	 * 
	 * HELPER METHODS AND RECURSIVE METHODS
	 * 
	 * 
	 */
	public boolean addItem(Type item, Node n) {
		if (item.compareTo(n.getData()) == 0) {
			return false;
		} else if (item.compareTo(n.getData()) < 0) {
			if (n.getLeft() == null) {
				n.setLeft(new Node(n.getNode(), item));
				size++;
				return true;
			} else
				return addItem(item, n.getLeft());
		} else {
			if (n.getRight() == null) {
				n.setRight(new Node(n.getNode(), item));
				size++;
				return true;
			} else
				return addItem(item, n.getRight());
		}
	}

	public boolean containsItem(Type item, Node n) {
		if (item.compareTo(n.getData()) == 0) {
			return true;
		} else if (item.compareTo(n.getData()) < 0) {
			if (n.getLeft() == null) {
				return false;
			} else
				return containsItem(item, n.getLeft());
		} else {
			if (n.getRight() == null) {
				return false;
			} else
				return containsItem(item, n.getRight());
		}
	}

	public Type firstItem(Node n) {
		if (n.getLeft() == null)
			return n.getData();
		return firstItem(n.getLeft());
	}

	public Node firstNode(Node n) {
		if (n.getLeft() == null)
			return n.getNode();
		return firstNode(n.getLeft());
	}

	public Type lastItem(Node n) {
		if (n.getRight() == null)
			return n.getData();
		return lastItem(n.getRight());
	}

	public boolean removeItem(Type item, Node n, boolean isRoot) {
		if (item.compareTo(n.getData()) == 0) {
			if (n.getRight() != null && n.getLeft() != null) {
				return removeHasTwoChildren(n);
			}
			return removeOneOrNoneChild(n, isRoot);
		}

		else if (item.compareTo(n.getData()) < 0) {
			if (n.getLeft() == null) {
				return false;
			} else
				return removeItem(item, n.getLeft(), isRoot);
		} else {
			if (n.getRight() == null) {
				return false;
			} else
				return removeItem(item, n.getRight(), isRoot);
		}
	}

	public boolean removeHasTwoChildren(Node n) {
		Node firstNode;
		firstNode = firstNode(n.getRight());

		Type data = firstNode.getData();

		removeOneOrNoneChild(firstNode, false);

		n.setData(data);

		return true;
	}

	public boolean removeOneOrNoneChild(Node n, boolean isRoot) {
		if (n.getRight() == null && n.getLeft() != null) {
			if (isRoot) {
				root = n.getLeft();
				root.setParent(null);
				size--;
				return true;
			}
			if (n.getParent().getLeft() == n) {
				n.getLeft().setParent(n.getParent());
				n.getParent().setLeft(n.getLeft());
				size--;
				return true;
			} else {
				n.getLeft().setParent(n.getParent());
				n.getParent().setRight(n.getLeft());
				size--;
				return true;
			}
		}

		if (n.getRight() != null && n.getLeft() == null) {
			if (isRoot) {
				root = n.getRight();
				root.setParent(null);
				size--;
				return true;
			}
			if (n.getParent().getRight() == n) {
				n.getRight().setParent(n.getParent());
				n.getParent().setRight(n.getRight());
				size--;
				return true;
			} else {
				n.getRight().setParent(n.getParent());
				n.getParent().setLeft(n.getRight());
				size--;
				return true;
			}
		}

		else {
			if (isRoot) {
				root = null;
				size--;
				return true;
			}
			if (n.getParent().getRight() == n) {
				n.getParent().setRight(null);
			} else
				n.getParent().setLeft(null);

			size--;
			return true;
		}
	}

	private class Node {
		Node parent;
		Node left;
		Node right;
		Type data;

		public Node(Node parent, Type data) {
			this.parent = parent;
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public Node getNode() {
			Node node = this;
			return node;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node n) {
			parent = n;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node n) {
			left = n;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node n) {
			right = n;
		}

		public Type getData() {
			return data;
		}

		public void setData(Type n) {
			data = n;
		}
	}

	/**
	 * Returns the BST represented as a String. Each line represents each level of
	 * the tree, two values in each line are children to a value in the above line.
	 * Null/No children is represented as "-"
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		int remainingNodes = size; // The amounts of Nodes in the tree that need to be added
		int level = 0; // The current level in Level-Order Traversal. Start at level 0 (root)
		int nodesInLevel = 0; // The current amount of nodes in the level while traversing
		int levelCapacity = 1; // Maximum amount of nodes per level. Value is 2^n, where n is the current
								// level. Start at 2^0 = 1
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);

		// Build the string while there are still non-null Nodes to be added
		// in a Level-Order Traversal of the BST.
		while (remainingNodes != 0) {

			// If the current level reaches maximum capacity, update some variables, and
			// start a new line representing a new level.
			if (nodesInLevel == levelCapacity) {
				result.append("\n");
				level++;
				nodesInLevel = 0;
				levelCapacity = (int) Math.pow(2, level);
			}

			Node current = q.poll();

			// Represent null nodes as "-"
			if (current == null) {
				result.append("- ");
				// To keep structure order, add the null node's children to the queue, which is
				// null.
				q.offer(null);
				q.offer(null);
			} else { // If the node is not null, add its data to the string, then add its children to
						// the queue
				result.append(current.data.toString() + " ");
				q.offer(current.getLeft());
				q.offer(current.getRight());
				remainingNodes--; // Since a non-null node was added to the string, there is one less remaining
									// node.
			}

			// A node was added to the string, increase the counter that represents the
			// amount of nodes in the current level
			nodesInLevel++;

		}

		// When all of the non-null nodes have been added to the string, fill the
		// remaining slots of the current level with
		// null nodes.
		while (nodesInLevel < levelCapacity) {
			result.append("- ");
			nodesInLevel++;
		}

		return result.toString();
	}
}
