package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class checks to see if two words are anagrams of each other non case sensitive
 * and also gets the largest group of anagrams found in an array of strings or from a 
 * file list of words.
 * 
 * @author Jason Crandall and Michael Hart
 * @version February 6, 2019
 *
 */
public class AnagramChecker {
	
	/**
	 * Converts a string to a character array and, using an insertion sort,
	 * sorts the characters in lexiographical order. Returns this new array
	 * as a string.
	 * 
	 * @param s - String to be sorted
	 * @return stringSorted - the sorted string
	 */
	public static String sort(String s) {
		char[] string = s.toCharArray();
		for (int i = 1; i < string.length; i++) {
			char c = string[i];
			int j;
			for(j = i-1; j >= 0 && string[j] > c; j--) {
				string[j+1] = string[j];
			}
			string[j+1] = c;		
		}
		String stringSorted = new String(string);
		return stringSorted;
	}
	
	/**
	 * A generic insertion sort that sorts through an array of 
	 * objects using the comparator that is entered as a parameter
	 * 
	 * @param words - list of objects to be sorted
	 * @param cmp - comparator
	 */
	public static <T> void insertionSort(T[] words, Comparator<? super T> cmp) {
		for(int i = 1; i < words.length; i++) {
			T value = words[i];
			int j;
			for (j= i-1; j >= 0 && cmp.compare(words[j], value) > 0; j--) {
				words[j+1] = words[j];
			}
			words[j+1] = value;
		}
		return;
	}
	
	/**
	 * Compares two strings using the sort(String) method and returns true
	 * if they are equal (anagrams) and false otherwise.
	 * 
	 * @param a - String
	 * @param b - String
	 * @return
	 */
	public static boolean areAnagrams(String a, String b) {
		if (sort(a.toLowerCase()).equals(sort(b.toLowerCase())))
			return true;
		else
			return false;
	}
	
	/**
	 * Sorts the array by grouping anagrams together via the insertionSort method.
	 * Iterates through the sorted array by keeping track of the start and end indexes of the 
	 * groups of anagrams. The largest group is then stored in a new array that is 
	 * returned.
	 * 
	 * @param list - list of strings
	 * @return biggestGroup - an array of the biggest group of anagrams in the list
	 */
	public static String[] getLargestAnagramGroup(String[] list) {
		if(list.length == 0)
			return list;
		// Calls insertion sort with a comparator that sorts by anagram
		//insertionSort(list, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		Arrays.sort(list, ((i1,i2) -> sort(i1.toLowerCase()).compareTo(sort(i2.toLowerCase()))));
		// Search the array by marking the start and end indexes of the groups of anagrams
		// and storing the one with the largest gap ie the largest group
		int startIndex = 0;
		int biggestStartIndex = 0;
		int endIndex = 0;
		int biggestEndIndex = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (!areAnagrams(list[i], list[i-1])){
				endIndex = i-1;
				if ((endIndex - startIndex) > (biggestEndIndex - biggestStartIndex)) {
					biggestEndIndex = endIndex;
					biggestStartIndex = startIndex;
				}
				startIndex = i;
			}
			if (i == list.length -1) {
				endIndex = list.length - 1;
				if ((endIndex - startIndex) > (biggestEndIndex - biggestStartIndex)) {
					biggestEndIndex = endIndex;
					biggestStartIndex = startIndex;
				}
			}
			
		}
		
		if((biggestEndIndex - biggestStartIndex) == 0) {
			String[] empty = new String[] {};
			return empty;
		}
		
		// Store the items between the two biggest indexes into a new array
		String[] biggestGroup = new String[biggestEndIndex - biggestStartIndex + 1];
		for (int i = 0; i < biggestGroup.length; i++) {
			if (biggestStartIndex <= biggestEndIndex) {
				biggestGroup[i] = list[biggestStartIndex];
				biggestStartIndex++;
			}
		}
		
		return biggestGroup;
	}
	
	/**
	 * Imports a file with a list of words and calls the
	 * getLargestAnagramGroup(String[] list) method on that list.
	 * 
	 * @param filename - name of the file with a word list
	 * @return an array of the biggest group of anagrams from the file
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		String[] words = readInFile(filename);
		return getLargestAnagramGroup(words);
	}
	
	/**
	 * Uses a buffer reader to read in words from a file and store them in
	 * an ArrayList. That list is then converted to an array of Strings and
	 * is returned.
	 * 
	 * @param filename - name of the file
	 * @return finalList - an array of words from the file
	 */
	private static String[] readInFile(String filename) {
		ArrayList<String> words = new ArrayList<String>();
		try 
		{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
			while(reader.ready())
				words.add(reader.readLine());

			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] finalList = new String[words.size()];
		finalList = words.toArray(finalList); 
		return finalList;
		
	}
}