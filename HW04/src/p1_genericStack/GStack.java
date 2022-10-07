package p1_genericStack;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GStack <E> {
	private E[] arr;
	private int top;
	private int maxSize;
	
	@SuppressWarnings("unchecked")
	public GStack(Class<E> clss, int maxSize) {
		this.maxSize = maxSize;
		top = -1;
		arr = (E[]) Array.newInstance(clss, maxSize);
	}
	
	public void display() {
		for(int i = top; i >=0; i--) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public void push(E value) {
		if(!isFull()) {
			arr[++top] = value;
		}
	}
	
	public void pushAll(E[] arr) {
		for(E value: arr) {
			push(value);
		}
	}
	
	public E pop() { 
		return arr[top--];
	}
	
	@SuppressWarnings("unchecked")
	public E[] popAll(Class<E> clss) {
		E[] temp = (E[]) Array.newInstance(clss, maxSize);
		int i = 0;
		while(!isEmpty()) {
			temp[i++] = pop();
		}
		return Arrays.copyOf(temp, i);	
	}
	
	public E peek() { 
		return arr[top];
	}
	
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
}
