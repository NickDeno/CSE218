package p2_lyricsLinkedListGUI.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class ParentList {
	private LinkedList<ParentLink> list;
	
	public ParentList() {
		list = new LinkedList<ParentLink>();
	}
	
	public void insert(String key, LinkedList<String> babyList) {
		list.add(new ParentLink(key, babyList));
	}
	
	public ParentLink get(int idx) {
		return list.get(idx);
	}
	
	public int size() {
		return list.size();
	}
	
	public void display() {
		for(ParentLink p: list) {
			System.out.println(p.toString());
		}
	}
	
	public String search(String key) {
		ListIterator<ParentLink> iter = list.listIterator();
		while(iter.hasNext()) {
			if(iter.next().getKeyword().equals(key)) {
				return key;
			}
		}
		return null;
	}
	
	public String generateParagraph(String key, int length) {
		Random random = new Random();
		String paragraph = "";
		for(int i = 0; i < length; i++) {
			paragraph += key + " ";
			for(int j = 0; j < size(); j++) { 
				if(get(j).getKeyword().equals(key)) {
					String randWord = get(j).getBabyList().get(random.nextInt(get(j).getBabyList().size()));
					key = randWord;
					break;
				}
			}
		}	
		return paragraph;
	}
	
	public void fillWithLyrics(File file) {
		String[] lyrics = getLyrics(file);
		for (int i = 0; i < lyrics.length; i++) { 
			if (search(lyrics[i]) == null) {
				LinkedList<String> babyList = new LinkedList<String>();
				for (int j = i; j < lyrics.length - 1; j++) {
					if (lyrics[j].equals(lyrics[i])) {
						babyList.add(lyrics[j + 1]);
					}
				}
				insert(lyrics[i], babyList);
			}
		}
	}
	
	private String[] getLyrics(File file) {
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