package p4;

public class Main {

	public static void main(String[] args) {
		String str1 = "abcdcba";
		String str2 = "AbCdcBa";
		String str3 = "abcd";
		boolean isCaseSensitive = false;
		
		System.out.println("isPalindrome Method Test for " + str1 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str1, isCaseSensitive));
		System.out.println();
		
		System.out.println("isPalindrome Method Test for " + str2 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str2, isCaseSensitive));
		System.out.println();
		
		System.out.println("isPalindrome Method Test for " + str3 + " (Case InSensitive):");
		System.out.println(Util.isPalindrome(str3, isCaseSensitive));
		System.out.println();
		
		isCaseSensitive = true;
		System.out.println("isPalindrome Method Test for " + str2 + ": (Case Sensitive)");
		System.out.println(Util.isPalindrome(str2, isCaseSensitive));
		System.out.println();
	}

}
