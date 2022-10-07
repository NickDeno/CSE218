package p2_generic_arrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class Bag<E> {
	private E[] arr;
	private int nElems;

	@SuppressWarnings("unchecked")
	public Bag(Class<E> clss, int maxSize) {
		arr = (E[]) Array.newInstance(clss, maxSize);
		nElems = 0;
	}

	public void insert(E value) {
		arr[nElems++] = value;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
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
}
