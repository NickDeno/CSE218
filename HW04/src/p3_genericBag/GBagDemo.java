package p3_genericBag;

import java.util.Arrays;

public class GBagDemo {
	
	public static void main(String[] args) {
		GBag<Integer> intBag = new GBag(Integer.class, 6);
		GBag<String> strBag = new GBag(String.class, 6);
		
		intBag.insert(1);
		intBag.insert(2);
		intBag.insert(3);
		intBag.insert(4);
		intBag.insert(5);
		intBag.insert(3);
		
		strBag.insert("One");
		strBag.insert("Two");
		strBag.insert("Three");
		strBag.insert("Four");
		strBag.insert("Five");
		strBag.insert("Three");
		
		intBag.sort();
		strBag.sort();
		
		//Original Bags
		System.out.println("Original Bags:");
		intBag.display();
		strBag.display();
		
		//Testing Predicate Search method
		System.out.println("Search Results");
		Integer[] intSearchResults = intBag.search(Integer.class, p -> p.equals(2));
		String[] strSearchResults = strBag.search(String.class, p -> p.equals("Two"));
		System.out.println(Arrays.toString(intSearchResults));
		System.out.println(Arrays.toString(strSearchResults));
		System.out.println();
		
		//Testing Predicate Remove method
		System.out.println("Remove Results");
		Integer[] intRemoveResults = intBag.remove(Integer.class, p -> p.equals(3));
		String[] strRemoveResults = strBag.remove(String.class, p -> p.equals("Three"));
		System.out.println(Arrays.toString(intRemoveResults));
		System.out.println(Arrays.toString(strRemoveResults));
		System.out.println();
		
		System.out.println("Bags After Removal");
		intBag.insert(6);
		intBag.display();
		strBag.display();
		
	}
}
