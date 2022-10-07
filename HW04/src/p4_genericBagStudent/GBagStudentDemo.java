package p4_genericBagStudent;

import java.util.Arrays;

public class GBagStudentDemo {
	//g
		public static void main(String[] args) {
			GBag<Student> studentBag = new GBag(Student.class, 5);
			
			Student s1 = new Student("John", 3.5);
			Student s2 = new Student("Jack", 2.4);
			Student s3 = new Student("James", 3.6);
			Student s4 = new Student("Jim", 3.5);
			Student s5 = new Student("John", 2.2);
			
			studentBag.insert(s1);
			studentBag.insert(s2);
			studentBag.insert(s3);
			studentBag.insert(s4);
			studentBag.insert(s5);
			
			//Original Bag
			System.out.println("Original Student Bag");
			studentBag.display();
			
			//Testing sort methods
			System.out.println("Sorted Bag Based Off User Sort Parameter:");
			studentBag.sort((o1,o2) -> o1.getGpa().compareTo(o2.getGpa()));
			studentBag.display();
			System.out.println("Sorted Bag Based Off Default Sort Parameter:");
			studentBag.sort(); //Without specifying what sort uses to sort by, it uses default compareTo method of object in bag.
			studentBag.display();
			
			//Testing predicate search method (By Id)
			System.out.println("Sequential Predicate Search by Id:");
			Student[] idSearchResults = studentBag.search(Student.class, s -> s.getId().equals("2"));
			System.out.println(Arrays.toString(idSearchResults));
			System.out.println();
			
			//Testing predicate search method (By name)
			System.out.println("Sequential Predicate Search by Name:");
			Student[] nameSearchResults = studentBag.search(Student.class, s -> s.getName().equals("John"));
			System.out.println(Arrays.toString(nameSearchResults));
			System.out.println();
			
			//Testing predicate remove method (By Id);
			System.out.println("Sequential Predicate Remove by Id:");
			Student[] idRemoveResults = studentBag.remove(Student.class, s -> s.getId().equals("2"));
			System.out.println("Student Removed by Id: " + Arrays.toString(idRemoveResults));
			System.out.println("Student Bag After Id Removal:");
			studentBag.display();
			
			//Testing predicate remove method (By Name)
			System.out.println("Sequential Predicate Remove by Name");
			Student[] nameRemoveResults = studentBag.remove(Student.class, s -> s.getName().equals("John"));
			System.out.println("Students Removed by Name: " + Arrays.toString(nameRemoveResults));
			System.out.println("Student Bag after Removal:");
			studentBag.display();
		
		}
}
