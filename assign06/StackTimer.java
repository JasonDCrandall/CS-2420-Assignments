package assign06;

import assign06.LinkedListStack;
import assign06.ArrayStack;

public class StackTimer {
	
	public static void main(String[] args) {
		
	    // Do 10000 lookups and use the average running time.
	    int timesToLoop = 1000;
	    //LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
	    ArrayStack<Integer> stack = new ArrayStack<Integer>();
	    
	    // For each problem size n . . .
	    for (int n = 1000; n <= 10000; n += 1000) {
	    	for(int i = 0; i <= n; i++)
	    		stack.push(n);
	    	
	      long startTime, midpointTime, stopTime;

	      // First, spin computing stuff until one second has gone by.
	      // This allows this thread to stabilize.
	      startTime = System.nanoTime();
	      while (System.nanoTime() - startTime < 1000000000) { // empty block 
	      }

	      // Now, run the test.  
	      startTime = System.nanoTime();

	      for (int i = 0; i < timesToLoop; i++) {
	        stack.peek();
	      }

	      midpointTime = System.nanoTime();

	      // Run a loop to capture the cost of running the "timesToLoop" loop and 
	      // generating a random ISBN.
	      for (int i = 0; i < timesToLoop; i++) { 
	      
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
