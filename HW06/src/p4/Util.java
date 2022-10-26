package p4;

public class Util {
	// Overloaded isPalindrome Recursive Method. Gives user option to specify if its case sensitive or not. More efficient due to only passing
	// through index values each time instead of creating substrings each time. However it is less intuitive for user as they have to pass through
	// a leftIdx and rightIdx when calling method
		public static boolean isPalindrome(String str, int leftIdx, int rightIdx, boolean isCaseSensitive) {
			if(leftIdx == rightIdx) return true;
			if(isCaseSensitive) {
				if(str.charAt(leftIdx) != str.charAt(rightIdx)) return false;
				return isPalindrome(str, leftIdx+1, rightIdx-1, isCaseSensitive);
			} else {
				if(Character.toLowerCase(str.charAt(leftIdx)) != Character.toLowerCase(str.charAt(rightIdx))) return false;
				return isPalindrome(str,leftIdx,rightIdx, isCaseSensitive);
			}
		}
	
	// Overloaded isPalindrome method. Gives user to specify if case sensitive or not however is less efficient then first method. This is because each time,
	// a substring is created excluding the "checked characters" being the front and rear characters. To do this, a substring is created each time and is passed through.
	// This makes it more intuitive and easy to use for user as they do not have to pass through a leftIdx and rightIdx.
	public static boolean isPalindrome(String str, boolean isCaseSensitive) {
		int front = 0;
		int rear = str.length()-1;
		if(front >= rear) return true;
		
		if(isCaseSensitive) {
			if(str.charAt(front) != str.charAt(rear)) return false;
			return isPalindrome(str.substring(front+1, rear), isCaseSensitive);
		} else {
			if(Character.toLowerCase(str.charAt(front)) != Character.toLowerCase(str.charAt(rear))) return false;
			return isPalindrome(str.substring(front+1, rear), isCaseSensitive);
		}
	
	}
}
