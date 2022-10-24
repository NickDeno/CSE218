package p4;

public class Main {

	public static void main(String[] args) {
		String str1 = "abcdcba";
		String str2 = "AbCdcBa";
		String str3 = "abcd";
		
		//Testing Default isPalindrome method:
		System.out.println("Default isPalindrome Method Test for " + str1 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str1, 0, str1.length()-1));
		System.out.println();
		
		System.out.println("Default isPalindrome Method Test for " + str2 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str2, 0, str2.length()-1));
		System.out.println();
		
		System.out.println("Default isPalindrome Method Test for " + str3 + " (Case Insensitive):");
		System.out.println(Util.isPalindrome(str3, 0, str3.length()-1));
		System.out.println();
		
		//Testing Overloaded isPalindrome Method (Can chose if case sensitive or not):
		System.out.println("Overloaded isPalindrome Method Case Sensitive Test for " + str2 + ":");
		System.out.println(Util.isPalindrome(str2, 0, str2.length()-1, true));
		System.out.println();
	}

}
