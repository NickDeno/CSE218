package p2;

import java.util.ArrayList;

public class PQueue { // Implements a priority queue that gives highest priority to highest number.
	private ArrayList<Double> arr;
	
	public PQueue(int prefSize) {
		arr = new ArrayList<Double>(prefSize);
	}
	
	public void add(double value) {
		if(arr.isEmpty()) {
			arr.add(value);
		} else {
			for(int i = 0; i < arr.size(); i++){
				if(value > arr.get(i)) {
					arr.add(i, value);
					break;
				}
			}
		}
		
	}
	
	public double remove() {
		return arr.remove(0);
	}
	
	public double peekMin() {
		return arr.get(arr.size()-1);
	}
	
	public boolean isEmpty() {
		return arr.isEmpty();
	}
	
}
