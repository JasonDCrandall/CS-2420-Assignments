package assign04;

import java.util.Arrays;
import java.util.Random;

public class GetLargestAnagramCheckerJavaSort extends AnagramCheckerJavaSort {

	public static void main(String[] args) {
		double timesToLoop = 1000;
		Random rand = new Random();
		
		for (int n = 1000; n <= 20000; n += 1000) {
			
			String[] s = new String[n];
			
			for (int i = 0; i < n; i++) {
				String str = "";
				for(int d = 0; d < (rand.nextInt(7) + 3); d++)
					str+=(char)('a' + (rand.nextInt(26)));
				s[i] = str;
				
			}
			
			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				getLargestAnagramGroup(s);
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
