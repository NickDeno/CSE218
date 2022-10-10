package p1_linkedLists;

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

	@Override
	public String toString() {
		return "Keyword: \"" + keyword + "\", Followed by Words: " + babyList.toString();
	}
}
