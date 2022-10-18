package p5;

import java.io.File;
import java.util.LinkedList;

public class Demo {

	public static void main(String[] args) {
		LinkedList<String> lyricsList = new LinkedList<String>();
		Utilities.fillWithLyrics(lyricsList, new File("src/p5/words.txt"));
		for(String s: lyricsList) {
			System.out.println(s);
		}
	}

}
