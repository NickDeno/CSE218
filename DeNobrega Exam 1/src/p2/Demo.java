package p2;

public class Demo {

	public static void main(String[] args) {
		GStack<Double> myStack = new GStack<Double>();
		myStack.push(1.0);
		myStack.push(2.0);
		myStack.push(3.0);
		myStack.display();
		
		while(!myStack.isEmpty()) {
			System.out.println(myStack.pop());
		}
	}

}
