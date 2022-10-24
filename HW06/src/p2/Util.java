package p2;

public class Util {
	public static String stringReverser(String str) {
		if(str.length() <= 1) return str;
		return str.charAt(str.length()-1) + stringReverser(str.substring(0, str.length()-1));	
	}
}
