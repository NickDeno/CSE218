package p4_genericBagStudent;

public class Student implements Comparable<Student> {
	private String name;
	private String id;
	private Double gpa;
	private static int idCount = 0;
	
	public Student(String name, Double gpa) {
		super();
		this.name = name;
		this.id = String.valueOf(idCount++);
		this.gpa = gpa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getGpa() {
		return gpa;
	}

	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", gpa=" + gpa + "]";
	}

	@Override
	public int compareTo(Student s) {
		return this.id.compareTo(s.getId());
	}
	
}
