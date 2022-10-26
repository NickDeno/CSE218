package p4;

public class Util {
	// isPalindrome Method from Lecture
	public static boolean isPalindrome(String str, boolean isCaseSensitive) {
		int front = 0;
		int rear = str.length()-1;
		if(front >= rear) return false;
		
		if(isCaseSensitive) {
			if(str.charAt(front) != str.charAt(rear)) return false;
			return isPalindrome(str.substring(front+1, rear), isCaseSensitive);
		} else {
			if(Character.toLowerCase(str.charAt(front)) != Character.toLowerCase(str.charAt(rear))) return false;
			return isPalindrome(str.substring(front+1, rear), isCaseSensitive);
		}
	
	}
}
