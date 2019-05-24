package assign04;

import java.util.ArrayList;
import java.util.Random;

public class AnagramCheckerTiming extends AnagramChecker{

	public static void main(String[] args) {
		double timesToLoop = 1000;
		Random rng = new Random();
		String testString = new String();
		

		
		for (int n = 1000; n <= 20000; n += 1000) {
			
			char rand = (char)(rng.nextInt(26) + 'a');
			
			for (int i = 0; i < 1000; i++)
				testString = testString + rand;

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				areAnagrams(testString,testString);
			}

			midpointTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}

}
