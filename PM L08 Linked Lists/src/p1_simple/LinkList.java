package p1_simple;


/*
 * First in first out data behavior. The benefit of this compared to array based stack is there is no "maxSize"
 * 
 */
public class LinkList {
	private Link first;
	
	public LinkList() {
		first = null;
	}
	
	public Link find(int key) {
		Link current = first;
		while(current.getiData() != key && current != null) {
			current = current.getNext();
		}
		return current;
	}
	
	 // If the list is empty the inserted link is set to the first link, if not then the next link of the new link is set to the
	 // first, and the new link is set to new first.
	public void insert(int iData, String title, double price) {
		Link newLink = new Link(iData, new Book(title, price));
		if(first == null) {
			first = newLink;
		} else {
			newLink.setNext(first);
			first = newLink;
		}
	}
	
	//Removes link from front of list by storing the front link as a temp, overwriting first with the next link of first, making
	//that link the new first link. Then return temp
	public Link remove() {
		Link temp = first;
		first = first.getNext();
		return temp;
	}
	
	public void display() {
		Link current = first; //Holds Link that is being displayed
		while(current != null) {
			current.display();
			current = current.getNext();
		}
	}
}
