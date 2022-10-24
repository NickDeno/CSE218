package p4;

public class Util {
	//Default isPalindrome Recursive Method. By default, it IS NOT case sensitive
	public static boolean isPalindrome(String str, int leftIdx, int rightIdx) {
		if(leftIdx == rightIdx) return true;
		if(Character.toLowerCase(str.charAt(leftIdx)) != Character.toLowerCase(str.charAt(rightIdx))) return false;
		return isPalindrome(str, leftIdx+1, rightIdx-1);
	}
	
	// Overloaded isPalindrome Recursive Method. Gives user option to specify if its case sensitive or not
	public static boolean isPalindrome(String str, int leftIdx, int rightIdx, boolean isCaseSensitive) {
		if(isCaseSensitive) {
			if(leftIdx == rightIdx) return true;
			if(str.charAt(leftIdx) != str.charAt(rightIdx)) return false;
			return isPalindrome(str, leftIdx+1, rightIdx-1, isCaseSensitive);
		} else {
			return isPalindrome(str,leftIdx,rightIdx);
		}
	}
}
