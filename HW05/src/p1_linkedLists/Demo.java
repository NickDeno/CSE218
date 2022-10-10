package p1_linkedLists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		LinkedList<ParentLink> parentList = lyricsToList("data/blowingInTheWind.txt");
		for(ParentLink p: parentList) {
			System.out.println(p.toString());
		}
	}
	
	public static LinkedList<ParentLink> lyricsToList(String filename){
		String[] lyrics = getLyrics(filename);
		LinkedList<String> usedKeywords = new LinkedList<String>();
		LinkedList<ParentLink> parentList = new LinkedList<ParentLink>();
		
		for(int i = 0; i < lyrics.length ; i++) {
			if(!usedKeywords.contains(lyrics[i])) { //Checks if current "keyword" has not been used already (IE parentList doesn't contain ParentLink with that keyword already).
				LinkedList<String> babyList = new LinkedList<String>();
				for(int j = i; j < lyrics.length-1; j++) { //Adds all words following keyword into a "baby" LinkedList
					if(lyrics[j].equals(lyrics[i])) {
						babyList.add(lyrics[j+1]);
					}
				}
				parentList.add(new ParentLink(lyrics[i], babyList)); //Adds a "ParentLink" to parentList which contains unique keyword, and linkedList of words that follow keyword
				usedKeywords.add(lyrics[i]);
			}
		}
		return parentList;
	}
	
	public static String[] getLyrics(String fileName) {
		File file = new File(fileName);
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
