package p4;

public class Demo {

	public static void main(String[] args) {
		GQueue<Book> myQueue = new GQueue<Book>();
		myQueue.add(new Book("CSE118", 50.99));
		myQueue.add(new Book("CSE148", 60.99));
		myQueue.add(new Book("CSE148", 70.99));
		myQueue.display();
		
		while(!myQueue.isEmpty()) {
			System.out.println(myQueue.remove());
		}
	}
}
