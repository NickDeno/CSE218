package p1_genericStack;

import java.util.Arrays;

public class GStackDemo {

	public static void main(String[] args) {
		GStack<Integer> intStack = new GStack(Integer.class, 10);
		GStack<String> strStack = new GStack(String.class, 10);
		GStack<Double> doubStack = new GStack(Double.class, 10);
		intStack.push(10);
		intStack.push(20);
		strStack.push("Ten");
		strStack.push("Twenty");
		doubStack.push(10.0);
		doubStack.push(20.0);
		
		//Display Original Stacks
		System.out.println("Original Stacks:");
		intStack.display();
		strStack.display();
		doubStack.display();
		
		//Testing pushAll method
		Integer[] intArr = {30,40,50};
		String[] strArr = {"Thirty","Fourty","Fifty"};
		Double[] doubArr = {30.0,40.0,50.0};
		intStack.pushAll(intArr);
		strStack.pushAll(strArr);
		doubStack.pushAll(doubArr);
		
		//Stacks after pushAll
		System.out.println("Stacks After Calling pushAll:");
		intStack.display();
		strStack.display();
		doubStack.display();
		
		//Testing popAll method
		System.out.println("Arrays Containing Stacks Using popAll:");
		Integer[] intPopStack = intStack.popAll(Integer.class);
		String[] strPopStack = strStack.popAll(String.class);
		Double[] doubPopStack = doubStack.popAll(Double.class);
		System.out.println(Arrays.toString(intPopStack));
		System.out.println(Arrays.toString(strPopStack));
		System.out.println(Arrays.toString(doubPopStack));
	
		
	}

}
