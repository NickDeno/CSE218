package p5;

import java.util.Arrays;

public class Util {
	//Overloaded characterCounter Method. Traverses array from each end. Checks if leftIdx and rightIdx are equal to key. Then increments leftIdx by 
	//1 and decrements rightIdx by 1. More efficient since fewer stack frames created => less memory used => possibility of StackOverflowError is lower.
	public static int characterCounter(char[] arr, char key, int leftIdx, int rightIdx) {
		if(leftIdx == rightIdx) return 0;
		int count = 0;
		if(arr[leftIdx] == key) count++;
		if(arr[rightIdx] == key) count++;
		return count + characterCounter(arr, key, leftIdx+1, rightIdx-1);
	}
	
	//Overloaded characterCounter method. Checks if first element in array is equal to key. Then passes a copy of original array with first element removed recursively.
	//This method is more intuitive for user as they do not have to pass through a leftIdx and rightIdx parameter. However it is less effiecent for each iteration, a
	//copy of the previous array must be made excluding first element.
	public static int characterCounter(char[] arr, char key) {
		if(arr.length == 0) return 0;
		if(arr[0] == key) return 1 + characterCounter(Arrays.copyOfRange(arr, 1, arr.length), key);
		return  characterCounter(Arrays.copyOfRange(arr, 1, arr.length), key);
	}
}
