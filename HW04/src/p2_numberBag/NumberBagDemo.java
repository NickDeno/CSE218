package p2_numberBag;

public class NumberBagDemo {
	
	public static void main(String[] args) {
		NumberBag<Integer> intBag = new NumberBag(5);
		NumberBag<Double> doubBag = new NumberBag(5);
		
		intBag.insert(1);
		intBag.insert(5);
		intBag.insert(3);
		intBag.insert(2);
		intBag.insert(4);
		
		doubBag.insert(1.0);
		doubBag.insert(5.0);
		doubBag.insert(3.0);
		doubBag.insert(2.0);
		doubBag.insert(4.0);
		
		//Testing if Bags are sorted from insertion method
		intBag.display();
		doubBag.display();
	
		//Testing search method
		System.out.println("Search Results:");
		Integer intSearchResult = intBag.search(3);
		Double doubSearchResult = doubBag.search(3.0);
		System.out.println(intSearchResult);
		System.out.println(doubSearchResult);
		System.out.println();
		
		//Testing remove method
		System.out.println("Remove Results");
		Integer intRemoveResult = intBag.remove(3);
		Double doubRemoveResult = doubBag.remove(3.0);
		System.out.println(intRemoveResult);
		System.out.println(doubRemoveResult);
		System.out.println();
		
		//Bag after removal
		System.out.println("Bags after removal:");
		intBag.display();
		doubBag.display();
	}
}	
