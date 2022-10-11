package p1_lyricsLinkedList;

import java.io.File;
import java.util.LinkedList;

public class Demo {

	public static void main(String[] args) {
		LinkedList<ParentLink> lyricsList = Utilities.lyricsToList(new File("data/blowingInTheWind.txt"));
		for(ParentLink p: lyricsList) {
			System.out.println(p.toString());
		}
	}
}
