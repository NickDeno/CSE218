package p2;

import java.util.ArrayList;

public class StackX {
	private ArrayList<String> arr;
	private int top;
	
	public StackX(int maxSize) {
		arr = new ArrayList<>(maxSize);
		top = -1;
	}
	
	public void push(String str) {
		arr.add(++top,str);
	}
	
	public String pop() {
		if(!arr.isEmpty()) {
			return arr.remove(top--);
		} else {
			System.out.println("No element present to pop.");
			return null;
		}
	}
	
	public String peek() {
		if(!arr.isEmpty()) {
			return arr.get(top);	
		} else {
			System.out.println("No element present to peek.");
			return null;
		}
	}
	
	public String reverseCharacters(String str) {
		for(int i = 0; i < str.length(); i++) {
			push(Character.toString(str.charAt(i)));
		}
		String reversedStr = "";
		while(!arr.isEmpty()) {
			reversedStr += pop();
		}
		return reversedStr;
	}
	
	public String reverseSentences(String str) {
		for(String s: str.split("(?<=\\.|\\?|\\!)")) {
			push(s);		
		}	
		String reversedStr = "";
		while(!arr.isEmpty()) {
			reversedStr += pop();
		}
		return reversedStr;
	}
}
