package p4_binarySearch;

public class Main4 {

	public static void main(String[] args) {
		int[] arr = {1, 4, 6, 8, 9, 12, 34, 56, 89, 99};
		int idxFound = binarySearch(arr, 0, arr.length -1, 8);
		System.out.println(idxFound);
	}

	private static int binarySearch(int[] arr, int left, int right, int key) {
		int mid = (left+right)/2;
		if(left > right) return -1;
		
		if(arr[mid] == key) {
			return mid;
		} else if(arr[mid] > key) {
			right = mid - 1;
			return binarySearch(arr, left, right, key);
		} else {
			left = mid + 1;
			return binarySearch(arr, left, right, key);
		}
	}

}
