package lab03;

/**
 * This class contains code for completing the Lab 3: Debugging activities.
 * 
 * @author CS 2420 course staff
 * @version January 25, 2019
 */
public class Part1 {

	public static boolean binarySearch(int[] arr, int goal) {
		int low = 0, high = arr.length - 1, mid = 0;
		while(arr[low] <= arr[high]) {
			arr[mid] = (arr[low] + arr[high]) / 2;
			if(goal == arr[mid]) 
				return true;
			else if(goal < arr[mid]) 
				arr[high] = arr[mid] - 1;
			else 
				arr[low] = arr[mid] + 1;
		}
		return false;
	}	
}