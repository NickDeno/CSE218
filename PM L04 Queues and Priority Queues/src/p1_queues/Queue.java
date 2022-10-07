package p1_queues;

public class Queue {
	private long[] queArray;
	private int front;
	private int rear;
	private int maxSize;
	private int nElems;
	
	public Queue(int maxSize) {
		this.maxSize = maxSize;
		front = 0;
		rear = -1;
		nElems = 0;
		queArray = new long[maxSize];
	}
	
	public void add(long value) {
		if(rear == maxSize - 1) {
			rear = -1; // wrap around
		}
		queArray[++rear] = value;
		nElems++;
	}
		
	public long remove() {
		long temp = queArray[front++];
		if(front == maxSize) { // wrap-around
			front = 0;
		}
		nElems--;
		return temp;
	}
	
	public boolean isFull() {
		return nElems == maxSize;
	}
	
	public boolean isEmpty() {
		return nElems == 0;
	}
	

}
