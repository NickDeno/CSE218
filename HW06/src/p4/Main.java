package p4;

public class Main {

	public static void main(String[] args) {
		String str1 = "abcdcba";
		String str2 = "AbCdcBa";
		String str3 = "abcd";
		
		//Testing Default isPalindrome method:
		System.out.println("isPalindrome Method Test for " + str1 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str1, false));
		System.out.println();
		
		System.out.println("isPalindrome Method Test for " + str2 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str2, false));
		System.out.println();
		
		System.out.println("isPalindrome Method Test for " + str3 + " (Case InSensitive):");
		System.out.println(Util.isPalindrome(str3, false));
		System.out.println();
		
		System.out.println("isPalindrome Method Test for " + str2 + ": (Case Sensitive)");
		System.out.println(Util.isPalindrome(str2, true));
		System.out.println();
	}

}
