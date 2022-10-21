package p5_key;

import java.util.LinkedList;

public class UniqueLinkedList {
	private LinkedList<String> list;
	
	private UniqueLinkedList() {
		list = new LinkedList<>();
	}
	
	public void addUniqueWord(String word) {
		if(isUnique(word)) {
			list.add(word);
		}
	}
	
	public void addUniqueWords(String[] words) {
		for(int i = 0; i < words.length; i++) {
			addUniqueWord(words[i]);
		}
	}
	
	public void display() {
		System.out.println(list);
	}

	private boolean isUnique(String word) {
		if(list.contains(word)) {
			return false;
		}
		return true;
	}
	
}
