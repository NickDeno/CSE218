package p3_bag;

public class Demo {
	
	public static void main(String[] args) {
		Bag<Student> myBag = new Bag<>(5);
		myBag.insert(new Student("A","AA", 1.9));
		myBag.insert(new Student("B","BB", 2.9));
		myBag.insert(new Student("C","CC", 3.9));
		myBag.display();
	}
}
