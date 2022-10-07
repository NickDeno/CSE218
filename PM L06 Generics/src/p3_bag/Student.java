package p3_bag;

public class Student implements Comparable<Student> {
	private Name name;
	private double gpa;
	
	public Student(String firstName, String lastName, double gpa) {
		super();
		this.name = new Name(firstName, lastName);
		this.gpa = gpa;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", gpa=" + gpa + "]";
	}

	@Override
	public int compareTo(Student s) {
		return this.name.getFirstName().compareTo(s.name.getFirstName());
	}
	
}
