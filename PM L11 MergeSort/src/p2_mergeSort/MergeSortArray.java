package p2_mergeSort;

public class MergeSortArray {
	private int[] theArray;
	private int nElems;
	
	public MergeSortArray(int maxSize) {
		theArray = new int[maxSize];
		nElems = 0;
	}
	
	public void insert(int value) {
		theArray[nElems++] = value;
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.println();
	}
	
	public void mergeSort() {
		int[] workspace = new int[nElems];
		recMergeSort(workspace, 0, nElems-1);
	}

	private void recMergeSort(int[] workspace, int lowerBound, int upperBound) {
		if(lowerBound == upperBound) return; // Base case.
		int mid = (lowerBound + upperBound)/2;
		recMergeSort(workspace, lowerBound, mid);
		recMergeSort(workspace, mid+1, upperBound);
		merge(workspace, lowerBound, mid+1, upperBound);
	}

	private void merge(int[] workspace, int lowPtr, int highPtr, int upperBound) {
		int j = 0; 
		int lowerBound = lowPtr;
		int mid = highPtr-1; //Last element "arrA"
		int n = upperBound-lowerBound +1;
		
		// lowPtr = "aIdx", highPtr = "bIdx", j = "cIdx", workspace = "arrC"
		while(lowPtr <= mid && highPtr <= upperBound) { //While neither sub array is done
			if(theArray[lowPtr] < theArray[highPtr]) {
				workspace[j++] = theArray[lowPtr++];
			} else {
				workspace[j++] = theArray[highPtr++];
			}
		}
		while(lowPtr <= mid) workspace[j++] = theArray[lowPtr++]; //Runs if upper half("arrB") is done copying but lower half is not("arrA")
		while(highPtr <= upperBound) workspace[j++] = theArray[highPtr++]; //Runs if lower half("arrA") is done copying but upper half is not("arrB") 
		
		//Copy sorted elements back into original array after merging.
		for(j = 0; j < n; j++) {
			theArray[lowerBound + j] = workspace[j];
		}
		
		
		
		
	}
}
