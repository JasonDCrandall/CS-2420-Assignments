package assign05;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;
import static assign05.ArrayListSorter.*;

public class ArrayListSorterTimer {

	public static void main(String[] args) {
		long startTime, midpointTime = 0, stopTime;
		long timesToLoop = 1000;
		Random rng = new Random();
		pivotType = 4;
		threshold = 25; // (24, 75, ?) seem to be ideal spots
		

		for (int u = 1000; u <= 20000; u+=1000) {
			
			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}
			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++) {
				ArrayList<Integer> tested = generatePermuted(u);
				quicksort(tested);
			}
			midpointTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++) {
				ArrayList<Integer> empty = generatePermuted(u);
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
			System.out.println(averageTime);
		}
	}
}
