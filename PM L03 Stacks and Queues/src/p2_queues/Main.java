package p2_queues;

public class Main {

	public static void main(String[] args) {
		Queue myQ = new Queue(4);
		myQ.add(10);
		myQ.add(20);
		myQ.add(30);
		myQ.add(40);
		
		System.out.println(myQ.remove());// 10
		System.out.println(myQ.remove()); // 20
		myQ.add(50);
		System.out.println(myQ.remove()); // 30
		System.out.println(myQ.remove()); // 40
		System.out.println(myQ.remove()); // 50
		
		System.out.println(myQ.isEmpty());
	}

}
