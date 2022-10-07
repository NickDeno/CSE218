package p1;

import java.util.ArrayList;

public class Queue {
	private ArrayList<String> arr;
		
	public Queue(int prefSize) {
		arr = new ArrayList<String>(prefSize);
	}
		
	public void add(String value) {
		arr.add(value);
	}
		
	public String remove() {
		return arr.remove(0);
	}
		
	public boolean isEmpty() {
		return arr.isEmpty();
	}
}

