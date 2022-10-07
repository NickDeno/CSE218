package p4;

import java.util.Date;

public class Student {
	private String name;
	private double gpa;
	private Date regDate;
	
	public Student(String name, double gpa) {
		super();
		this.name = name;
		this.gpa = gpa;
		this.regDate = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setPhoneNumber(double gpa) {
		this.gpa = gpa;
	}

	public Date getRegDate() {
		return regDate;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", gpa=" + gpa + ", regDate=" + regDate + "]";
	}
}
