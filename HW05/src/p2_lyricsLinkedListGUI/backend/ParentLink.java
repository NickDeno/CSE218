package p2_lyricsLinkedListGUI.backend;

import java.util.LinkedList;

public class ParentLink {
	private String keyword;
	private LinkedList<String> babyList;
	
	public ParentLink(String keyword, LinkedList<String> babyList) {
		this.keyword = keyword;
		this.babyList = babyList;
	}

	public String getKeyword() {
		return keyword;
	}
	
	public LinkedList<String> getBabyList(){
		return babyList;
	}

	@Override
	public String toString() {
		return "Keyword: \"" + keyword + "\", Followed by Words: " + babyList.toString();
	}
}
