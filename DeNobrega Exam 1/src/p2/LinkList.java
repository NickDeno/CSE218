package p2;

public class LinkList <E extends Number> {
	private Link<E> first;
	
	public LinkList() {
		first = null;
	}
	
	public void insert(E value) {
		Link<E> newLink = new Link<E>(value);
		if(first == null) {
			first = newLink;
		} else {
			newLink.setNext(first);
			first = newLink;
		}
	}
	
	public Link<E> remove() {
		Link<E> temp = first;
		first = first.getNext();
		return temp;
	}
	
	public Link<E> getFirst(){
		return first;
	}
	
	public void display() {
		Link<E> current = first; //Holds Link that is being displayed
		while(current != null) {
			current.display();
			current = current.getNext();
		}
		System.out.println();
	}
}
