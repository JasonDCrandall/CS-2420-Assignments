package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class to test the implementation of the HashTable class
 * while also incorporating the StudentBadHash to test the generic
 * capabilities of the Hash Table
 * 
 * @author Jason Crandall & Michael Hart
 * @version March 28, 2019
 */
class HashTableTest {
	
	private HashTable<Integer, String> intKeyTable, emptyTable;
	private HashTable<String, Integer> stringKeyTable;
	private HashTable<StudentBadHash, Double> studentTable;
	private StudentBadHash alan, ada, edsgar, grace;

	@BeforeEach
	void setUp() throws Exception {
		intKeyTable = new HashTable<Integer, String>();
		intKeyTable.put(1, "banana");
		intKeyTable.put(5, "apple");
		intKeyTable.put(3, "orange");
		intKeyTable.put(9, "pear");
		intKeyTable.put(11, "grape");
		intKeyTable.put(15, "grapefruit");
		
		stringKeyTable = new HashTable<String, Integer>();
		stringKeyTable.put("banana", 1);
		stringKeyTable.put("apple", 5);
		stringKeyTable.put("orange", 3);
		stringKeyTable.put("pear", 9);
		stringKeyTable.put("grape", 11);
		stringKeyTable.put("grapefruit", 15);
		
		emptyTable = new HashTable<Integer, String>();
		
		
		
		alan = new StudentBadHash(1019999, "Alan", "Turing");
		ada = new StudentBadHash(1004203, "Ada", "Lovelace");
		edsgar = new StudentBadHash(1010661, "Edsgar", "Dijkstra");
		grace = new StudentBadHash(1019941, "Grace", "Hopper");

		studentTable = new HashTable<StudentBadHash, Double>();
		studentTable.put(alan, 3.2);
		studentTable.put(ada, 3.5);
		studentTable.put(edsgar, 3.8);
		studentTable.put(grace, 4.0);
	}

	@Test
	void testClear() {
		assertTrue(intKeyTable.size() > 0);
		intKeyTable.clear();
		assertTrue(intKeyTable.size() == 0);
		assertTrue(intKeyTable.isEmpty());
		
		
		assertTrue(stringKeyTable.size() > 0);
		stringKeyTable.clear();
		assertTrue(stringKeyTable.size() == 0);
		assertTrue(stringKeyTable.isEmpty());
		
		assertTrue(studentTable.size() > 0);
		studentTable.clear();
		assertTrue(studentTable.size() == 0);
		assertTrue(studentTable.isEmpty());
	}
	
	@Test
	void testSudentXHashClear() {
		assertTrue(studentTable.size() > 0);
		studentTable.clear();
		assertTrue(studentTable.size() == 0);
		assertTrue(studentTable.isEmpty());
	}

	@Test
	void testContainsKey() {
		assertTrue(intKeyTable.containsKey(1));
		assertFalse(intKeyTable.containsKey(2));
		
		
		assertTrue(stringKeyTable.containsKey("banana"));
		assertFalse(stringKeyTable.containsKey("kiwi"));
		
		assertFalse(emptyTable.containsKey(1));
	}
	
	@Test
	void testStudentXHashContainsKey() {
		StudentBadHash jason = new StudentBadHash(1234567, "jason", "crandall");
		assertTrue(studentTable.containsKey(alan));
		assertFalse(studentTable.containsKey(jason));
	}

	@Test
	void testContainsValue() {
		assertTrue(intKeyTable.containsValue("pear"));
		assertFalse(intKeyTable.containsValue("tomato"));
		
		
		assertTrue(stringKeyTable.containsValue(9));
		assertFalse(stringKeyTable.containsValue(2));
		
		assertFalse(emptyTable.containsValue("value"));
	}
	
	@Test
	void testStudentXContainsValue() {
		assertTrue(studentTable.containsValue(4.0));
		assertFalse(studentTable.containsValue(1.1));
	}

	@Test
	void testEntries() {
		List<MapEntry<Integer, String>> list1 = intKeyTable.entries();
		assertTrue(list1.size() == intKeyTable.size());
		for (MapEntry<Integer, String> entry : list1) {
			assertTrue(intKeyTable.containsKey(entry.getKey()));
			assertTrue(intKeyTable.containsValue(entry.getValue()));
		}
		
		
		List<MapEntry<String, Integer>> list2 = stringKeyTable.entries();
		assertTrue(list2.size() == stringKeyTable.size());
		for (MapEntry<String, Integer> entry : list2) {
			assertTrue(stringKeyTable.containsKey(entry.getKey()));
			assertTrue(stringKeyTable.containsValue(entry.getValue()));
		}
		
		List<MapEntry<Integer, String>> list3 = emptyTable.entries();
		assertEquals(0, list3.size());
	}
	
