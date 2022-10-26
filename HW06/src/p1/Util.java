package p1;

import java.util.Arrays;

public class Util {	
	//Overloaded isMember method. Searches element on left and right end of array. Then increments from left and decrements from right to 
	//finally meet in the middle. I.E fewer stack frames are created this way => less memory used => possibility of StackOverflowError is lower.
	public static <E> boolean isMember(E[] arr, E key, int leftIdx, int rightIdx) {
		if(rightIdx < leftIdx) return false;
		if(arr[leftIdx].equals(key) || arr[rightIdx].equals(key)) return true;
		return isMember(arr, key, leftIdx+1, rightIdx-1);
	}
	
	//Overloaded isMember method. In this method, user does not have to pass through a "counter" variable when calling. Instead
	//a new array is created each time with the first value excluded. However, this causes method to be less efficient due to the
	//nature of creating a copy of an array.
	public static <E> boolean isMember(E[] arr, E key) {
		if(arr.length == 0) return false;
		if(arr[0].equals(key)) return true;
		return isMember(Arrays.copyOfRange(arr, 1, arr.length), key);
	}
}
