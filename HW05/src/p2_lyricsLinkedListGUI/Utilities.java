package p2_lyricsLinkedListGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Utilities {
	public static String generateParagraph(String key, int length, LinkedList<ParentLink> list) {
		Random random = new Random();
		String paragraph = "";
		for(int i = 0; i < length; i++) {
			paragraph += key + " ";
			for(ParentLink p: list) {
				if(p.getKeyword().equals(key)) {
					String randWord = p.getBabyList().get(random.nextInt(p.getBabyList().size()));
					key = randWord;
					break;
				}
			}	
		}	
		return paragraph;
	}
	
	//Checks if keyword user inputed is present in the list
	public static boolean keyIsPresent(String key, LinkedList<ParentLink> list) {
    	for(ParentLink p: list) {
			if(p.getKeyword().equals(key))	{
				return true;
			}
		}
    	return false;
    }
	public static LinkedList<ParentLink> lyricsToList(File file){
		String[] lyrics = getLyrics(file);
		LinkedList<String> usedKeywords = new LinkedList<String>();	
		LinkedList<ParentLink> parentList = new LinkedList<ParentLink>();	
		for(int i = 0; i < lyrics.length ; i++) {
			if(!usedKeywords.contains(lyrics[i])) { 
				LinkedList<String> babyList = new LinkedList<String>();
				for(int j = i; j < lyrics.length-1; j++) { 
					if(lyrics[j].equals(lyrics[i])) {
						babyList.add(lyrics[j+1]);
					}
				}
				parentList.add(new ParentLink(lyrics[i], babyList));
				usedKeywords.add(lyrics[i]);
			}
		}
		return parentList;
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
