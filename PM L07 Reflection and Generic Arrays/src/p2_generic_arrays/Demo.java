package p2_generic_arrays;

import java.util.Arrays;

public class Demo {
	
	public static void main(String[] args) {
		Bag<Book> bookBag = new Bag<>(Book.class, 5);
		
		Book b1 = new Book("A", 10.0);
		Book b2 = new Book("B", 20.0);
		Book b3 = new Book("C", 30.0);
		
		bookBag.insert(b1);
		bookBag.insert(b2);
		bookBag.insert(b3);
		
		Book[] searchResults = bookBag.search(Book.class, b -> b.getTitle().equals("A") || b.getPrice() == 20.0);
		System.out.println(Arrays.toString(searchResults));
		
	}
}
