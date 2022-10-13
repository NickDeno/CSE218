package p1_lyricsLinkedList;

import java.io.File;

public class Demo {

	public static void main(String[] args) {
		ParentList lyricsList = new ParentList();
		lyricsList.fillWithLyrics(new File("data/blowingInTheWind.txt"));
		lyricsList.display();
	}
}
