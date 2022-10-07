package p2;

public class StudentBag {
	private Student[] arr;
	private int nElems;
	
	
	public StudentBag(int maxSize) {
		arr = new Student[maxSize];
	}
	
	public void insert(Student student) {
		arr[nElems++] = student;
	}
	
	public void fill(int numberOfStudents) {
		for(int i = 0; i < numberOfStudents; i++) {
			
			String randName = "";
			for(int j = 0; j < 3; i++) {
				if(j == 0) {
					randName = randName + (char)((Math.random() * 26) + 'A');
				} else {
					randName = randName + (char)((Math.random() * 26) + 'a');
				}
			}
			double randGpa = Math.random() * 4.0;
			int randAge = (int)(Math.random() * (23) + 18);
		
			arr[i].setName(randName);
			arr[i].setGpa(randGpa);
			arr[i].setAge(randAge);
			
		}	
	}
	
	public void displayStudents() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	
	
	
}
