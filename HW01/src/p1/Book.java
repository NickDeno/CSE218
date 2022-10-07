package p1;

public class Book {
	private String title;
	private String isbn;
	private Author author;
	private double price;
	
	
	
	public Book(String title, Author author, double price) {
		super();
		this.title = title;
		this.isbn = makeIsbn();
		this.author = author;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	private String makeIsbn() { //Generates a random 13 number string(isbn) that starts with 978....
		String isbn = "978";
		for(int i = 0; i < 10; i++) {
			int randNum = (int)(Math.random() * 10); // Generates a random int number from 0-9
			isbn = isbn + (char)(randNum + '0');
		}
		return isbn;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", isbn=" + isbn + ", author=" + author + ", price=" + price + "]";
	}

	
	
}
