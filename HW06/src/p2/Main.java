package p2;

public class Main {

	public static void main(String[] args) {
		String str = "ABCDE";
		String str2 = "Hello Java World";
		
		System.out.println("Original String: " + str);
		System.out.println("Reversed String: " + Util.stringReverser(str) + "\n");
		
		System.out.println("Original String: " + str2);
		System.out.println("Reversed String: " + Util.stringReverser(str2));
	
	}

}
