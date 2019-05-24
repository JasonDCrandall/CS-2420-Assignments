package assign10;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static assign05.ArrayListSorter.*;
/**
 * This program demonstrates how use formulas to compare the empirically observed
 * running time of a method/algorithm to the expected Big-O behavior.
 * 
 * Let T(N) be the running time observed, and let F(N) be the Big-O expected.
 * 
 * If T(N) / F(N) converges to a positive value, then F(N) correctly represents
 * the growth rate of the running times.
 * 
 * If T(N) / F(N) converges to 0, then F(N) is an overestimate of the growth
 * rate of the running times.
 * 
 * If T(N) / F(N) converges to infinity, then F(N) is an underestimate of the
 * growth rate of the running times.
 * 
 * @author Erin Parker
 * @version January 28, 2019
 */
public class AnalysisListSorterTimeAnalysis {
	
	// All of these values are purposely small to keep the lecture demo quick.
	// Values for your own timing experiments should be larger.
	private final static int TIMES_TO_LOOP = 100;   // In practice, this value should be larger.    
	private final static int NSTART = 10000;
	private final static int NSTOP = 1000000;
	private final static int NINCR = 10000;

	public static void main(String[] args) {
		

		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\tT(N)/NlogN");
		System.out.println("-----------------------------------------------------------------------------------");

		for(int N = NSTART; N <= NSTOP; N += NINCR) { 
			List<Integer> list = new ArrayList<Integer>();
			BinaryMaxHeap<Integer> heap;
			Random rng = new Random(2);
			for (int i = 0; i < N; i++) {
				list.add(12);
			}
			
			heap = new BinaryMaxHeap<Integer>(list);
			//heap.extractMax();
			System.out.print(N + "\t|  ");

			// Let things stabilize
			long startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000)
				;

			// Time the routine
			startTime = System.nanoTime();
			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				heap.add(10);
			}
			
			long midTime = System.nanoTime();

			// Time the empty loop
			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				
			}

			long stopTime = System.nanoTime();

			double avgTime = (((midTime - startTime) - (stopTime - midTime))) / (double) (TIMES_TO_LOOP * 10);

			System.out.println(formatter.format(avgTime) + "\t" + 
					formatter.format(avgTime / (Math.log10(N) / Math.log10(2))) + "\t" + 
					formatter.format(avgTime / N) + "\t" + 
					formatter.format(avgTime / (N * N)) + "\t" +
					formatter.format(avgTime / (N*(Math.log10(N) / Math.log10(2)))));
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