package p1_stack;

public class StackX {
	
	private long[] arr;
	private int top;
	private int maxSize;
	
	public StackX(int maxSize) {
		arr = new long[maxSize];
		this.maxSize = maxSize;
		top = -1;
	}
	
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public void push(long value) {
		arr[++top] = value;
	}
	
	public long pop() { // Returns most recent elem and "removes" it by decrementing top by 1
		return arr[top--];
	}
	
	public long peek() { //Returns most recent elem without removing it
		return arr[top];
	}
}
