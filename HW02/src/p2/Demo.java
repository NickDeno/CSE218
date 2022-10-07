package p2;

public class Demo {

	public static void main(String[] args) {
		StackX myStack = new StackX(300);
		String paragraph = "Hello, my name is Nick. I am majoring in CS. I am excited to persue this degree! What is your major? Do you enjoy it?";
		
		System.out.println("Original: " + paragraph);
		System.out.println("Reversed Sentences: " + myStack.reverseSentences(paragraph)); // Reverses order of sentences and displays it
		System.out.println("Reversed Characters: " + myStack.reverseCharacters(paragraph)); // Reverses order of characters and displays it
		
	}

}
