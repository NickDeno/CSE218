package p4_genericBagStudent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class GBag <E extends Comparable<E>> {
	private E[] arr;
	private int nElems;
	
	@SuppressWarnings("unchecked")
	public GBag(Class<E> clss, int maxSize) {
		arr = (E[]) Array.newInstance(clss, maxSize);
		nElems = 0;
	}
	
	public void insert(E value) {
		arr[nElems++] = value;
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}

	@SuppressWarnings("unchecked")
	public E[] search(Class<E> clss, Predicate<E> predicate) {
		E[] temp = (E[]) Array.newInstance(clss, nElems);
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (predicate.test(arr[i])) {
				temp[count++] = arr[i];
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	@SuppressWarnings("unchecked")
	public E[] binarySearch(Class<E> clss, E value) {
		E[] temp = (E[]) Array.newInstance(clss, nElems);
		int count = 0;
		int left = 0;
		int right = nElems;
		
		while(left <= right) {
			int mid = (left + right)/2;

			if(arr[mid].compareTo(value) > 0) {
				right = mid - 1;
			} 
			else if(arr[mid].compareTo(value) < 0) {
				left = mid + 1;
			} 
			else {
				temp[count++] = arr[mid];
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	@SuppressWarnings("unchecked")
	public E[] remove(Class<E> clss, Predicate<E> predicate) {
		E[] temp = (E[]) Array.newInstance(clss, nElems);
		int count = 0;
		for(int i = 0; i <nElems; i++) {
			if(predicate.test(arr[i])) {
				temp[count++] = arr[i];
				
				for(int j = i; j < nElems-1; j++) {
					arr[j] = arr[j+1];
				}
				nElems--;
				i--;
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	//Overloaded sort method. User can either sort bag by default compareTo method of object, or specify their own
	public void sort(Comparator<E> comparator) {
		Arrays.sort(arr, comparator);
	}
	
	public void sort() {
		Arrays.sort(arr);
	}

}
