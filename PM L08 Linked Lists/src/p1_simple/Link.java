package p1_simple;

public class Link {
	private int iData;
	private Book book;
	private Link next;
	
	public Link(int iData, Book book) {
		this.iData = iData;
		this.book = book;
		next = null;
	}
	
	public void display() {
		System.out.println("iData: " + iData + ", Book: " + book);
	}

	public int getiData() {
		return iData;
	}

	public void setiData(int iData) {
		this.iData = iData;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}
	
}
