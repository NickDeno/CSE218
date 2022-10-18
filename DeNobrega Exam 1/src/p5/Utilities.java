package p5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Utilities {
	public static void fillWithLyrics(LinkedList<String> lyricsList, File file) {
		String[] lyrics = getLyrics(file);
		for (int i = 0; i < lyrics.length; i++) {
			if(!lyricsList.contains(lyrics[i])) {
				lyricsList.add(lyrics[i]);
			}		
		}
	}
	
	private static String[] getLyrics(File file) {
		String[] arr;
		try {
			Scanner sc = new Scanner(file);
			String line = sc.nextLine();
			arr = line.split("\\s+");
			sc.close();
			return arr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
