package p1;

public class Node {
	public int iData;
	public double dData;
	
	public Node leftChild;
	public Node rightChild;
	
	public void displayNode() {
		System.out.print(iData + ", " + dData);
		System.out.println();
	}
	
	
}
