package p2_doubly_linked_lists;

public class Demo {

	public static void main(String[] args) {
		DoublyLinkedList dList = new DoublyLinkedList();
		dList.insertFirst(22);
		dList.insertFirst(44);
		dList.insertFirst(66);
//		theList.displayForward();
//		theList.displayBackward();
//		
		dList.insertAfter(44, 33);
		dList.displayForward();
		dList.deleteKey(44);
		dList.displayForward();

	//end of main
	}
}
