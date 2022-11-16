package p1_partition;

public class ArrayPar {
	private long[] arr;
	private int nElems;

	public ArrayPar(int maxSize) {
		arr = new long[maxSize];
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left - 1;
		int rightPtr = right + 1;
		
		while(true) {
			while(leftPtr < right && arr[++leftPtr] < pivot) {
				; // nop
			}
			
			while(rightPtr > left && arr[--rightPtr] >= pivot) {
				; // nop
			}
			
			if(leftPtr >= rightPtr) {
				break;
			} else {
				swap(leftPtr, rightPtr);
			}
			
		}
		return leftPtr;
	}

	private void swap(int leftPtr, int rightPtr) {
		long temp = arr[rightPtr];
		arr[rightPtr] = arr[leftPtr];
		arr[leftPtr] = temp;
	}
	
	public void insert(long value) {
		arr[nElems++] = value;
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
