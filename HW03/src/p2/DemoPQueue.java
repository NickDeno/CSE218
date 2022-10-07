package p2;

public class DemoPQueue {

	public static void main(String[] args) {
		PQueue myPQue = new PQueue(10);
		myPQue.add(10);
		myPQue.add(20);
		myPQue.add(15);
		myPQue.add(30);
		
		
		System.out.println(myPQue.peekMin());
		while(!myPQue.isEmpty()) {
			System.out.println(myPQue.remove());
		}

	}

}
