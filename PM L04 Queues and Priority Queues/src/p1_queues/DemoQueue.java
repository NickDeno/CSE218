package p1_queues;

public class DemoQueue {

	public static void main(String[] args) {
		Queue q = new Queue(5);
		q.add(10);
		q.add(20);
		q.add(30);
		System.out.println(q.remove());
		System.out.println(q.remove());
		q.add(40);
		q.add(50);
		q.add(60);
		q.add(70);
		System.out.println(q.isFull());
		
		while(!q.isEmpty()) {
			System.out.println(q.remove());
		}
		
	}

}
