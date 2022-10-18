package p3;

import java.util.ArrayList;

public class GStack <E> {
	private ArrayList<E> arr;
	private int top;
	
	//prefSize != maxSize, just gives a starting point.
	public GStack(int prefSize) {
		arr = new ArrayList<E>(prefSize);
		top = -1;
	}
	
	public void display() {
		for(int i = top; i >=0; i--) {
			System.out.println(arr.get(i));
		}
		System.out.println();
	}
	
	public void push(E value) {
		arr.add(++top, value);
	}
	
	public E pop() {
		return arr.remove(top--);
	}
	
	public E peek() { 
		return arr.get(top);
	}
	
	public boolean isEmpty() {
		return arr.isEmpty();
	}
	
	
	
	
}
