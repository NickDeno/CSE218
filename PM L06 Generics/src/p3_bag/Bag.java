package p3_bag;

public class Bag <E> {
	private E[] arr;
	private int nElems;
	
	public Bag(int maxSize) {
		arr =(E[]) new Object[maxSize];
		nElems = 0;
	}
	
	public void insert(E value) {
		arr[nElems++] = value;
	}
	
	public void display() {
		for(int i = 0; i <nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
}
