package p3;

import java.util.Date;

public class Student {
	private String name;
	private String phoneNumber;
	private Date regDate;
	
	public Student(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.regDate = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getRegDate() {
		return regDate;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", phoneNumber=" + phoneNumber + ", regDate=" + regDate + "]";
	}
}
