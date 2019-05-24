package assign04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class AnagramCheckerJavaSort {
	
	public static String sortString(String s) {
		s = s.toLowerCase();
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
	
	public static boolean areAnagrams(String a, String b) {
		if (sortString(a).equals(sortString(b)))
			return true;
		else
			return false;
	}
	
	public static String[] getLargestAnagramGroup(String[] list) {
		if(list.length == 0)
			return list;
		Arrays.sort(list, ((i1,i2) -> sortString(i1.toLowerCase()).compareTo(sortString(i2.toLowerCase()))));
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
		String[] biggestGroup = new String[biggestEndIndex - biggestStartIndex + 1];
		for (int i = 0; i < biggestGroup.length; i++) {
			if (biggestStartIndex <= biggestEndIndex) {
				biggestGroup[i] = list[biggestStartIndex];
				biggestStartIndex++;
			}
		}
		
		return biggestGroup;
	}
	
	public static String[] getLargestAnagramGroup(String filename) {
		String[] words = readInFile(filename);
		return getLargestAnagramGroup(words);
	}
	
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
