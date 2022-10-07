package p1_generic_methods;

public class Student {
	private Name name;
	private double gpa;
	
	public Student(Name name, double gpa) {
		super();
		this.name = name;
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
	
}
