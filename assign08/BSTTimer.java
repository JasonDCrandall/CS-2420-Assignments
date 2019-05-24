package assign08;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import assign06.ArrayStack;

public class BSTTimer {

	public static void main(String[] args) {
		// Do 10000 lookups and use the average running time.
	    int timesToLoop = 1000;
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    BinarySearchTree1<Integer> tree = new BinarySearchTree1<Integer>();
	    Random rng = new Random(40);
	    TreeSet<Integer> set = new TreeSet<Integer>();
	    // For each problem size n . . .
	    for (int n = 1000; n <= 20000; n += 1000) {
	    	for(int i = 0; i <= n; i++) {
	    		list.add(rng.nextInt(n));
	    	}
	      long startTime, midpointTime, stopTime;
	     
	      // First, spin computing stuff until one second has gone by.
	      // This allows this thread to stabilize.
	      startTime = System.nanoTime();
	      while (System.nanoTime() - startTime < 1000000000) { // empty block 
	      }

	      // Now, run the test.  
	      startTime = System.nanoTime();

	      for (int i = 0; i < timesToLoop; i++) {
	        //tree.contains(tree.last());
	        //set.contains(set.last());
	    	//set.addAll(list);
	    	  tree.addAll(list);
	      }

	      midpointTime = System.nanoTime();

	      // Run a loop to capture the cost of running the "timesToLoop" loop and 
	      // generating a random ISBN.
	      for (int i = 0; i < timesToLoop; i++) { 
	    	  //tree.last();	  
	    	  //set.last();
	      }

	      stopTime = System.nanoTime();

	      // Compute the time, subtract the cost of running the loop
	      // from the cost of running the loop and doing the lookups.
	      // Average it over the number of runs.
	      double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
	          / timesToLoop;

	      System.out.println(n + "\t" + averageTime);
	  }

	}

	

}
