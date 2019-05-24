package comprehensive;

import java.io.FileNotFoundException;
import java.util.Random;

public class ComprehensiveTiming {
	
	//private static String[] arr;
	
	public static void main(String[] args) throws FileNotFoundException {
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 10000;

		
		//String[] arr = {"src/comprehensive/assignment_extension_request.g", "\n"};
		
		// For each problem size n . . .
		for (int n = 1000; n <= 10000; n += 1000) {
			
			
			String[] arr = {String.format("src/timing%d.g", n), "1"};

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
