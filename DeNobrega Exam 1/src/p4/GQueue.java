package p4;

import java.util.LinkedList;

public class GQueue <E> {
	private LinkedList<E> list;
	
	public GQueue() {
		list = new LinkedList<E>();
	}
	
	public void display() {
		for(int i  = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
	}
	
	public void add(E value) {
		list.addLast(value);
	}
		
	public E remove() {
		return list.removeFirst();
	}
		
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
