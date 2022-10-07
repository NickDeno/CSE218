package p2_priority_queue;

public class DemoPQ {

	public static void main(String[] args) {
		PQueue pq1 = new PQueue(5);
		pq1.insert(10);
		pq1.insert(20);
		pq1.insert(50);
		pq1.insert(70);
		pq1.insert(30);
		while(!pq1.isEmpty()) {
			System.out.print(pq1.remove() + " ");
		}
	}

}
