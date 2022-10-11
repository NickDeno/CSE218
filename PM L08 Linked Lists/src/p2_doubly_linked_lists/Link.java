package p2_doubly_linked_lists;

public class Link {
	public long lData;
	public Link next;
	public Link previous;
	
	public Link (long lData) {
		this.lData = lData;
		next = null;
		previous = null;
	}

	public void display() {
		System.out.println("lData: " + lData);
	}
	
	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}
	
}
