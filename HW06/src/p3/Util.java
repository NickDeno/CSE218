package p3;

public class Util {
	public static <E extends Comparable<E>> E maxElement(E[] arr, int idx) {
		if(idx == arr.length-1) return arr[idx];
		return max(arr[idx], maxElement(arr, idx+1));	
	}

	private static <E extends Comparable<E>> E max(E e1, E e2) {
		if(e1.compareTo(e2) > 1) return e1;
		if(e1.compareTo(e2) < 1) return e2;
		return e1;
		
	}
}
