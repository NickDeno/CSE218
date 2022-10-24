package p1;

public class Util {
	//Default isMember method. Searches each element from left to right until end of array. Less efficient since more stack 
	//frames are created => more memory used => possibility of StackOverflowError is higher.
	public static <E> boolean isMember(E[] arr, E key, int idx) {
		if(idx == arr.length) return false;
		if(arr[idx].equals(key)) return true;
		return isMember(arr, key, idx+1);
	}
	
	//Overloaded isMember method. Searches element on left and right end of array. Then increments from left and decrements from right to 
	//finally meet in the middle. I.E fewer stack frames are created this way => less memory used => possibility of StackOverflowError is lower.
	public static <E> boolean isMember(E[] arr, E key, int leftIdx, int rightIdx) {
		if(rightIdx < leftIdx) return false;
		if(arr[leftIdx].equals(key) || arr[rightIdx].equals(key)) return true;
		return isMember(arr, key, leftIdx+1, rightIdx-1);
	}
}
