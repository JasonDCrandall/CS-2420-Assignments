package assign04;

import java.text.DecimalFormat;
import java.util.Random;

public class CheckAnalysis extends AnagramCheckerJavaSort{
	// All of these values are purposely small to keep the lecture demo quick.
		// Values for your own timing experiments should be larger.
		private final static int TIMES_TO_LOOP = 100;   // In practice, this value should be larger.    
		private final static int NSTART = 10000;
		private final static int NSTOP = 200000;
		private final static int NINCR = 10000;

		public static void main(String[] args) {

			DecimalFormat formatter = new DecimalFormat("00000E00");
			Random rand = new Random();
			
			System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\tT(N)/NlogN");
			System.out.println("-----------------------------------------------------------------------------------");

			for(int N = NSTART; N <= NSTOP; N += NINCR) { 
				
				String[] s = new String[N];
				
				for (int j = 0; j < N; j++) {
					String str = "";
					for(int d = 0; d < (rand.nextInt(7)+3); d++)
						str+=(char)('a' + (rand.nextInt(26)));
					s[j] = str;
				}
				System.out.print(N + "\t|  ");

				// Let things stabilize
				long startTime = System.nanoTime();
				while(System.nanoTime() - startTime < 1000000000)
					;

				// Time the routine
				startTime = System.nanoTime();
				for(int i = 0; i < TIMES_TO_LOOP; i++) {
					getLargestAnagramGroup(s);        // What is the Big-O behavior of this mystery method?
				}
				
				long midTime = System.nanoTime();

				// Time the empty loop
				for(int i = 0; i < TIMES_TO_LOOP; i++) {
					;
				}

				long stopTime = System.nanoTime();

				double avgTime = ((midTime - startTime) - (stopTime - midTime)) / (double) TIMES_TO_LOOP;

				System.out.println(formatter.format(avgTime) + "\t" + 
						formatter.format(avgTime / (Math.log10(N) / Math.log10(2))) + "\t" + 
						formatter.format(avgTime / N) + "\t" + 
						formatter.format(avgTime / (N * N)) + "\t" +
						formatter.format(avgTime / N*(Math.log10(N) / Math.log10(2))));
			}
		}
		
		
		
		/**
		 * This is a "mystery" method.
		 * Without looking at the implementation of this method, you should be able
		 * to guess its Big-O behavior by looking at the convergence of values 
		 * printed above. 
		 * 
		 * @param N - the problem size
		 * @return - an unused value
		 */
		private static long doSomething(int N) {
			long count = 0;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < 10000; j++)
					count++;
			return count;
		}
}
