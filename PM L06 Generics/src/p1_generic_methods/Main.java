package p1_generic_methods;

public class Main {

	public static void main(String[] args) {
		Student s1 = new Student(new Name("AA", "A"), 4.0);
		Student s2 = new Student(new Name("BB", "B"), 3.0);
		Student s3 = new Student(new Name("CC", "C"), 2.0);
		
		Integer[] a1 = {1,2,3,4};
		String[] a2 = {"A","B","C"};
		Student[] a3 = {s1,s2,s3};
		
		displayArray(a1);
//		displayArray(a2);
//		displayArray(a3);
	}
	
	public static <E extends Number> void displayArray(E[] a) {
		for(E e: a) {
			System.out.println(e + " ");
		}
		System.out.println();
	}
}
