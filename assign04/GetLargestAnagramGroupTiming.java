package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GetLargestAnagramGroupTiming extends AnagramChecker{
	
	public static void main(String[] args) {
		double timesToLoop = 1000;
		Random rand = new Random();
		
		for (int n = 10000; n <= 100000; n += 10000) {
		
			String[] s = new String[n];
			
			for (int i = 0; i < n; i++) {
				String str = "fuck";
				//for(int d = 0; d < 5; d++)
					//str+=(char)('a' + (rand.nextInt(26)));
				s[i] = str;
				
			}
			//String[] test = getLargestAnagramGroup(s);
			//System.out.println(Arrays.toString(test));
			
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
