package assign09;

import java.util.Random;

/**
 * This class counts and displays the number of collisions occurred in the HashTable
 * that uses separate chaining.
 * 
 * @author Jason Crandall & Michael Hart
 * @version March 28, 2019
 */
public class HashCodeCollisionCounter {
	
	private static HashTable<StudentGoodHash, Integer> goodTable = new HashTable<StudentGoodHash, Integer>();
	private static HashTable<StudentMediumHash, Integer> mediumTable = new HashTable<StudentMediumHash, Integer>();
	private static HashTable<StudentBadHash, Integer> badTable = new HashTable<StudentBadHash, Integer>();

	public static void main(String[] args) {
		
		Random rng = new Random(34);
		String first = new String();
		String last = new String();
		
		
		for (int n = 1000; n <= 20000; n+=1000) {
			
			goodTable.clear();
			mediumTable.clear();
			badTable.clear();
			
			// Generate a random StudentXHash to add to the three 
			// HashTables for timing
			
			for (int i = 0; i < n; i++) {
				
				for (int j = 0; j < rng.nextInt(1) + 6; j++) {
					char rand = (char)(rng.nextInt(26) + 'a');
					first = first + rand;
				}
				for(int h = 0; h < 10; h++) {
					char rand = (char)(rng.nextInt(26) + 'a');
					last = last + rand;
				}
				
				StudentGoodHash goodStudent = new StudentGoodHash(rng.nextInt(100000) + 999999, first, last);
				StudentMediumHash mediumStudent = new StudentMediumHash(rng.nextInt(100000) + 9999999, first, last);
				StudentBadHash badStudent = new StudentBadHash(rng.nextInt(100000) + 9999999, first, last);
				
				goodTable.put(goodStudent, 0);
				mediumTable.put(mediumStudent, 0);
				badTable.put(badStudent, 0);
				
				first = new String();
				last = new String();
			}
			
			// Print the results
			
			System.out.println(n + "\t" + goodTable.getCollisionCount() + "\t" + 
					mediumTable.getCollisionCount() + "\t" + badTable.getCollisionCount());
		}

	}

}
