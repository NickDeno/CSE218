package p3;

public class Demo {
	public static void main(String args[]) {
		GStack<Integer> myStack = new GStack<Integer>(10);
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.display();
		
		while(!myStack.isEmpty()) {
			System.out.println(myStack.pop());
		}
		
	}
}
