package p2_numberBag;

import java.util.ArrayList;

/*
 * This Bag class sorts on insertion which allows binary search and remove methods.
 * It assumes there is no duplicate numbers in the bag. if duplicates were allowed, search and remove methods would have to 
 * be modified to return an ArrayList rather than a single value to account for duplicates. Another possibility
 * would be to implement a sort method with Collections.sort. However, this would rely on user to call sort
 * method prior to search method for it to work. Doing it this way ensures bag is always sorted and no possible user error.
 */
public class NumberBag <E extends Number & Comparable<E>>{
	private ArrayList<E> arr;
	
	public NumberBag(int prefSize) {
		arr = new ArrayList<>(prefSize);
	}
	
	public void insert(E value) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).compareTo(value) < 0) {
				continue;
			} else if(arr.get(i).equals(value)) {
//				arr.add(i, value); Uncomment this line want to include duplicate values in bag
				return;
			} else {
				arr.add(i, value);
				return;
			}
		}
		arr.add(value);
	}
	
	public void display() {
		for(E e: arr) {
			System.out.println(e);
		}
		System.out.println();
	}
	
	public E search(E value) {
		int left = 0;
		int right = arr.size();
		
		while(left <= right) {
			int mid = (left + right)/2;

			if(arr.get(mid).compareTo(value) > 0) {
				right = mid - 1;
			} 
			else if(arr.get(mid).compareTo(value) < 0) {
				left = mid + 1;
			} 
			else {
				return arr.get(mid);
			}
		}
		return null;
	}
	
	public E remove(E value) {
		int left = 0;
		int right = arr.size();
		
		while(left <= right) {
			int mid = (left + right)/2;

			if(arr.get(mid).compareTo(value) > 0) {
				right = mid - 1;
			} 
			else if(arr.get(mid).compareTo(value) < 0) {
				left = mid + 1;
			} 
			else {
				return arr.remove(mid);
			}
		}
		return null;
	}	
}
