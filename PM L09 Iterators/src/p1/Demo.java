package p1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Demo {
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Iterator<Integer> iter1 = list.iterator();
		
		ListIterator<Integer> iter2 = list.listIterator();
		iter2.forEachRemaining(n -> System.out.println());
		
	}
}
