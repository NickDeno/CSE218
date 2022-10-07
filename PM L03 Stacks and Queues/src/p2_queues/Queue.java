package p2_queues;

public class Queue {
	private long[] queArray;
	private int front;
	private int rear;
	private int maxSize;
	private int nElems;
	
	public Queue(int maxSize) {
		queArray = new long[maxSize];
		this.maxSize = maxSize;
		nElems = 0;
		rear = -1;
		front = 0;
	}
	
	public long remove() {
		long temp = queArray[front++];
		if(front == maxSize) {
			front = 0;
		}
		nElems--;
		return temp;
	}
 	
	public void add(long value) {
		if(rear == maxSize - 1) {
			rear = -1;
		}
		queArray[++rear] = value;
		nElems++;
	}
	
	public boolean isFull() {
		return nElems == maxSize;
	}
	
	public boolean isEmpty() {
		return nElems == 0;
	}
}
