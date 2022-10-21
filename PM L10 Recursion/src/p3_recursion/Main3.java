package p3_recursion;

public class Main3 {

	public static void main(String[] args) {
		int x = 1;
		showX(x);
	}

	private static void showX(int x) {
		if(x>100) return; //Base case. Prevents recursive method from causing stackOverflowError.
		x = 2 * x;
		System.out.println(x);
		showX(x);
	}

}
