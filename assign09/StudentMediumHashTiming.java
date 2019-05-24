package assign09;

import java.util.Random;

/**
 * A timing class for StudentMediumHash meant to time its
 * runtime for various methods when used in a HashTable
 * 
 * @author Jason Crandall & Michael Hart
 * @version March 28, 2019
 */
public class StudentMediumHashTiming {
	private static HashTable<StudentMediumHash, Integer> table = new HashTable<StudentMediumHash, Integer>();

	public static void main(String[] args) {
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 10000;
		Random rng = new Random(34);
		String first = new String();
		String last = new String();

		// For each problem size n . . .
		for (int n = 1000; n <= 20000; n += 1000) {
			table.clear();
			
			for (int i = 0; i < n; i++) {
				
				for (int j = 0; j < rng.nextInt(1) + 6; j++) {
					char rand = (char)(rng.nextInt(26) + 'a');
					first = first + rand;
				}
				for(int h = 0; h < 10; h++) {
					char rand = (char)(rng.nextInt(26) + 'a');
					last = last + rand;
				}
				StudentMediumHash student = new StudentMediumHash(rng.nextInt(100000) + 999999, first, last);
				table.put(student, 0);
				first = new String();
				last = new String();
			}
			
			StudentMediumHash jason = new StudentMediumHash(1234567, "Jason", "Crandall");

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				table.entries();
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
