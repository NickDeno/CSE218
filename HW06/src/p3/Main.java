package p3;

public class Main {

	public static void main(String[] args) {
		Integer[] intArr = {1, 3, 5, 2, 8, 5, 6};
		Double[] doubArr = {1.0, 3.0, 5.0, 2.0, 8.0, 5.0, 6.0};
		String[] strArr = {"A", "AAAA", "AA", "AAA"};
		
		System.out.println("Max Value of Integer Array:");
		System.out.println(Util.maxElement(intArr));
		System.out.println();
		
		System.out.println("Max Value of Double Array:");
		System.out.println(Util.maxElement(doubArr));
		System.out.println();
		
		System.out.println("Max Value of String Array:");
		System.out.println(Util.maxElement(strArr));
		System.out.println();
		
	}

}
