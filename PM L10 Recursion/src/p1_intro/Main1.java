package p1_intro;

import java.util.Arrays;

public class Main1 {

	public static void main(String[] args) {
		int x  = 2;
		int y  = 5;
		int[] z = {1, 2, 3, 4, 5};
		showX(x);
		showZ(z);
		System.out.println("In the main method, x = " + x);
		
		System.out.println("Z array: " + Arrays.toString(z));
	}
	
	private static void showX(int xx) {
		xx = 2 * xx;
		System.out.println("In the showX method, xx = " + xx);
	}
	

	private static void showZ(int[] zz) {
		zz[0] = 100;
		System.out.println("In the showZ method, zz = " + Arrays.toString(zz));
	}


}
