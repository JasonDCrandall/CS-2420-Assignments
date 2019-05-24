package lab05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import com.sun.tools.javac.util.List;

/**
 * This class demonstrates how to use iterators, for Lab 5.
 * 
 * @author Ryan Sargent & Erin Parker
 * @version February 8, 2019
 */
public class IteratorDemo {
	public static void main(String[] args) {
		SortedSet<Integer> ss = new TreeSet<>();

		// How we like to see this
		for (int element : ss) {
			System.out.println(element);
		}

		// But this is what it is really doing
		Iterator<Integer> iter = ss.iterator();
		while (iter.hasNext()) {
			int element = iter.next();
			System.out.println(element);
		}

		// Same thing, different loop
		for (Iterator<Integer> it = ss.iterator(); it.hasNext();) {
			int element = it.next();
			System.out.println(element);
		}

		// this works for ANY collection:
		Collection<Integer> collection = new ArrayList<>();
		collection = new LinkedList<Integer>();
		collection = new HashSet<>();
		collection = new Vector<>();
		collection = new PriorityQueue<>();

		Queue<Integer> q = new LinkedList<>();
		q = new PriorityQueue<>();

		Stack<Integer> stack = new Stack<>();

		Iterator<Integer> iterator;
		iterator = collection.iterator();
		iterator = q.iterator();
		iterator = stack.iterator();

		for (int whatever : collection) {
			System.out.println(whatever);
		}

		for (int something : q) {
			System.out.println(q);
		}

		for (int nothing : stack) {
			System.out.println(stack);
		}
		List<MathVector> mathvecs = new ArrayList<>(Arrays.asList(
		MathVector v1 = new MathVector(new double[][] {{3, 1, 2}});
		MathVector v2 = new MathVector(new double[][] {{3, 2, 5}});
		MathVector v3 = new MathVector(new double[][] {{3}, {5}, {6}});
		MathVector v4 = new MathVector(new double[][] {{7}, {2}, {6}});
		MathVector v5 = new MathVector(new double[][] {{3}, {5}, {8}})));
		
		

		
	}
}