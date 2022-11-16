package p1_partition;

public class Demo {
	public static void main(String[] args) {
		int pivot = 10;
		ArrayPar arr = new ArrayPar(10);
		arr.insert(5);
		arr.insert(2);
		arr.insert(4);
		arr.insert(6);
		arr.insert(7);
		arr.insert(3);
		arr.insert(8);
		arr.insert(1);
		arr.display();
		int partition = arr.partitionIt(0, 7, pivot);
		arr.display();
		System.out.println("Partition is at " + partition + " for pivot = " + pivot);
	}
}
