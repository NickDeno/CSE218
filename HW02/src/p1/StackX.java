package p1;

import java.util.ArrayList;

public class StackX {
	private ArrayList<String> arr;
	private int top;
	
	public StackX(int maxSize) {
		arr = new ArrayList<>(maxSize);
		top = -1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public void push(String str) {
		arr.add(++top,str);
	}
	
	public String pop() {
		if(!arr.isEmpty()) {
			return arr.remove(top--);
		} else {
			System.out.println("No element present to pop.");
			return null;
		}
	}
	
	public String peek() {
		if(!arr.isEmpty()) {
			return arr.get(top);	
		} else {
			System.out.println("No element present to peek.");
			return null;
		}
	}
}
