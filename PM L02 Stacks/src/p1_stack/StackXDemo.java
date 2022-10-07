package p1_stack;


public class StackXDemo {
	
	public static void main(String[] args) {
//		StackX myStack = new StackX(5);
//		myStack.push(10);
//		myStack.push(20);
//		myStack.push(30);
//		System.out.println(myStack.pop());
//		System.out.println(myStack.pop());
//		myStack.push(40);
//		System.out.println(myStack.pop());
//		
//		if(!myStack.isFull()) {
//			myStack.push(50);
//		} 
//		
//		if(!myStack.isEmpty()) {
//			System.out.println(myStack.pop());
//		}
//		
//		
		StackX myStack = new StackX(5);
		while(!myStack.isFull()) {
			myStack.push((long)(Math.random() * 10));
		}
	
	}
}
