package p1;

public class Demo {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(5, 5.5);
		tree.insert(10, 5.5);
		tree.insert(7, 1.1);
		tree.insert(3, 5.5);
		tree.insert(4, 5.5);
		tree.insert(6, 5.5);
		tree.insert(7, 5.5);
		tree.insert(2, 5.5);
		
		System.out.println("Min Node in Tree:");
		tree.minimum().displayNode();
		System.out.println("Max Node in Tree");
		tree.maximum().displayNode();
		System.out.println("Search Results");
		tree.find(7).displayNode();
		
	}

}
