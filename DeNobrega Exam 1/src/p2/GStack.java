package p2;


public class GStack <E extends Number> {
	private LinkList<E> list;
	private int nElems;
	
	public GStack() {
		list = new LinkList<E>();
	}
	
	public void push(E value) {
		list.insert(value);	
		nElems++;
	}
	
	public E pop() {
		nElems--;
		return list.remove();
	}
	
	public Link<E> peek() {
		return list.getFirst();
	}
	
	public void display() {
		list.display();
	}
	
	public boolean isEmpty() {
		return nElems == 0;
	}
	
}
