package p2_quickSort;

public class ArrayQuick {
	private long[] arr;
	private int nElems;

	public ArrayQuick(int maxSize) {
		arr = new long[maxSize];
	}

	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}

	public void recQuickSort(int left, int right) {
		if (left >= right) {
			return;
		} else {
			long pivot = arr[right];
			int partition = partitionIt(left, right, pivot);
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}

	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left - 1;
		int rightPtr = right + 1;

		while (true) {
			while (leftPtr < right && arr[++leftPtr] < pivot) {
				; // nop
			}

			while (rightPtr > left && arr[--rightPtr] >= pivot) {
				; // nop
			}

			if (leftPtr >= rightPtr) {
				break;
			} else {
				swap(leftPtr, rightPtr);
			}

		}
		swap(leftPtr, right);
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
		for (int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
