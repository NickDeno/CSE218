package p3;

import java.util.Arrays;

import p1.Author;
import p1.Book;
import p2.BookBag;

public class Demo {
	
	public static void main(String[] args) {
		Book b1 = new Book("MAT101", new Author("John", "Doe"), 59.99);
		Book b2 = new Book("ENG101", new Author("Jeff", "Jones"), 49.99);
		Book b3 = new Book("HIS101", new Author("Jack", "Brown"), 59.99);
		Book b4 = new Book("ART101", new Author("Jim", "Doe"), 79.99);
		Book b5 = new Book("MAT102", new Author("Bob", "Smith"), 99.99);
		
		BookBag bookBag = new BookBag(5);
		bookBag.insert(b1);
		bookBag.insert(b2);
		bookBag.insert(b3);
		bookBag.insert(b4);
		bookBag.insert(b5);
		
		bookBag.display();
		bookBag.bubbleSortByISBN();
		bookBag.display();
		System.out.println(bookBag.binarySearchByISBN(b3.getIsbn()));
		System.out.println(Arrays.toString(bookBag.sequentialSearchByLastName("Doe")));
		bookBag.deleteByISBN(b1.getIsbn());
		bookBag.display();

	}
}
