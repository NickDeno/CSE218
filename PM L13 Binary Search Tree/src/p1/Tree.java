package p1;

public class Tree {
	public Node root;
	
	//Key of this tree is iData
	public Tree() {
		root = null;
	}
	
	public Node maximum() {
		Node current = root;
		Node last = null; // Last right node
		while(current != null) {
			last = current;
			current = current.rightChild;
		}
		return last;
	}
	
	public Node minimum() {
		Node current = root;
		Node last = null; // Last left node
		while(current != null) {
			last = current;
			current = current.leftChild;
		}
		return last;
	}
	
	public Node find(int key) {
		Node current = root;
		while(current.iData != key) { // While no match found
			if(key < current.iData) { // Go left
				current = current.leftChild;
			} else { // Go right
				current = current.rightChild;
			}
			
			if(current == null) {
				return null;
			}
		}
		return current;
	}

	public void insert(int iData, double dData) {
		Node newNode = new Node();
		newNode.iData = iData;
		newNode.dData = dData;
		
		if(root == null) { // Tree is empty
			root = newNode; 
		} else { // Tree is not empty
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if(iData < current.iData) { // Go left
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else { // Go right
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	public boolean isEmpty() {
		return root == null;
	}
}
