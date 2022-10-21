package p2;

public class Link <E extends Number> {
	private E value;
	private Link<E> next;
	
	public Link(E value) {
		this.value = value;
		next = null;
	}
	
	public void display() {
		System.out.println(value);
	}

	public Link<E> getNext() {
		return next;
	}

	public E getValue() {
		return value;
	}

	public void setNext(Link<E> next) {
		this.next = next;
	}
	
}
