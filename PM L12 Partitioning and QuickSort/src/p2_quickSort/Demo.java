package p2_quickSort;

public class Demo {

	public static void main(String[] args) {
		ArrayQuick arr = new ArrayQuick(20);
		arr.insert(5);
		arr.insert(6);
		arr.insert(1);
		arr.insert(2);
		arr.insert(8);
		arr.insert(3);
		arr.insert(7);
		arr.insert(4);
		
		arr.display();
		
		arr.quickSort();
		
		arr.display();
	}

}
