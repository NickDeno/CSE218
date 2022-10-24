package p5;

public class Util {
	//Default characterCounter Method. Traverses array from left to right checking if each character is equal to key. Less efficient
	//since more stack frames are created => more memory used => possibility of StackOverflowError is higher
	public static int characterCounter(char[] arr, char key, int idx) {
		if(idx == arr.length) return 0;
		if(arr[idx] == key) return 1 + (characterCounter(arr, key, idx+1));
		return characterCounter(arr, key, idx+1);
	}
	
	//Overloaded characterCounter Method. Traverses array from each end. Checks if leftIdx and rightIdx are equal to key. Then increments leftIdx by 
	//1 and decrements rightIdx by 1. More efficient since fewer stack frames created => less memory used => possibility of StackOverflowError is lower.
	public static int characterCounter(char[] arr, char key, int leftIdx, int rightIdx) {
		if(leftIdx == rightIdx) return 0;
		int count = 0;
		if(arr[leftIdx] == key) count++;
		if(arr[rightIdx] == key) count++;
		return count + characterCounter(arr, key, leftIdx+1, rightIdx-1);
	}
}
