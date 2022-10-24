package p5;

public class Main {

	public static void main(String[] args) {
		char[] charArr = {'A','B','C','D','A','A','B'};
		
		char key1 = 'A';
		System.out.println("Count of " + key1 + ":");
		System.out.println(Util.characterCounter(charArr, key1, 0));
		System.out.println(Util.characterCounter(charArr, key1, 0, charArr.length-1));
		System.out.println();
		
		char key2 = 'F';
		System.out.println("Count of " + key2 + ":");
		System.out.println(Util.characterCounter(charArr, key2, 0));
		System.out.println(Util.characterCounter(charArr, key2, 0, charArr.length-1));
	}

}
