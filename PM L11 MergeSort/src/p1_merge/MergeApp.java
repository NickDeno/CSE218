package p1_merge;

public class MergeApp {

	public static void main(String[] args) {
		int[] arrA = {1,4,7,9,12};
		int[] arrB = {2,6,8,13,20};
		int[] arrC = new int[arrA.length + arrB.length];
		
		merge(arrA, arrA.length, arrB, arrB.length, arrC);
		display(arrC);
	}

	private static void display(int[] arr) {
		for(int i: arr) {
			System.out.print(i + ", ");
		}
	}

	private static void merge(int[] arrA, int sizeA, int[] arrB, int sizeB, int[] arrC) {
		int idxA = 0;
		int idxB = 0;
		int idxC = 0;
		
		while(idxA < sizeA && idxB < sizeB) { // Neither arrA nor arrB is finished
			if(arrA[idxA] < arrB[idxB]) {
				arrC[idxC++] = arrA[idxA++];
			} else {
				arrC[idxC++] = arrB[idxB++];
				
			}
		}
		
		// arrB is done copying
		while(idxA < sizeA) arrC[idxC++] = arrA[idxA++];
	
		//arrA is done copying
		while(idxB < sizeB) arrC[idxC++] = arrB[idxB++];
	}

}
