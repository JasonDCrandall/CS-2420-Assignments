package assign09;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

import comprehensive.RandomPhraseGenerator;

/**
 * This is a timing class meant to print out the runtime of java's HashMap
 * and compare them to the HashTable class
 * 
 * @author Jason Crandall & Michael Hart
 * @version March 28, 2019
 */
public class JavaHashTiming {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 10000;
		String[] arr = {"assignment_extension_request.g", "2"};

		// For each problem size n . . .
		for (int n = 1000; n <= 20000; n += 1000) {
			
			for (int i = 0; i < n; i++) {
				
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
				RandomPhraseGenerator.main(arr);
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
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}

	}
}

