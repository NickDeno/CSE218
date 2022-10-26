package p3;

import java.util.Arrays;

public class Util {
	public static <E extends Comparable<E>> E maxElement(E[] arr, int idx) {
		if(idx == arr.length-1) return arr[idx];
		return max(arr[idx], maxElement(arr, idx+1));	
	}
	
	//Overloaded maxElement method. More intuitive for user as they do not have to pass through an index.
	//However this method is less efficient since a copy of the array is created each time.
	public static <E extends Comparable<E>> E maxElement(E[] arr) {
		if(arr.length-1 == 0) return arr[arr.length-1];
		return max(arr[0], maxElement(Arrays.copyOfRange(arr, 1, arr.length)));
	}

	private static <E extends Comparable<E>> E max(E e1, E e2) {
		if(e1.compareTo(e2) > 1) return e1;
		if(e1.compareTo(e2) < 1) return e2;
		return e1;
		
	}
}