	@Test
	void testStudentXEntries() {
		List<MapEntry<StudentBadHash, Double>> list = studentTable.entries();
		assertTrue(list.size() == studentTable.size());
		for (MapEntry<StudentBadHash, Double> entry : list) {
			assertTrue(studentTable.containsKey(entry.getKey()));
			assertTrue(studentTable.containsValue(entry.getValue()));
		}
	}

	@Test
	void testGet() {
		assertEquals("pear", intKeyTable.get(9));
		assertNull(intKeyTable.get(14));
		
		
		assertTrue(9 == stringKeyTable.get("pear"));
		assertNull(stringKeyTable.get("kiwi"));
		
		assertNull(emptyTable.get(0));
	}
	
	@Test
	void testStudentXGet() {
		StudentBadHash jason = new StudentBadHash(1234567, "jason", "crandall");
		assertTrue(4.0 == studentTable.get(grace));
		assertNull(studentTable.get(jason));
	}

	@Test
	void testIsEmpty() {
		assertFalse(intKeyTable.isEmpty());
		intKeyTable.clear();
		assertTrue(intKeyTable.isEmpty());
		
		
		assertFalse(stringKeyTable.isEmpty());
		stringKeyTable.clear();
		assertTrue(stringKeyTable.isEmpty());
		
		assertTrue(emptyTable.isEmpty());
	}
	
	@Test
	void testStudentXIsEmpty() {
		assertFalse(studentTable.isEmpty());
		studentTable.clear();
		assertTrue(studentTable.isEmpty());
	}

	@Test
	void testPut() {
		assertNull(intKeyTable.put(16, "kiwi"));
		assertEquals("kiwi", intKeyTable.put(16, "strawberry"));
		
		
		assertNull(stringKeyTable.put("kiwi", 16));
		assertTrue(16 == stringKeyTable.put("kiwi", 18));
		
		assertNull(emptyTable.put(16, "kiwi"));
		assertFalse(emptyTable.isEmpty());
	}
	
	@Test
	void testStudentXPut() {
		StudentBadHash jason = new StudentBadHash(1234567, "jason", "crandall");
		assertNull(studentTable.put(jason, 4.0));
		assertTrue(4.0 == studentTable.put(jason, 3.99));
	}

	@Test
	void testRemove() {
		assertNull(intKeyTable.remove(16));
		assertEquals("banana", intKeyTable.remove(1));
		
		
		assertNull(stringKeyTable.remove("kiwi"));
		assertTrue(1 == stringKeyTable.remove("banana"));
		
		assertNull(emptyTable.remove(2));
	}
	
	@Test
	void testStudentXRemove() {
		StudentBadHash jason = new StudentBadHash(1234567, "jason", "crandall");
		assertNull(studentTable.remove(jason));
		assertTrue(3.2 == studentTable.remove(alan));
	}

	@Test
	void testSize() {
		assertEquals(6, intKeyTable.size());
		intKeyTable.put(16, "kiwi");
		assertEquals(7, intKeyTable.size());
		intKeyTable.remove(1);
		assertEquals(6, intKeyTable.size());
		intKeyTable.clear();
		assertEquals(0, intKeyTable.size());
		
		
		assertEquals(6, stringKeyTable.size());
		stringKeyTable.put("kiwi", 16);
		assertEquals(7, stringKeyTable.size());
		stringKeyTable.remove("banana");
		assertEquals(6, stringKeyTable.size());
		stringKeyTable.clear();
		assertEquals(0, stringKeyTable.size());
		
		assertEquals(0, emptyTable.size());
	}
	
	@Test
	void testStudentXSize() {
		StudentBadHash jason = new StudentBadHash(1234567, "jason", "crandall");
		assertEquals(4, studentTable.size());
		studentTable.put(jason, 4.0);
		assertEquals(5, studentTable.size());
		studentTable.clear();
		assertEquals(0, studentTable.size());
	}

}
