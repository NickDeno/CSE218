package p1;

public class DemoQueue {

	public static void main(String[] args) {
		Queue myQ = new Queue(5);
		myQ.add("10");
		myQ.add("20");
		myQ.add("30");
		myQ.add("40");
		
		System.out.println(myQ.remove());
		System.out.println(myQ.remove());
		System.out.println();
		
		myQ.add("50");
		while(!myQ.isEmpty()) {
			System.out.println(myQ.remove());
		}
	}

}
