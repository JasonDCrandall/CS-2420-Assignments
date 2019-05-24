package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester for the AnagramChecker class
 * 
 * @author Jason Crandall and Michael Hart
 * @version February 6, 2019
 *
 */
class AnagramCheckerTest extends AnagramChecker {
	
	private String simpleString, stringNull, sortedString, charString;
	private String[] simpleArray, nullArray, medArray, textExpectedArray, allCaps, 
	sizeOne, emptyArray, mixNullArr, noAnagrams; 
	private Integer[] intArray;
	private Character[] charArray;
	
	@BeforeEach
	void setup() throws Exception{
	
		simpleString = new String("iPhone");
		stringNull = null; 
		sortedString = new String("abc");
		charString = new String("a");
	
		simpleArray = new String[] {"hij", "abc", "def"};
		nullArray = null;
		medArray = new String[] {"abc", "tlk", "cba", "klt", "ret", "bca"};
		textExpectedArray = new String[] {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		intArray = new Integer[] {1,3,5,4,2,8,7,6,9,10};
		allCaps = new String[] {"BAD", "BOOK", "DAB", "ABD", "KOOB", "DBA"};
		sizeOne = new String[] {"A"};
		emptyArray = new String[] {};
		mixNullArr = new String[] {"hi", null};
		noAnagrams = new String[] {"hi", "bye", "byte", "size", "Queue"};
		
		charArray = new Character[] {'b', 'c', 'q', 'f', 'z', 'm'};
	}
	
	@Test
	public void testSortString() {
		assertEquals("Pehino", sort(simpleString));
	}
	
	@Test
	public void testSortStringNull() {
		assertThrows(NullPointerException.class, () -> sort(stringNull));
	}
	
	@Test
	public void testSortStringOneChar() {
		assertEquals("a", sort(charString));
	}
	
	@Test
	public void testSortedString() {
		assertEquals("abc", sort(sortedString));
	}
	
	@Test
	public void testInsertionSortStringLexiographic() {
		String[] s = new String[] {"abc", "def", "hij"};
		insertionSort(simpleArray, ((i1,i2) -> i1.compareTo(i2)));
		assertArrayEquals(s, simpleArray);
	}
	
	@Test
	public void testInsertionSortAnagrams() {
		String[] result = new String[] {"abc", "cba", "bca", "ret", "tlk", "klt"};
		insertionSort(medArray, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		assertArrayEquals(result, medArray);
	}
	
	@Test
	public void testInsertionSortInteger() {
		Integer[] result = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		insertionSort(intArray, (i1,i2) -> i1.compareTo(i2));
		assertArrayEquals(result, intArray);
	}
	
	@Test
	public void testInsertionSortAllCaps() {
		String[] result = new String[] {"BAD", "DAB", "ABD", "DBA", "BOOK", "KOOB"};
		insertionSort(allCaps, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		assertArrayEquals(result, allCaps);
	}
	
	@Test
	public void testInsertionSortNull() {
		assertThrows(NullPointerException.class, () -> 
		insertionSort(nullArray, ((i1,i2) -> i1.compareTo(i2))));
	}
	
	@Test 
	public void testInsertionSortEmptyArr() {
		insertionSort(emptyArray, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		String[] result = new String[] {};
		assertArrayEquals(result, emptyArray);
	}
	
	@Test
	public void testInsertionSortArraySizeOne() {
		insertionSort(sizeOne, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		String[] result = new String[] {"A"};
		assertArrayEquals(result, sizeOne);
	}
	
	@Test 
	public void testInsertionSortArrayMixNullArr() {
		assertThrows(NullPointerException.class, () -> 
		insertionSort(mixNullArr, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase())))));
	}
	
	@Test
	public void testInsertionSortArrayCharArr() {
		insertionSort(charArray, ((i1,i2) -> i1.compareTo(i2)));
		Character[] result = new Character[] {'b', 'c', 'f', 'm', 'q', 'z'};
		assertArrayEquals(result, charArray);
	}
	
	@Test
	public void testAreAnagramsOneChar() {
		assertTrue(areAnagrams(charString, charString));
	}
	
	@Test
	public void testAreAnagrams() {
		String a = new String("boat");
		String b = new String("toba");
		String c = new String("hey");
		assertTrue(areAnagrams(a,b));
		assertFalse(areAnagrams(a,c));
	}
	
	@Test
	public void testAreAnagramsAllCaps() {
		String a = new String("BAD");
		String b = new String("DAB");
		String c = new String("CAB");
		assertTrue(areAnagrams(a,b));
		assertFalse(areAnagrams(a,c));
	}
	
	@Test
	public void testAreAnagramsNull() {
		assertThrows(NullPointerException.class, () -> areAnagrams(stringNull, simpleString));
	}
	
	@Test
	public void testAreAnagramsSameString() {
		assertTrue(areAnagrams(simpleString, simpleString));
	}
	
	@Test
	public void testGetLargestAnagramGroup() {
		String[] result = new String[] {"abc", "cba", "bca"};
		assertArrayEquals(result, getLargestAnagramGroup(medArray));
	}
	
	@Test
	public void testGetLargestAnagramGroupNoAnagrams() {
		String[] result = new String[] {};
		assertArrayEquals(result, getLargestAnagramGroup(simpleArray));
	}
	
	@Test
	public void testGetLargestAnagramGroupSizeOne() {
		String[] result = new String[] {};
		assertArrayEquals(result, getLargestAnagramGroup(result));
	}
	
	@Test
	public void testGetLargestAnagramGroupNull() {
		assertThrows(NullPointerException.class, () -> getLargestAnagramGroup(nullArray));
	}
	
	@Test
	public void testGetLargestAnagramGroupTxtFile() {
		String[] largestGroup = getLargestAnagramGroup("src/assign04/sample_word_list.txt");
		assertArrayEquals(textExpectedArray, largestGroup);
	}

	@Test
	public void testGetLargestAnagramGroupAllCaps() {
		String[] largestGroup = getLargestAnagramGroup(allCaps);
		String[] result = new String[] {"BAD", "DAB", "ABD","DBA"};
		assertArrayEquals(result, largestGroup);
	}
	
	@Test
	public void testGetLargestAnagramGroupEmptyArr() {
		assertArrayEquals(emptyArray, getLargestAnagramGroup(emptyArray));
	}
	
	@Test
	public void testGetLargestAnagramGoupSizeOne() {
		String[] result = new String[] {};
		assertArrayEquals(result, getLargestAnagramGroup(sizeOne));
	}
	
	@Test
	public void testGetLargestAnagramGroupMixNullArr() {
		assertThrows(NullPointerException.class, () -> getLargestAnagramGroup(mixNullArr));
	}
	
	@Test
	public void testGetLargestAnagramGroupNoAnagrams2() {
		String[] result = new String[] {};
		assertArrayEquals(result, getLargestAnagramGroup(noAnagrams));
	}
}
