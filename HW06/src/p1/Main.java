package p1;

public class Main {

	public static void main(String[] args) {
		Integer[] intArr = {1,2,3,4,5};
		String[] strArr = {"A","B","C","D","E"};
			
		System.out.println("isMember Results Integer Array:");
		System.out.println(Util.isMember(intArr, 4, 0, intArr.length-1));
		System.out.println(Util.isMember(intArr, 10, 0, intArr.length-1) + "\n");
		
		System.out.println("isMember Results String Array:");
		System.out.println(Util.isMember(strArr, "C", 0, intArr.length-1));
		System.out.println(Util.isMember(strArr, "F", 0, intArr.length-1) + "\n");
		
		
	}

}
