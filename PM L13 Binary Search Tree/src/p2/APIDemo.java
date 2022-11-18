package p2;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class APIDemo {

	public static void main(String[] args) {
//		TreeMap<String, Destination> theMap = new TreeMap<>();
//		theMap.put("M", new Destination("Germany", 10));
//		theMap.put("G", new Destination("France", 5));
//		theMap.put("Z", new Destination("Poland", 20));
//		theMap.put("K", new Destination("Czeck", 12));
//		theMap.put("G", new Destination("Russia", 10));
//		
//		
//		
//		if (theMap.containsKey("B")) {
//			System.out.println("Key Already used!");
//		} else {
//			theMap.put("B", new Destination("U.K.", 10));
//		}
//		theMap.put("N", new Destination("Iceland", 30));

//		System.out.println(theMap);
//		System.out.println(theMap.get("G"));

//		Iterator<Destination> iterator = theMap.values().iterator();
//		while(iterator.hasNext()) {
//			Destination value = iterator.next();
//			if(value.getRiskFactor() <= 10) {
//				System.out.println(value); // stream filter
//			}
//		}

//		Iterator<String> iteratorKey = theMap.keySet().iterator();
//		while(iteratorKey.hasNext()) {
//			String key = iteratorKey.next();
//			if(key.equals("M")) {
//				System.out.println(theMap.get(key)); // stream filter
//			}
//		}
		

//		TreeSet<User> theTree = new TreeSet<>();
//		User u1 = new User("A", "A");
//		User u2 = new User("M", "M");
//		User u3 = new User("C", "C");
//		User u4 = new User("A", "C");
//		theTree.add(u1);
//		theTree.add(u2);
//		theTree.add(u3);
//		boolean b = theTree.add(u4);
//		System.out.println(b); // false
////	
//		for(User s : theTree) {
//			System.out.println(s);
//		}
//		System.out.println(theTree.contains(new User("A", "Z")));
//		
		TreeSet<String> theTree = new TreeSet<>();
		theTree.add("M");
		theTree.add("F");
		theTree.add("G");
		theTree.add("A");
		theTree.add("K");
		theTree.add("S");
		theTree.add("Y");
		theTree.add("N");
		theTree.add("F");
		
		
		Iterator<String> iterator = theTree.iterator();

		
		for(String s : theTree) {
			System.out.println(s);
		}
		System.out.println(theTree.contains("A"));
	}

}
